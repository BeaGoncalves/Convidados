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
import br.com.estudos.convidados.service.constants.ConvidadoConstants

class FormularioDeConvidadoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel : FormularioDeConvidadoViewModel
    private lateinit var binding: ActivityFormularioDeConvidadoBinding
    private var mConvidadoId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_de_convidado)


        mViewModel = ViewModelProvider(this).get(FormularioDeConvidadoViewModel::class.java)
        binding = ActivityFormularioDeConvidadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        observe()
        loadData()

        binding.radioPresente.isChecked = true
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mConvidadoId = bundle.getInt(ConvidadoConstants.CONVIDADOID)
            mViewModel.load(mConvidadoId)
        }
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.button_salvar) {

            val name = binding.editName.text.toString()
            val presence = binding.radioPresente.isChecked

            mViewModel.save(mConvidadoId, name, presence)
        }
    }
    private fun observe() {
        mViewModel.salvaConvidado.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_LONG).show()
            }
            finish()
        })

        mViewModel.convidado.observe(this, Observer {
            binding.editName.setText (it.nome)
            if (it.presence) {
                binding.radioPresente.isChecked = true
            } else {
                binding.radioAusente.isChecked = true
            }
        })
    }

    private fun setListeners() {
        binding.buttonSalvar.setOnClickListener(this)
    }

}