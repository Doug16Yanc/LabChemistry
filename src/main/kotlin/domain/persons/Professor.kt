package domain.persons

import domain.Departamento

data class Professor (
    override val id : Int,
    override val nome : String,
    override val departamento: Departamento,
    private val horas : Int
) : Individuo(id, nome, departamento)
