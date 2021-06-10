package dataStructures;

import java.util.*;

public class HashTable<K, V> implements DictionaryADT<K,V>{
    private ArrayList<HashNode<K, V> > bucketArray;

    private int numBuckets;
    private int size;

    public HashTable()
    {
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size=0;

        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isFull() {
        if (bucketArray.size() == size) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0; }

    @Override
    public void clear() {

        bucketArray.clear();
    }

    @Override
    public Iterator values() {

        return bucketArray.iterator();
    }

    @Override
    public Iterator keys() {

        return bucketArray.iterator();
    }

    @Override
    public boolean delete(K key)
    {
        int hashCode = Objects.hashCode(key);
        int index = hashCode % numBuckets;
        index = index < 0 ? index * -1 : index;
        int bucketIndex = index;
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        if (Objects.isNull(head)){
            return false;
        }
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;
            prev = head;
            head = head.next;
        }

        if (head == null)
            return false;
        size--;

        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);

        return true;
    }

    public V get(K key)
    {
        int hashCode = Objects.hashCode(key);
        int index = hashCode % numBuckets;
        index = index < 0 ? index * -1 : index;
        int bucketIndex = index;

        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
       Iterator<HashNode<K,V>> hashNodeIterator = bucketArray.iterator();
       while (hashNodeIterator.hasNext()){
           HashNode<K,V> hashNode = hashNodeIterator.next();
           while (Objects.nonNull(hashNode)){
               if (hashNode.key.equals(key)){
                   return true;
               }
               hashNode = hashNode.next;
           }
       }
       return false;
    }

    @Override
    public boolean add(K key, V value)
    {
        int hashCode = Objects.hashCode(key);
        int index = hashCode % numBuckets;
        index = index < 0 ? index * -1 : index;
        int bucketIndex = index;
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return false;
            }
            head = head.next;

        }

        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode
                = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
        return true;
    }

    @Override
    public V getValue(K key) {
        String name=null;
        Iterator<HashNode<K,V>> iterator = bucketArray.iterator();

        while (iterator.hasNext()){
            HashNode<K,V> hashNode = iterator.next();
            while (hashNode!=null) {
                if (hashNode.key.equals(key)){
                    name = (String) hashNode.value;
                }
                hashNode = hashNode.next;
            }
        }
        return (V) name;
    }

    @Override
    public K getKey(V value) {
        PhoneNumber phoneNumber = null;
        Iterator<HashNode<K,V>> iterator = bucketArray.iterator();
        while (iterator.hasNext()){
            HashNode<K,V> hashNode = iterator.next();
            while (hashNode!=null) {
                if (hashNode.value.equals(value)){
                    phoneNumber = (PhoneNumber) hashNode.key;
                }
                hashNode = hashNode.next;
            }
        }
        return (K) phoneNumber;
    }


    @Override
    public int compareTo(DictionaryADT o) {
       return 0;
    }
}
