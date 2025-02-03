package com.phycaresolutions.mymap.ktexamples

class Demo {
}
fun main(){
    val result:(Int,Int)-> Int ={x,y-> x+y}
    sum(10,20,result)
     sum(10,20){x,y->x+y}
     evenNu(2){ if (it%2==0)print("even") else println("odd") }

}
fun evenNu(x:Int, operation: (Int) -> Unit){
    operation(x)
}
fun sum(x:Int,y:Int,operation:(Int,Int)->Int){
     operation(x,y)
}