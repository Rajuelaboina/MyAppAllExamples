package com.task.task.kotlinexample

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FlowEcample {
}
fun sample() : Flow <Int> = flow {
       for (i in 1..3){
           delay(1000)
           emit(i)
       }
}
fun main() = runBlocking<Unit> {
     launch {
         for (k in 1..3){
             println("I am not blocked $k")
             delay(1000)
         }
     }
    sample().collect {value -> println(value) }
}