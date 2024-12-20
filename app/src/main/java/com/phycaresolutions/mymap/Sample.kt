package com.phycaresolutions.mymap

class Sample : OnItemClickListener {
    override fun onItemClick() {
        println("this is interface")
    }

}
fun main(){
    val sample = Sample();
    sample.onItemClick()


    // higher-order function
    val ev:(Int)->Unit = {x:Int-> print(x) }
    //even(2,ev) // this is first way
    even(2){x:Int-> if (x%2 == 0) println("$x  is Even Number") else println("$x  is Not Even Number") } // second way
   // val add = {x:Int,y:Int-> x+y}
   // calculate(10,20,add)
    // OR
   // calculate(10,20){x:Int,y:Int->  println(x+y)}
  val result = calculate(10,20,::subtract)
  println(result)
}
fun add(x:Int,y:Int) = x+y

fun subtract(x:Int,y:Int): Int {
   return x-y
}
fun mul(x:Int,y:Int):Int{
    return x*y
}
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int) :Int{
   return operation(x,y)
}

fun even(x:Int,operation:(Int)->Unit){
    operation(x)
}
// Delegates
interface OnItemClickListener{
    fun onItemClick()
}
abstract class onItemLongClickLinstener{
    abstract fun  onLongClick()
}