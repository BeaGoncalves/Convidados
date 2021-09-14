package br.com.estudos.convidados.service.repository

import br.com.estudos.convidados.service.model.ConvidadoModel

class ConvidadoRepository {


    fun todosConvidados() : List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return list
    }

    fun getPresente() : List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return list
    }

    fun getAusente() : List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return list
    }
    fun save(convidado: ConvidadoModel) {

    }
    fun update(convidado: ConvidadoModel) {

    }
    fun delete(convidado: ConvidadoModel) {

    }
}
