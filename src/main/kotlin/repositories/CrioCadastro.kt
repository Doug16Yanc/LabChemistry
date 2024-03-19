package repositories

import domain.Departamento
import domain.persons.Aluno
import domain.persons.Professor

interface CrioCadastro {
    fun realizoRegistro(
        escolha: String,
        departamentos: MutableList<Departamento>,
        professores: MutableList<Professor>,
        alunos: MutableList<Aluno>
    ) : Boolean {
        return true
    }
}