package ru.amalkoott.kotlin_example

import android.util.Log
import ru.amalkoott.core.utils.Constant.KOTLIN_TASK
import ru.amalkoott.core.utils.findInt
import ru.amalkoott.core.utils.shaker

fun findInt(){
    val list : List<Any> = listOf(1f,Exception(),1.0,666,"one",777,'o', arrayListOf(0))
    val listWithoutInt : List<Any> = listOf(6.0,6.0,6.0,'f',"six", emptyList<String>())
    try {
        Log.d(KOTLIN_TASK,"founded Int value(s): ${list.findInt()} in $list")
        Log.d(KOTLIN_TASK,"founded Int value(s): ${listWithoutInt.findInt()} in $listWithoutInt")
    }
    catch (e: Exception){
        e.printStackTrace()
    }
    // hjfejflakmfewjfp
}

fun shakerSort(): List<Int?>?{
    val list : List<Int?> = listOf(54,null,1234,65,65,null,0,-432,)
    val sorted = list.shaker()
    Log.d(KOTLIN_TASK,"sorted list with values: $sorted")
    return sorted
}
fun shakerSortWithNull(): List<Int?>?{
    val nullList: List<Int?>? = null
    val sorted = nullList.shaker()
    Log.d(KOTLIN_TASK,"sorted null list: $sorted")
    return sorted
}