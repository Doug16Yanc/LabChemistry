package services

import domain.Departamento
import domain.persons.Aluno
import domain.persons.Professor
import domain.product.Produto
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

        if (escolha == "p"){
            println("Identificador : ")
            var identificador = sc.nextInt()
            sc.nextLine()
            println("Senha : ")
            var senha = sc.nextLine()

            val idEncontrado = professores.stream() //Essa é só uma forma mais Java de se validar um identificador
                .filter(Predicate<Professor> { content: Professor -> content.id == identificador }).findFirst().orElse(null)

            if (idEncontrado != null && senha.equals(idEncontrado.senha)){
                println("Login efetivado com sucesso.\n")
                interageProfessor(idEncontrado)
            }
            else{
                println("Credenciais inválidas.\n")
            }
        }
        else if (escolha == "a"){
            println("Identificador : ")
            var identificador = sc.nextInt()
            sc.nextLine()
            println("Senha : ")
            var senha = sc.nextLine()

            //Essa é a forma mais Kotlin de se validar um identificador
            val idEncontrado = alunos.find {it.id == identificador}

            if (idEncontrado != null && senha.equals(idEncontrado.senha)){
                println("Login efetivado com sucesso.\n")
                interageAluno(idEncontrado)
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

        val deptEncontrado = departamentos.find { it.nome == departamento }

        if (deptEncontrado != null) {

            println("Senha de acesso à plataforma : ")
            var senha = sc.nextLine()
            if (escolha == "p") {
                println("Número de horas dedicadas à instituição : ")
                var horas = sc.nextInt()
                val professor = Professor(id, nome, Departamento(deptEncontrado.numero, deptEncontrado.nome), senha, horas)
                professores.add(professor)
                println("Muito bem, seu número para entrar no sistema é ${professor.id}\n")

            } else if (escolha == "a") {
                println("Número da matrícula : ")
                var matricula = sc.nextInt();
                val aluno = Aluno(id, nome, Departamento(deptEncontrado.numero, deptEncontrado.nome), senha, matricula)
                alunos.add(aluno)
                println("Muito bem, seu número para entrar no sistema é ${aluno.id}\n")

            }
            return true
        }
        else {
            println("Seu departamento ainda não está cadastrado no sistema. Entre em contato com o administrador do sistema.\n")
            return false
        }
    }
    fun interageProfessor(professor : Professor) : Int{
        println("Bem-vindo(a), caríssimo(a) ${professor.nome}, o que desejas?" +
                "1 - Solicitar substâncias\n" +
                "2 - Remover conta\n")
        var opcao = sc.nextInt()

        when(opcao){
            1 -> {
                forneceSubstancias()
            }
            2 -> {
                removeConta()
            }
            else -> {
                println("Opção não disponível.\n")
            }
        }

        return opcao
    }
    fun interageAluno(aluno : Aluno) : Int{
        println("Bem-vindo(a), caríssimo(a) ${aluno.nome}, o que desejas?" +
                "1 - Solicitar substâncias\n" +
                "2 - Remover conta\n")
        var opcao = sc.nextInt()

        when(opcao){
            1 -> {
                forneceSubstancias()
            }
            2 -> {
                removeConta()
            }
            else -> {
                println("Opção não disponível.\n")
            }
        }

        return opcao
    }
    fun forneceSubstancias() : List<Produto>{
        var quantoGlobal : Int = 0
        println("Quantas substâncias você quer remover?\n")
        var quantidade = sc.nextInt()

        Substancial().analisarProdutos()

        val substancias = mutableListOf<Produto>()

        for (i in 0..quantidade){
            println("CAS da substância 1 : ")
            var id = sc.nextInt()

            val subsEncontrada = Substancial().armazenaProdutos().find {it.numero == id}

            if (subsEncontrada != null){
                println("Quantos?")
                var quanto = sc.nextInt()

                quantoGlobal = quanto

                if (subsEncontrada.quantidade >= quanto){
                    println("$quanto unidades de ${subsEncontrada.nome} fornecidos com sucesso.\n")
                    subsEncontrada.quantidade -= quanto
                    substancias.add(subsEncontrada)
                }
                else{
                    println("Quantidade insuficiente em estoque. Se necessário, entre em contato com o administrador.\n")
                }
            }
            else{
                println("CAS de substância inválido.\n")
            }
        }
        println("Substâncias fornecidas:\n")
        for (substancia in substancias){
            println("Nome : ${substancia.nome}\n" +
                    "Quantidade fornecida : $quantoGlobal\n")
        }
        return substancias
    }
    fun removeConta(){

    }
}