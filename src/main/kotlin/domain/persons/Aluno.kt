package domain.persons

import domain.Departamento

data class Aluno (
    override val id : Int,
    override val nome : String,
    override val departamento: Departamento,
    private val matricula : Int
) : Individuo(id, nome, departamento)

