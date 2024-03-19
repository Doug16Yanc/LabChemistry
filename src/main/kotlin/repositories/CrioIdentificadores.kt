package repositories

import domain.persons.Aluno
import domain.persons.Professor

interface CrioIdentificadores {
    fun geroId(professores: MutableList<Professor>, alunos: MutableList<Aluno>): Int{
        return 1;
    }
}