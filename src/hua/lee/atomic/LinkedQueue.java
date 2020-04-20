package hua.lee.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue<E> {
    /**
     * 链表结构
     * next 使用 AtomicReference 来管理，用来保证原子性和线程安全
     *
     * @param <E> 数据类型
     */
    private static class Node<E> {
        final E item;
        /**
         * 通过 AtomicReference 实现指针的原子操作
         */
        final AtomicReference<Node<E>> next;

        /**
         * Node 构造方法
         *
         * @param item 数据元素
         * @param next 下一个节点
         */
        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    /**
     * 哨兵，队列为空时，头指针（head）和尾指针（tail）都指向此处
     */
    private final Node<E> GUARD = new Node<>(null, null);
    /**
     * 头节点，初始时指向 GUARD
     */
    private final AtomicReference<Node<E>> head = new AtomicReference<>(GUARD);
    /**
     * 尾节点，初始时指向 GUARD
     */
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(GUARD);

    /**
     * 将数据元素放入链表尾部
     * <p>
     * 在插入新元素之前，将首先检查tail 指针是否处于队列中间状态，
     * 如果是，那么说明有另一个线程正在插入元素。
     * 此时线程不会等待其他线程执行完成，而是帮助他完成操作，将 tail 指针指向下一个节点。
     * 然后重复进行检查确认，直到 tail 完全处于队列尾部才开始执行自己的插入操作。
     * 如果两个线程同时插入元素，curTail.next.compareAndSet 会失败，这种情况下不会对当前数据结构造成破坏。当前线程只需重新读取tail 并再次重试。
     * 如果curTail.next.compareAndSet执行成功，那么插入操作已生效。
     * 此时 tail.compareAndSet(curTail, newNode) 会进行尾部指针的移动：
     * 如果移动失败，那么当前线程将直接返回，不需要进行重试
     * 因为另一个线程在检查 tail 时候会帮助更新。
     *
     * @param item 数据元素
     * @return true 成功
     */
    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            //判断下尾部节点是否出现变动
            if (curTail == tail.get()) {
                //tailNext节点为空的话，说明当前 tail 指在了队列的尾部
                if (tailNext == null) {
                    //将新节点设置成 当前尾节点 的 next节点，此处为原子操作，失败则 while 循环重试
                    if (curTail.next.compareAndSet(null, newNode)) {
                        //将 tail 节点的指针指向 新节点
                        //此处不用担心 tail.compareAndSet 会更新失败
                        //因为当更新失败的情况下，肯定存在其他线程在操作
                        //另一个线程会进入 tailNext!=null 的情况，重新更新指针
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                } else {
                    //当前尾节点 的 next 不为空的话，说明链表已经被其他线程操作过了
                    //直接将 tail 的 next 指针指向下个节点
                    tail.compareAndSet(curTail, tailNext);
                }
            }
        }
    }
}
