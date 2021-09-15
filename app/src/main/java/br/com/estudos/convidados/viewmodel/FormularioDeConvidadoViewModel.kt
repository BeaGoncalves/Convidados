package br.com.estudos.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.estudos.convidados.service.model.ConvidadoModel
import br.com.estudos.convidados.service.repository.ConvidadoRepository

class FormularioDeConvidadoViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mConvidadoRepository: ConvidadoRepository = ConvidadoRepository.getInstance(mContext)

    private var mSalvaConvidado = MutableLiveData<Boolean>()
    val salvaConvidado: LiveData<Boolean> = mSalvaConvidado

    private var mConvidado = MutableLiveData<ConvidadoModel>()
    val convidado: LiveData<ConvidadoModel> = mConvidado

    fun save(id: Int, nome: String, presence: Boolean) {
        val convidado = ConvidadoModel(id, nome, presence)

        if (id == 0) {
            mSalvaConvidado.value = mConvidadoRepository.save(convidado)
        } else {
            mSalvaConvidado.value = mConvidadoRepository.update(convidado)
        }
    }

    fun load(id: Int) {
        mConvidado.value = mConvidadoRepository.get(id)
    }

}