package com.phycaresolutions.mylibrary

import java.sql.DriverManager.println

class Test {
   fun main(args:Array<String>){
       val sum = {a:Int,b:Int -> a+b}
       val result = sum(10,20)
       println(""+result)
    }
}