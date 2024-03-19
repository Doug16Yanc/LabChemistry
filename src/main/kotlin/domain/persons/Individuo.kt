package domain.persons

import domain.Departamento

open class Individuo(
    open val id : Int,
    open val nome : String,
    open val departamento : Departamento
)