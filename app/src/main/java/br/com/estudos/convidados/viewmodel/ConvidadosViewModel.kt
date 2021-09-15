package br.com.estudos.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.estudos.convidados.service.constants.ConvidadoConstants
import br.com.estudos.convidados.service.model.ConvidadoModel
import br.com.estudos.convidados.service.repository.ConvidadoRepository

class ConvidadosViewModel(application: Application) : AndroidViewModel(application) {

    private val mConvidadoRepository = ConvidadoRepository.getInstance(application.applicationContext)

    private val _mConvidadoList = MutableLiveData<List<ConvidadoModel>>()
    val convidadoList: LiveData<List<ConvidadoModel>> = _mConvidadoList

    fun load(filter: Int) {

        if (filter == ConvidadoConstants.FILTER.EMPTY) {
        _mConvidadoList.value = mConvidadoRepository.getTodosConvidados()
        } else if (filter == ConvidadoConstants.FILTER.PRESENTE) {
        _mConvidadoList.value = mConvidadoRepository.getPresente()
        } else
        _mConvidadoList.value = mConvidadoRepository.getAusente()
    }

    fun delete(id: Int) {
        mConvidadoRepository.delete(id)

    }
}