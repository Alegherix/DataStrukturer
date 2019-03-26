package Introduction

fun interleave(firstS : String, secondS : String) : String{
    val returnStrng = StringBuilder()
    for(i in 0..Math.max(firstS.length, secondS.length)){
        if(firstS.length>i)
            returnStrng.append(firstS.get(i))
        if(secondS.length>i)
            returnStrng.append(secondS.get(i))
    }
    return returnStrng.toString()
}

fun main() {
    println(interleave("anna", "patrik"))
}