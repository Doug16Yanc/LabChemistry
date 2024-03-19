package application

import domain.Departamento
import domain.persons.Aluno
import domain.persons.Professor
import services.Gerenciamento
import services.UsoIndividuos
import java.util.*

fun main() {
    println("Seja bem-vindo(a) ao nosso sistema de gerenciamento do nosso laboratório químico.\n")
    interagePrimeiro()
}

fun interagePrimeiro(){
    val departamentos : MutableList<Departamento> = ArrayList<Departamento>()
    val professores: MutableList<Professor> = ArrayList<Professor>()
    val alunos: MutableList<Aluno> = ArrayList<Aluno>()
    do{
        println("Quem é você?\n")

        println("G/g - Gerente\n" +
                "P/p - Professor\n" +
                "A/a - Aluno\n" +
                "O/o - Sair do sistema\n")
        var opcao = readLine()?.trim()?.lowercase(Locale.getDefault())

        when(opcao) {
            "g" -> {
                Gerenciamento().validoLogin(departamentos, professores, alunos)
            }

            "p" -> {
                UsoIndividuos.questionaSobreCadastro(opcao, departamentos, professores, alunos)
            }

            "a" -> {
                UsoIndividuos.questionaSobreCadastro(opcao, departamentos, professores, alunos)
            }

            "o" -> {
                println("Sua despedida é sempre tão desoladora e comovente, espero te ver logo")
                System.exit(0)
            }

            else -> {
                println("Opção impossível.\n")
            }
        }
    } while (true)
}