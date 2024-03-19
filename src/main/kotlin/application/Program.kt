package application

import domain.Mistura
import services.Gerenciamento
import services.UsoIndividuos
import java.util.*

fun main() {
    println("Seja bem-vindo(a) ao nosso sistema de gerenciamento do nosso laboratório químico.\n")
    interagePrimeiro()
}

fun interagePrimeiro(){
    do{
        println("Quem é você?\n")

        println("G/g - Gerente\n" +
                "P/p - Professor\n" +
                "A/a - Aluno\n" +
                "O/o - Sair do sistema\n")
        var opcao = readLine()?.trim()?.lowercase(Locale.getDefault())

        when(opcao) {
            "g" -> {
                Gerenciamento().validoLogin()
            }

            "p" -> {
                UsoIndividuos.questionaSobreCadastro(opcao)
            }

            "a" -> {

            }

            "o" -> {
                println("Sua despedida é sempre tão desoladora e comovente, espero te ver logo")
                System.exit(0)
            }

            else -> {
                println("")
            }
        }
    } while (true)
}