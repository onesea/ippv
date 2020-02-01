package com.my.ippv;

public class PhoneNo {
    class Node {
        public Node[] child;
        Node() {
            child = new Node[10];
            for (int i = 0; i < child.length; ++i)
                child[i] = null;
        }
    }
    Node root = new Node();

    public void add(String code) {
        Node node = root;
        for (int i = 0; i < code.length(); ++i) {
            char ch = code.charAt(i);
            Node tmp = node.child[(int)ch - (int)'0'];
            if (tmp == null) {
                tmp = new Node();
                node.child[(int)ch - (int)'0'] = tmp;
            }
            node = tmp;
        }
    }

    public int match(String phone) {
        Node node = root;
        int len = 0;
        for (int i = 0; i < phone.length(); ++i) {
            char ch = phone.charAt(i);
            Node tmp = node.child[(int)ch - (int)'0'];
            if (tmp == null)
                break;
            node = tmp;
            ++len;
        }
        for (int i = 0; i < 10; ++i) {
            if (node.child[i] != null) {
                len = -1;
                break;
            }
        }
        return len;
    }
}
