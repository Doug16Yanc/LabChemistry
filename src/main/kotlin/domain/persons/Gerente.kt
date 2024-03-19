package domain.persons

data class Gerente(
    val nome : String,
    private val email : String,
    val login : String,
    val senha : String
)
