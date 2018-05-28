package com.lxw.data.map;


import java.util.Objects;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/05/27
 *     desc   :
 * </pre>
 */

class LxwHashMap<K, V> {
    //<<      :     左移运算符，num << 1,相当于num乘以2
    //
    //>>      :     右移运算符，num >> 1,相当于num除以2
    //
    //>>>    :     无符号右移，忽略符号位，空位都以0补齐
    private static final int MINIMUM_CAPACITY = 4;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static  EntryNode[] EMTRY_TABLE = new EntryNode[MINIMUM_CAPACITY >> 1];
    private EntryNode<K, V>[] table;
    private EntryNode<K, V> nullKeyEntry;

    private int size;

    private int threshold;

    LxwHashMap() {
        table = EMTRY_TABLE;
        threshold = -1;
    }

    LxwHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity should not negative " + capacity);
        }

        if (capacity == 0) {
            EntryNode<K, V>[] tab = EMTRY_TABLE;
            table = tab;
            threshold = -1;
            return;
        }
        if (capacity <= MINIMUM_CAPACITY) {
            capacity = MINIMUM_CAPACITY;
        } else if (capacity >= MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        } else {
            capacity = roundUpToPowerOfTwo(capacity);
        }
        makeTable(capacity);
    }

    private EntryNode<K, V>[] makeTable(int capacity) {
        EntryNode<K, V>[] tab = new EntryNode[capacity];
        table = tab;
        threshold = (tab.length >> 1) + (tab.length >> 2);
        System.out.println(threshold);
        return table;

    }

    /**
     * 保证容量是2的倍数
     */
    public int roundUpToPowerOfTwo(int i) {
        i--; // If input is a power of two, shift its high-order bit right.

        // "Smear" the high-order bit all the way to the right.
        i |= i >>> 1;
        i |= i >>> 2;
        i |= i >>> 4;
        i |= i >>> 8;
        i |= i >>> 16;

        return i + 1;
    }


    public V put(K key, V value) {
        if (key == null) {
            return putValueForNullKey(value);
        }

        int hash = secondaryHash(key.hashCode());
        int index = hash & (table.length - 1);
        EntryNode<K, V>[] tab = table;
        //如果key已存在的情况下
        for (EntryNode<K, V> e = tab[index]; e != null; e = e.next) {
            //这里 先判断 e.key == key 进行基本数据类型的 对比，再进行引用类型的 比较 速度优化
            if (e.hash == hash && (e.key == key || e.key.equals(key))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        //size的值比较后 再加1，所以doubleCapacity时要注意判断size为0的情况
        if (size++ > threshold) {
          table =  doubleCapacity();
          index = hash&(table.length-1);
        }
        addNewEntry(index,hash,key,value);

        return null;
    }

    /**
     *创建一个新的node节点，并放置在 原先节点的头部
     */
    private void addNewEntry(int index, int hash, K key, V value) {
        table[index] = new EntryNode<K,V>(hash,key,value,table[index]);
    }

    /**
     * https://www.zhihu.com/question/45989507
     * 扩容算法
     * 如果 容器的大小已经 到达  MAXIMUM_CAPACITY,则返回
     * 如果 size为0， 扩容后，直接返回新table
     * 其他情况下， 扩容数组后，重新散列原来的数据 android下使用hightBit做重新散列优化{https://www.zhihu.com/question/45989507}
     *
     * @return table
     */
    private EntryNode<K, V>[] doubleCapacity() {
        EntryNode<K, V>[] oldTable = table;
        int oldLength = oldTable.length;
        if (oldLength == MAXIMUM_CAPACITY) {
            return oldTable;
        }

        int newLength = oldLength << 1;
        System.out.println("newLength: "+newLength);
        System.out.println("oldLength: "+oldLength);
        EntryNode<K, V>[] newTable =makeTable(newLength);
        if (size == 0) {
            return newTable;
        }

        for (int j = 0; j < oldLength; j++) {
            EntryNode<K, V> e = oldTable[j];
            if (e == null) {
                continue;
            }
            int hightBit = e.hash & oldLength;
            newTable[j | hightBit] = e;
            EntryNode<K, V> broken = null;
            for (EntryNode<K, V> n = e.next; n != null; e = n, n = n.next) {
                int nextHightBit = n.hash & oldLength;
                //nextHightBit的值要么是0，要么是1，
                // 所以下面一端代码就是，要么在原来的[j|hightBit]上，用链表链下去，
                // 要么是在新的[j|nextHightBit]上创建一个新结点，然后使用链表，一直往下链
                if (hightBit != nextHightBit) {
                    if (broken == null) {
                        //高位 | index ，无重复的可能
                        newTable[j | nextHightBit] = n;
                    } else {
                        broken.next = n;
                    }
                    broken = e;
                    hightBit = nextHightBit;
                }
            }
            if (broken != null) {
                broken.next = null;
            }
        }
        return newTable;

//jdk中的实现
//        for(int i = 0;i<oldLength;i++){
//            EntryNode<K,V> e = oldTable[i];
//            if(e ==null){
//                continue;
//            }
//            for(EntryNode<K,V> n = e.next;n!=null;e=n,n=n.next){
//                int index = e.hash&(newLength-1);
//                e.next = newTable[index];
//                newTable[index] = e;
//            }
//        }
//        return newTable;
    }

    /**
     * int index = hash & (tab.length - 1);
     * 普通开发者写出来的hashCode的返回值key可能太大，不适合直接当做key使用，需要将他们映射到哈希表中，
     * 所以需要做上面这行代码的与运算，但是我们的hashCode返回值可能低位不够随机，所以需要secondaryHash一次。
     *
     * @param h key的hashcode
     * @return 二次hash
     */
    private int secondaryHash(int h) {
        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }

    private V putValueForNullKey(V value) {
        EntryNode<K, V> entry = nullKeyEntry;
        if (entry == null) {
            addNewEntryForNullKey(value);
            size++;
            return null;
        } else {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
    }

    private void addNewEntryForNullKey(V value) {
        nullKeyEntry = new EntryNode<K, V>(0, null, value, null);
    }

    public V get(K key){
        if(key == null){
           return getValueFromNullKey();
        }
        EntryNode<K,V>[] tab = table;
        int hash = secondaryHash(key.hashCode());
        int index = hash&(tab.length-1);
        for(EntryNode<K,V> e = tab[index];e!=null;e=e.next){
             if(e.hash == hash&&(e.key==key||e.key.equals(key))){
                 return e.value;
             }
        }
        return null;
    }

    public V remove (K key){
        if(key ==null){
         return nullKeyEntry ==null? null:nullKeyEntry.value;
        }
        EntryNode<K,V>[] tab = table;
        int hash = secondaryHash(key.hashCode());
        int index = hash&(tab.length-1);
        for( EntryNode<K,V> e = tab[index],pre =null; e!=null; pre=e,e=e.next){
            if(e.hash == hash&&(e.key==key||e.key.equals(key))){
               if(pre ==null){
                   tab[index] = e.next;
               }else{
                   pre.next = e.next;
               }
               size --;
               return e.value;
            }
        }
        return null;
    }

    private V getValueFromNullKey() {
        if(nullKeyEntry!=null){
           return nullKeyEntry.value;
        }
        return null;
    }


    static class EntryNode<K, V> {
        int hash;
        K key;
        V value;
        EntryNode<K, V> next;

        public EntryNode(int hash, K key, V value, EntryNode<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof EntryNode)) {
                return false;
            }
            EntryNode<?, ?> e = (EntryNode<?, ?>) o;
            return Objects.equals(e.key, key)
                    && Objects.equals(e.value, value);
        }

        @Override
        public final int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }
    }

}
