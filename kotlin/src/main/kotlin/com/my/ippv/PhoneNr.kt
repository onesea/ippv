package com.my.ippv

class PhoneNr {
    data class Node(val child: Array<Node?> = Array(10) { null })
    private val root_: Node = Node()

    fun add(cc: String) {
        var node = root_
        cc.map {
            var tmp = node.child[it.toInt() - '0'.toInt()]
            if (tmp == null) {
                tmp = Node()
                node.child[it.toInt() - '0'.toInt()] = tmp;
            }
            node = tmp
        }
    }

    fun match(phone: String): Int {
        var node = root_
        var len = 0
        for (ch in phone) {
            var tmp = node.child[ch.toInt() - '0'.toInt()]
            if (tmp != null) {
                ++len
                node = tmp
            } else {
                break
            }
        }
        return if (node.child.find { it != null } == null) len else -1
    }
}