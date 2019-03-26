package Introduction

import java.lang.Exception
import java.util.concurrent.ThreadLocalRandom

class GuessTheNumber{

    var guessNumber : Int = 0
    var guessAttempts = 0
    var lowerBound = 0
    var upperBound = 0
    var gameShouldEnd = false

    fun createRandomNumber(number : Int){
        upperBound = number
        guessNumber = ThreadLocalRandom.current().nextInt(upperBound)
    }

    fun printGuess(){
        print("Is the number: $guessNumber \n1) Higer \n2) Lower \n3) Correct \n")
    }

    fun takeInput(){
        val input = Integer.parseInt(readLine())

        try {
            when(input){
                1,2 -> calculateNewGuess(input)
                3 -> gameShouldEnd = true
            }
        }
        catch (e : Exception){
            println("Please type a number from 1-3")
        }
    }

    fun calculateNewGuess(guess : Int){
        guessAttempts++

        when(guess){
            1 -> lowerBound = guessNumber
            2 -> upperBound = guessNumber
        }
        guessNumber = (lowerBound + upperBound) / 2
    }

    fun initializeGame(guess : Int = Int.MAX_VALUE){
        createRandomNumber(guess)
        while (!gameShouldEnd){
            printGuess()
            takeInput()
        }
        println("Congratulations it took $guessAttempts attempts")
    }


}

fun main() {
    GuessTheNumber().initializeGame(1000000)
}