package domain

import java.util.*

class Mistura {
    //Cria uma lista dos sais a partir da classe Sal
    private val sais : MutableList<Sal>

    //Inicializa um arranjo de sais por meio de um contador que é
    //iterado até que sejam percorridos todas as combinações
    init{
        val mistura = arrayOfNulls<Sal>(112)
        var contador = 0;

        for (anion in Sal.Anion.values()){
            for (cation in Sal.Cation.values()){
                mistura[contador] = Sal(anion, cation)
                contador++;
            }
        }
        sais = Arrays.asList(*mistura) //Converte o arranjo em uma lista
        sais.shuffle() //Realiza a mistura das substâncias
    }
    //Mostra os sais que foram formados no console
    fun mostraSais(){
        for ((index, sais) in sais.withIndex()){
            println("$sais")
            if ((index + 1) % 14 == 0) println() //Combina os 14 cátions para cada um dos ânions disponíveis
        }
    }
}