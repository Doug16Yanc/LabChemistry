package domain

//Define escopo da classe e seus atributos
class Sal(
    private val anion : Anion,
    private val cation : Cation
) {
//Preenche as enumerações com os valores a serem atribuídos às instâncias
    enum class Anion { nitrato, clorato, acetato, cloreto, brometo, iodeto, sulfato, sulfeto }
    enum class Cation { lítio, sódio, potássio, rubídio, césio, magnésio, cálcio, estrôncio, bário, prata, mercúrio, chumbo, cádmio, amônio }

    //Retorna a concatenação de strings a ser mostrada no console
    override fun toString(): String {
        return "$anion" + " de " + "$cation"
    }
}