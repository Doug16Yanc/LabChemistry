package domain.persons

import domain.Departamento

data class Professor (
    override val id : Int,
    override val nome : String,
    override val departamento: Departamento,
    override val senha : String,
    private val horas : Int
) : Individuo(id, nome, departamento, senha)
