package Introduction

// Hur mycket overhead när jag använder Stringbuilder ist för charArr?
fun interleave(firstS : String, secondS : String) : String{
    val returnString = StringBuilder(firstS.length + secondS.length)
    for(i in 0..Math.max(firstS.length, secondS.length)){
        if(firstS.length>i) returnString.append(firstS[i])
        if(secondS.length>i) returnString.append(secondS[i])
    }
    return returnString.toString()
}

fun main() {
    println(interleave("anna", "patrik"))
}