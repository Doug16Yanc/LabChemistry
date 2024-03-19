package domain.product

import java.time.LocalDate
import java.util.*

data class Produto(
        val numero : Int,
        val nome : String,
        val formula : String,
        val validade : LocalDate,
        var quantidade : Int,
        val fornecedor : String
        )
