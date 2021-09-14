package br.com.estudos.convidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodosConvidadosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Essa é a tela de todos os convidados"
    }
    val text: LiveData<String> = _text
}