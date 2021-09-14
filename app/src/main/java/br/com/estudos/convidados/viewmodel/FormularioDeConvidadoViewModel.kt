package br.com.estudos.convidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.estudos.convidados.service.model.ConvidadoModel
import br.com.estudos.convidados.service.repository.ConvidadoRepository

class FormularioDeConvidadoViewModel : ViewModel() {

    private val mConvidadoRepository: ConvidadoRepository = ConvidadoRepository()

    private var mSalvaConvidado = MutableLiveData<Boolean>()
    val salvaConvidado: LiveData<Boolean> = mSalvaConvidado

    fun save(nome: String, presence: Boolean) {
        val convidado = ConvidadoModel(nome, presence)
        mConvidadoRepository.save(convidado)
    }

}