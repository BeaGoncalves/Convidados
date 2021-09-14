package br.com.estudos.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.estudos.convidados.viewmodel.FormularioDeConvidadoViewModel
import br.com.estudos.convidados.R
import br.com.estudos.convidados.databinding.ActivityFormularioDeConvidadoBinding

class FormularioDeConvidadoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel : FormularioDeConvidadoViewModel
    private lateinit var binding: ActivityFormularioDeConvidadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_de_convidado)


        mViewModel = ViewModelProvider(this).get(FormularioDeConvidadoViewModel::class.java)
        binding = ActivityFormularioDeConvidadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.button_salvar) {

            val name = binding.editName.text.toString()
            val presence = binding.radioPresente.isChecked

            mViewModel.save(name, presence)
        }
    }
    private fun observe() {
        mViewModel.salvaConvidado.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setListeners() {
        binding.buttonSalvar.setOnClickListener(this)
    }

}