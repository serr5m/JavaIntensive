package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionList {

    private Node head;
    private Node tail;
    private int size = 0;

    public TransactionsLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    Transaction getHeadTransaction() {
        return head.transaction;
    }

    Transaction getTailTransaction() {
        return tail.transaction;
    }

    int getSize() {
        return size;
    }

    public static class Node {
        Node(Transaction transaction) {
            this.transaction = transaction;
            this.prev = null;
            this.next = null;
        }

        Transaction transaction;
        Node prev;
        Node next;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node newNode = new Node(transaction);
        if (size == 0) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public void removeTransaction(UUID id) {
        Node curr = head;
        while (curr != null) {
            if (curr.transaction.getIdentifier() == id) {
                if (curr == head) {
                    head = curr.next;
                    if (curr.next != null) {
                        head.prev = null;
                    }
                } else if (curr == tail) {
                    tail = curr.prev;
                    tail.next = null;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }
                size--;
                return;
            }
            curr = curr.next;
        }
    }

    @Override
    public Transaction[] toArray() {
        Node curr = head;
        Transaction[] arrayTransaction = new Transaction[size];
        int i = 0;
        while (curr != null) {
            arrayTransaction[i] = curr.transaction;
            i++;
            curr = curr.next;
        }
        return arrayTransaction;
    }
}

