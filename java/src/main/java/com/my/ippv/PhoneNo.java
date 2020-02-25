package com.my.ippv;

public class PhoneNo {
    class Node {
	public String iso_;
        public Node[] child_;
        Node() {
	    iso_ = null;
            child_ = new Node[10];
            for (int i = 0; i < child_.length; ++i)
                child_[i] = null;
        }
    }

    Node root_ = new Node();

    public void add(String code, String iso) {
        var node = root_;
        for (int i = 0; i < code.length(); ++i) {
            int index = (int)code.charAt(i) - (int)'0';
            var tmp = node.child_[index];
            if (tmp == null) {
                tmp = new Node();
                node.child_[index] = tmp;
            }
	    if (i == code.length() - 1) {
	        tmp.iso_ = iso;
	    }
            node = tmp;
        }
    }

    public Result match(String phone) {
        var node = root_;
        var res = new Result();
        for (int i = 0; i < phone.length(); ++i) {
            int index = (int)phone.charAt(i) - (int)'0';
            node = node.child_[index];
            if (node == null)
                break;
	    if (node.iso_ != null) {
		    res.iso_ = node.iso_;
		    if (res.cc_ == 0)
			    res.cc_ = i + 1;
		    else if (res.ld_ == 0)
			    res.ld_ = i + 1;
		    else res.ac_ = i + 1;
	    }
        }

        return res;
    }
}
