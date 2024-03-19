package services

import domain.Departamento
import domain.persons.Aluno
import domain.persons.Professor
import repositories.CrioCadastro
import repositories.CrioIdentificadores
import utilidades.Util.Companion.sc
import java.util.*
import java.util.function.Predicate
import kotlin.random.Random


class UsoIndividuos : CrioCadastro, CrioIdentificadores {


    companion object {

        fun questionaSobreCadastro(
            escolha: String,
            departamentos: MutableList<Departamento>,
            professores: MutableList<Professor>,
            alunos: MutableList<Aluno>
        ) {
            println(
                "Já é cadastrado em nossa plataforma?\n" +
                        "S/s - Sim\n" +
                        "N/n - Não\n"
            )
            var opcao = readLine()?.trim()?.lowercase(Locale.getDefault())

            when (opcao) {
                "s" -> {
                    UsoIndividuos().façoLogin(escolha, professores, alunos)
                }

                "n" -> {
                    UsoIndividuos().realizoRegistro(escolha, departamentos, professores, alunos)
                }

                else -> {
                    println("Opção impossível.\n")
                }
            }
        }
    }

    override fun geroId(professores: MutableList<Professor>, alunos: MutableList<Aluno>): Int {
        var enter: Int
        var help: Boolean

        do {
            enter = Random.nextInt(1000, 100000)
            help = true
            for (i in professores) {
                if (i.id == enter) {
                    help = false
                    break
                }
            }
            for (i in alunos) {
                if (i.id == enter) {
                    help = false
                    break
                }
            }
        } while (!help)

        return enter
    }

    fun façoLogin(escolha: String, professores: MutableList<Professor>, alunos: MutableList<Aluno>, ): Boolean {
        println("Entre com suas credenciais.\n")

        if (escolha.equals("p")){
            println("Identificador : ")
            var identificador = sc.nextInt()
            sc.nextLine()
            println("Senha : ")
            var senha = sc.nextLine()

            val idEncontrado = professores.stream() //Essa é só uma forma mais Java de se validar um identificador
                .filter(Predicate<Professor> { content: Professor -> content.id == identificador }).findFirst().orElse(null)

            if (idEncontrado != null && senha.equals(idEncontrado.senha)){
                println("Login efetivado com sucesso.\n")
            }
            else{
                println("Credenciais inválidas.\n")
            }
        }
        else if (escolha.equals("a")){
            println("Identificador : ")
            var identificador = sc.nextInt()
            sc.nextLine()
            println("Senha : ")
            var senha = sc.nextLine()

            //Essa é a forma mais Kotlin de se validar um identificador
            val idEncontrado = alunos.find {it.id == identificador}

            if (idEncontrado != null && senha.equals(idEncontrado.senha)){
                println("Login efetivado com sucesso.\n")
            }
            else{
                println("Credenciais inválidas.\n")
            }
        }

        return true
    }


    override fun realizoRegistro(
        escolha: String,
        departamentos: MutableList<Departamento>,
        professores: MutableList<Professor>,
        alunos: MutableList<Aluno>
    ) : Boolean {
        println("Realize o preenchimento de acordo com as solicitações feitas.\n")
        var id = geroId(professores, alunos)
        println("Nome : ")
        var nome = sc.nextLine()
        println("Departamento : ")
        var departamento = sc.nextLine().trim().lowercase()

        val deptEncontrado = departamentos.find { it.nome.equals(departamento) }

        if (deptEncontrado != null) {

            println("Senha de acesso à plataforma : ")
            var senha = sc.nextLine()
            if (escolha.equals("p")) {
                println("Número de horas dedicadas à instituição : ")
                var horas = sc.nextInt()
                val professor = Professor(id, nome, Departamento(deptEncontrado.numero, deptEncontrado.nome), senha, horas)
                professores.add(professor)

            } else if (escolha.equals("a")) {
                println("Número da matrícula : ")
                var matricula = sc.nextInt();
                val aluno = Aluno(id, nome, Departamento(deptEncontrado.numero, deptEncontrado.nome), senha, matricula)
                alunos.add(aluno)
            }
            return true
        }
        else {
            println("Seu departamento ainda não está cadastrado no sistema. Entre em contato com o administrador do sistema.\n")
            return false
        }
    }
}