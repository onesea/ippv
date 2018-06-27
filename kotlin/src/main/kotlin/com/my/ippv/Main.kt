package com.my.ippv

import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

import java.util.ArrayList

fun main(args: Array<String>) {
    val tasks = ArrayList<Mono<Int>>()
    arrayOf(1,20,3,4,5).forEach { tasks.add(task(it)) }
    var ok = false
    Mono.zip(tasks) { ok = true }
            .subscribeOn(Schedulers.elastic())
            .subscribe { println("all tasks completed!") }

    println("waiting for all tasks to complete ...")
    while (!ok) {
        try {
            Thread.sleep(100)
        } catch (ignored: Exception) {
        }
    }
}

private fun task(i: Int): Mono<Int> {
    println("task$i scheduled")
    return Mono.defer {
        try {
            Thread.sleep(500)
            Mono.just(i)
        } catch (e: Exception) {
            Mono.error<Int>(e)
        }
    }
}

