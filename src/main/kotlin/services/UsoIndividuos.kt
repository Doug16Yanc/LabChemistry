package services

import domain.Departamento
import domain.persons.Individuo
import repositories.CrioCadastro
import repositories.CrioIdentificadores
import repositories.FazLogin
import utilidades.Util.Companion.sc
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class UsoIndividuos : FazLogin, CrioCadastro, CrioIdentificadores {
    val individuals : MutableList<Individuo> = ArrayList<Individuo>()
    companion object {

        fun questionaSobreCadastro(escolha : String) {
            println(
                "Já é cadastrado em nossa plataforma?\n" +
                        "S/s - Sim\n" +
                        "N/n - Não\n"
            )
            var opcao = readLine()?.trim()?.lowercase(Locale.getDefault())

            when (opcao) {
                "s" -> {
                    UsoIndividuos().validoLogin()
                }

                "n" -> {
                    UsoIndividuos().realizoRegistro(escolha)
                }

                else -> {
                    println("Opção impossível.\n")
                }
            }
        }
    }

    override fun geroId(): Int {
        var enter: Int
        var help: Boolean

        do {
            enter = Random.nextInt(1000, 100000)
            help = true
            for (i in individuals) {
                if (i.id == enter) {
                    help = false
                    break
                }
            }
        } while (!help)

        return enter
    }

    override fun validoLogin(): Boolean {
        println("")
        return true
    }

    override fun realizoRegistro(escolha : String) {
        println("Realize o preenchimento de acordo com as solicitações feitas.\n")
        var id = geroId()
        println("Nome : ")
        var nome = sc.nextLine()
        println("Departamento : ")
        var departamento = sc.nextLine().trim().lowercase()
        if (escolha.equals("p")){
            println("Número de horas dedicadas à instituição : ")
            var horas = sc.nextInt()
        }
        else if (escolha.equals("a")){
            println("Número da matrícula : ")
            var matricula = sc.nextInt();
        }
        val individuo = Individuo(id, nome, Departamento(1, departamento))

        individuals.add(individuo)
    }
}