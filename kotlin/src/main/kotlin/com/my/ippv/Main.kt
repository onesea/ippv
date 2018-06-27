package com.my.ippv

fun main(args: Array<String>) {
    var ippv = PhoneNr()
    ippv.add("1")
    ippv.add("852")
    var len = ippv.match("85212345")
    println("len=$len")
    len = ippv.match("1805")
    println("len=$len")
}

