package services

import domain.persons.Gerente
import repositories.FazLogin

class Gerenciamento : FazLogin{
    override fun validoLogin(): Boolean {
        var saiu = false
        val gerente = Gerente("Douglas", "doug@gmail.com", "d", "1609")

        println("Bem-vindo, caro gerente, realize entrada no sistema por meio de suas credenciais.\n")
        println("Usuário :  ")
        var login = readLine();
        println("Senha : ")
        var senha = readLine()

        if (login.equals(gerente.login) && senha.equals(gerente.senha)) {
            println("Login realizado com sucesso.\n")
        } else {
            println("Credenciais inválidas. Realizando retorno ao início.\n")
            saiu = true
        }
        return saiu;
    }
}