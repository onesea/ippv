package com.my.ippv

import java.util.stream.Stream

fun main(args: Array<String>) {
    val ippv = PhoneNr()
    ippv.add("1", "US")
    ippv.add("852", "HK")

    val stream = Stream.of("85212345","1805","850123")
    stream.forEach{ println("$it -> " + ippv.match(it)) }
}

