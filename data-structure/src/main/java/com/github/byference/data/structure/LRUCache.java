package com.github.byference.data.structure;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int value = obj.get(key);
 * obj.put(key,value);
 *
 * @author byference
 * @since 2022/8/22
 */
class LRUCache {

    int capacity;
    int size = 0;
    Node head = new Node(-99, -99);
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node current = head.next;
        while (current != null) {
            if (current.key == key) {
                int record = current.val;
                // 需要返回val，并且将node放到tail
                setToTail(current);
                return record;
            }
            current = current.next;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node newNode = new Node(key, value);

        if (capacity == 1) {
            newNode.prev = head;
            head.next = newNode;
            tail = newNode;
            return;
        }

        // update
        if (update(key, value)) {
            return;
        }

        // 判断是否超出容量
        if (size > 0 && (size + 1) > capacity) {
            Node first = head.next;
            Node newFirst = first.next;
            newFirst.prev = head;
            head.next = newFirst;
            size--;
        }

        // 首次put
        if (head.next == null) {
            newNode.prev = head;
            head.next = newNode;
            tail = newNode;
        } else {
            // 先不考虑数据相等替换的问题
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        resetTail();
    }


    public boolean update(int key, int value) {
        Node current = head.next;
        while (current != null) {
            if (current.key == key) {
                current.val = value;
                setToTail(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void resetTail() {
        tail.next = null;
    }

    public void setToTail(Node current) {
        if (current == tail) {
            return;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;

        tail.next = current;
        current.prev = tail;
        tail = current;

        resetTail();
    }

    class Node {
        Node prev;
        Node next;
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}