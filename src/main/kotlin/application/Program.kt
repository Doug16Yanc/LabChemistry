package application

import domain.Mistura
import java.util.*

fun main() {
    println("Seja bem-vindo(a) ao nosso sistema de gerenciamento do nosso laboratório químico.\n")
    interagePrimeiro()
}

fun interagePrimeiro(){
    println("Quem é você?\n")
    println("G/g - Gerente\n" +
            "P/p - Professor\n" +
            "A/a - Aluno\n")
    var opcao = readLine()?.trim()?.lowercase(Locale.getDefault())

    when(opcao){
        "g" -> {

        }
        "p" -> {

        }
        "a" -> {

        }
        else -> {
            println("")
        }
    }
}