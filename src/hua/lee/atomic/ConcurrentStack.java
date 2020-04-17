package hua.lee.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过 AtomicReference 实现线程安全的入栈和出栈操作
 *
 * @param <E> 栈元素类型
 */
public class ConcurrentStack<E> {
    private final AtomicReference<Node<E>> top = new AtomicReference<>();

    /**
     * 将元素放入栈顶
     *
     * @param item 待放入的元素
     */
    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead = null;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    /**
     * 弹出栈顶部元素
     *
     * @return 栈顶部元素，可能为 null
     */
    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    /**
     * 单向链表
     *
     * @param <E> 数据类型
     */
    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
