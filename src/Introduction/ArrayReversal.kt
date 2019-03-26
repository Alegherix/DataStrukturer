package Introduction

// Fungerar på Any -> Vill ha de till genersk array
// Samma problem som hänvisas till i DynArray?
fun reverseArray (arr : Array<Any>) : Array<Any>{
    val returnArray = Array<Any>(size = arr.size){}
    for(elem in 0..arr.size -1){
        returnArray[elem] = arr[(arr.size-1) - elem]
    }
    return returnArray
}

fun main() {
    reverseArray(arrayOf(1,2,3,4,5)).forEach { println(it) }
}