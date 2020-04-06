package hua.lee.lock;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteMap<K,V>{
    private final Map<K,V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock read = lock.readLock();
    private final Lock write = lock.writeLock();
    public ReadWriteMap(Map<K,V> map){
        this.map = map;
    }
    public V put(K key,V value){
        write.lock();
        try {
            return map.put(key, value);
        }finally{
            write.unlock();
        }
    }
    public V get(Object key){
        read.lock();
        try {
            return map.get(key);
        }finally{
            read.unlock();
        }
    }
}


