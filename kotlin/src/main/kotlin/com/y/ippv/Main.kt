package com.y.ippv

import java.util.stream.Stream

fun main(args: Array<String>) {
    if (args.size > 0)
    	println("args size = ${args.size}")
    val ippv = PhoneNr()
    ippv.add("1", "US")
    ippv.add("852", "HK")

    val stream = Stream.of("85212345","1805","850123")
    stream.forEach{ println("$it -> " + ippv.match(it)) }
}

