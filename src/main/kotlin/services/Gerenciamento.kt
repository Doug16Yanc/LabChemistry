package services

import domain.Departamento
import domain.persons.Aluno
import domain.persons.Gerente
import domain.persons.Professor
import utilidades.Util.Companion.sc

class Gerenciamento {
  fun validoLogin(
      departamentos: MutableList<Departamento>,
      professores: MutableList<Professor>,
      alunos: MutableList<Aluno>
  ): Boolean {
        var saiu = false
        val gerente = Gerente("Douglas", "doug@gmail.com", "d", "1609")

        println("Bem-vindo, caro gerente, realize entrada no sistema por meio de suas credenciais.\n")
        println("Usuário :  ")
        var login = readLine();
        println("Senha : ")
        var senha = readLine()

        if (login.equals(gerente.login) && senha.equals(gerente.senha)) {
            println("Login realizado com sucesso.\n")
            interageGerente(gerente, departamentos, professores, alunos)
        } else {
            println("Credenciais inválidas. Realizando retorno ao início.\n")
            saiu = true
        }

        return saiu;
    }
    fun interageGerente(
        gerente: Gerente,
        departamentos: MutableList<Departamento>,
        professores: MutableList<Professor>,
        alunos: MutableList<Aluno>
    ){
        var saiu = false
        do {
            println("Bem-vindo(a), caríssimo gerente ${gerente.nome}\n")
            println(
                "O que há de gerenciável para hoje?\n\n" +
                        "       GESTÃO HUMANA       \n\n" +
                        "       1 - Departamentos \n\n" +
                        "       2 - Indivíduos  \n\n" +
                        "       GESTÃO QUÍMICA  \n\n" +
                        "       3 - Adicionar substância\n\n" +
                        "       4 - Analisar substâncias\n\n" +
                        "       5 - Remover do estoque\n\n" +
                        "       GESTÃO SISTEMÁTICA  \n\n" +
                        "       6 - Retornar ao início\n\n"
            )
            var opcao = sc.nextInt()

            when (opcao) {
                1 -> {
                    gerenciaDepartamentos(departamentos)
                }

                2 -> {
                    removerPessoas(professores, alunos)

                }

                3 -> {
                    Substancial().cadastrarProduto()
                }

                4 -> {
                    Substancial().analisarProdutos()
                }

                5 -> {
                    Substancial().removerDoEstoque()
                }

                6 -> {
                    println("Até logo, meu amigo!\n")
                    saiu = true
                    break
                }
                else -> {
                    println("Opção impossível.\n")
                }
            }
        } while (true)
    }
    fun gerenciaDepartamentos(departamentos: MutableList<Departamento>): Int {
        println("1 - Cadastrar departamentos.\n" +
                "2 - Remover departamentos.\n")
        var opcao = sc.nextInt()
        when(opcao){
            1 -> cadastraDepartamentos(departamentos)
            2 -> removeDepartamentos(departamentos)
            else -> println("Opcão impossível.\n")
        }
        return opcao
    }
    fun cadastraDepartamentos(departamentos: MutableList<Departamento>) {
        println("Identificador do departamento : ")
        var id = sc.nextInt()
        sc.nextLine()
        println("Nome : ")
        var nome = sc.nextLine().trim().lowercase()

        val departamento = Departamento(id, nome)
        println("Departamento $id, de $nome cadastrado com sucesso.\n")
        departamentos.add(departamento)
    }
    fun removeDepartamentos(departamentos: MutableList<Departamento>): Boolean {
        println("Identificador : ")
        var id = sc.nextInt()

        val deptEncontrado = departamentos.find {it.numero == id}
        if (deptEncontrado != null) {
            println("Departamento $id, de ${deptEncontrado.nome} cadastrado com sucesso.\n")
            departamentos.removeAt(id)
            return true
        }
        else{
            println("Impossível realizar a remoção, pois não o número não é reconhecido.\n")
            return false
        }

    }
    fun removerPessoas(professores: MutableList<Professor>, alunos: MutableList<Aluno>): Boolean{
        println("Identificador do indivíduo : ")
        var id = sc.nextInt()

        val profEncontrado = professores.find {it.id == id}
        val alunoEncontrado = alunos.find {it.id == id}

        if (profEncontrado != null){
            println("Motivo da remoção de ${profEncontrado.nome}?")
            var motivo = sc.nextLine()
            professores.remove(profEncontrado)
        }
        if (alunoEncontrado != null){
            println("Motivo da remoção de ${alunoEncontrado.nome}?")
            var motivo = sc.nextLine()
            alunos.remove(alunoEncontrado)
        }
        return true
    }
}