package services

import domain.product.Produto
import utilidades.Util.Companion.imprimeMensagem
import utilidades.Util.Companion.sc
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Substancial {

    private val produtos : MutableList<Produto> = ArrayList<Produto>()

    init {
        produtos.addAll(armazenaProdutos())
    }
    fun armazenaProdutos() : List<Produto>{

       return listOf(
           Produto(1765, "Ácido sulfúrico", "H2SO4", LocalDate.of(2028, 12, 1), 100, "União Química"),
           Produto(1768, "Hidróxido de Potássio", "KOH", LocalDate.of(2028,  5, 9), 80, "ForteX Indústria"),
           Produto(1883, "Nitrato de cálcio", "Ca(OH)2", LocalDate.of(2025,  9, 15), 130, "ForteX Indústria"),
           Produto(1895, "Óxido de ferro (III)", "FE2O3", LocalDate.of(2027,  11, 9), 80, "BASF"),
        )
    }
    fun cadastrarProduto(){
        println("Dê todas as informações solicitadas:")
        println("Número CAS : ")
        var numero = sc.nextInt()

        val numeroExistente = armazenaProdutos().find { it.numero == numero }

        if (numeroExistente == null) {
            sc.nextLine()
            println("Nome : ")
            var nome = sc.nextLine()
            println("Fórmula : ")
            var formula = sc.nextLine()
            println("Informe a data de validade (no formato yyyy-MM-dd): ")
            val data = sc.nextLine()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dataValidade = LocalDate.parse(data, formatter)
            println("Quantidade em estoque : ")
            var quantidade = sc.nextInt()
            sc.nextLine()
            println("Fornecedora : ")
            var fornecedora = sc.nextLine()

            val produto = Produto(numero, nome, formula, dataValidade, quantidade, fornecedora)

            produtos.add(produto)
            println("Produto ${produto.numero}, ${produto.nome} adicionado com sucesso.\n")
        }
        else {
            println("Já há um produto com CAS igual. Trata-se do ${numeroExistente.nome}\n")
        }
    }

    fun analisarProdutos() {
        println("Produtos químicos atualmente em estoque.\n")
        for (produto in produtos){
            imprimeMensagem(
                    "CAS : ${produto.numero}\n" +
                    "Nome : ${produto.nome}\n" +
                    "Fórmula : ${produto.formula}\n" +
                    "Data de validade : ${produto.validade}\n" +
                    "Quantidade atual em estoque : ${produto.quantidade}\n" +
                    "Empresa fornecedora : ${produto.fornecedor}")
        }
    }

    fun removerDoEstoque() {
        println("Digite o CAS do produto:")
        var id = sc.nextInt()

        val produtoEncontrado = armazenaProdutos().find {it.numero == id}

        if (produtoEncontrado != null){
            println("Produto ${produtoEncontrado.nome}, com CAS ${produtoEncontrado.numero} removido com sucesso.\n")
            produtos.remove(produtoEncontrado)
        }
        else{
            println("Produto não encontrado.\n")
        }
    }
}