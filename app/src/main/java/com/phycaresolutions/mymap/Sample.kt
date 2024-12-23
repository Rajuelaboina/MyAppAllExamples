package com.phycaresolutions.mymap

import kotlin.Result

class Sample : OnItemClickListener {
    override fun onItemClick() {
        println("this is interface")
    }

}
fun main(){
    val list:List<Int> = listOf(1,2,3)
    println(list)
    val ls:MutableList<Int> = mutableListOf(1,2,3)
    println(ls)
    ls[0] = 10
    println(ls)
    val sample = Sample();
    sample.onItemClick()



    // higher-order function
    val ev:(Int)->Unit = {x:Int-> print(x) }
    //even(2,ev) // this is first way
    even(2){x:Int-> if (x%2 == 0) println("$x  is Even Number") else println("$x  is Not Even Number") } // second way
   /* val sum = { a: Int, b: Int -> a + b } // Lambda expression
    val result = sum(3, 4) // Invoking the lambda expression
    println(result) // Output: 7*/

   // val add = {x:Int,y:Int-> x+y}
    // result22 = add(10,20)
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
sealed class Result20{
   data class Success(val data:String) : Result20()
  data class Error(val message:String ) : Result20()
   object Loding : Result20()

}
fun progressResult(result20: Result20){
    when(result20){
        is Result20.Success ->{}
        is Result20.Error ->{}
        is Result20.Loding ->{}
        else -> {}
    }
}