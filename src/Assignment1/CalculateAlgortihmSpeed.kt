package Assignment1

val inputvalues = listOf(100, 101, 200, 10000)


fun logTwoN(){
    // Sista värdet blir skummt, why?
    for(value in inputvalues)
        println(kotlin.math.log2(value*1.0) * value)

}

fun nSpeed(){

}

fun nLogTwoN(){
    for (value in inputvalues){
        println(kotlin.math.log2(value*1.0) * value * value)
    }
}

fun squared(){
    for(value in inputvalues){

    }
}

fun main() {
    nLogTwoN()
}