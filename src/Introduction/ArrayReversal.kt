package Introduction

// Fungerar på Any -> Vill ha de till genersk array
fun reverseArray (arr : Array<Any>) : Array<Any>{
    val returnArray = Array<Any>(size = arr.size){}
    for(elem in 0..arr.size -1){
        returnArray[elem] = arr[(arr.size-1) - elem]
    }
    return returnArray
}

fun reverseIntArray (arrToRev : IntArray) : IntArray{
    val returnArray = IntArray(arrToRev.size)
    for(value in 0..arrToRev.size -1){
        returnArray[value] = arrToRev[(arrToRev.size-1) - value]
    }
    return returnArray
}

fun main() {
    reverseArray(arrayOf(1,2,3,4,5)).forEach { println(it) }
}