package com.my.ippv

import java.util.stream.Stream

fun main(args: Array<String>) {
    val ippv = PhoneNr()
    ippv.add("1")
    ippv.add("852")

    val stream = Stream.of("85212345","1805","850123")
    stream.forEach{ println("$it -> " + ippv.match(it)) }
}

