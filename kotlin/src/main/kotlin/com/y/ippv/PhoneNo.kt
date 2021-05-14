package com.y.ippv

class PhoneNr {
    data class Node(val iso: String?, val child: Array<Node?> = Array(10) { null })
    private val root_: Node = Node(null)

    fun add(cc: String, iso: String) {
        var node = root_
        cc.mapIndexedNotNull { i: Int, c: Char ->
            val index = c.code - '0'.code
            var tmp = node.child[index]
            if (tmp == null) {
                tmp = Node(if (i == cc.length - 1) iso else null)
                node.child[index] = tmp
            }
	    node = tmp
	    null
        }
    }

    fun match(phone: String): Result {
        var node: Node? = root_
	var len = 0
        var res = Result()
        for (ch in phone) {
            node = node!!.child[ch.code - '0'.code]
            if (node == null) {
		    break;
	    }
	    len++
	    if (node.iso != null) {
		    res.iso = node.iso
		    if (res.cc == 0)
		    	res.cc = len
		    else if (res.ld == 0)
		    	res.ld = len
		    else 
		    	res.ac = len
	    }
        }

        return res
    }
}

data class Result(var cc: Int = 0, var ld: Int = 0, var ac: Int = 0, var iso: String? = null)
