package br.com.estudos.convidados.service.constants

class ConvidadoConstants private constructor(){
    companion object {
        const val CONVIDADOID = "convidadoID"
    }
        object FILTER {
            const val EMPTY = 0
            const val PRESENTE = 1
            const val AUSENTE = 2
        }
}
