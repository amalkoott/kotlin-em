package ru.amalkoott.core.utils

fun List<Any>.findInt(): List<Int> = buildList{
    for(item in this@findInt){
        if (item is Int) add(item)
    }
}

fun List<Int?>?.shaker(): List<Int?>{
    if (this == null) return emptyList()

    val sorted = this.toMutableList()
    var left = 0
    var right = this.size - 1

    while (left <= right) {
        for (i in left until right) {
            if(sorted[i] == null || sorted[i + 1] != null && sorted[i]!! > sorted[i + 1]!!){
                sorted[i] = sorted[i + 1].also { sorted[i + 1] = sorted[i] }
            }
        }
        right--

        for (i in right downTo left + 1) {
            if(sorted[i - 1] == null || sorted[i] != null && sorted[i - 1]!! > sorted[i]!!){
                sorted[i - 1] = sorted[i].also { sorted[i] = sorted[i - 1] }
            }
        }
        left++
    }
    return sorted.toList()
}