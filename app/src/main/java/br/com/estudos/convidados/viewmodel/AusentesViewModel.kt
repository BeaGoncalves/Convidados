package br.com.estudos.convidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AusentesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Essa é a tela dos convidados ausentes"
    }
    val text: LiveData<String> = _text
}