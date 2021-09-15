package br.com.estudos.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.estudos.convidados.R
import br.com.estudos.convidados.databinding.FragmentTodosBinding
import br.com.estudos.convidados.service.constants.ConvidadoConstants
import br.com.estudos.convidados.view.adapter.ConvidadoAdapter
import br.com.estudos.convidados.view.listener.ConvidadoListener
import br.com.estudos.convidados.viewmodel.ConvidadosViewModel

class TodosConvidadosFragment : Fragment() {

    private lateinit var mListener : ConvidadoListener
    private lateinit var convidadosViewModel: ConvidadosViewModel
    private val mAdapter: ConvidadoAdapter = ConvidadoAdapter()
    private var _binding: FragmentTodosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        convidadosViewModel =
            ViewModelProvider(this).get(ConvidadosViewModel::class.java)

        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //RecyclerView
        //1 - Obter o recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_todos_convidados)
        //2 - Definir um layout
        recycler.layoutManager = LinearLayoutManager(root.context)
        //3 - Definir um adapter (pega o layout, pega os dados do repositório e junta os dois)
        recycler.adapter = mAdapter

        mListener = object : ConvidadoListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, FormularioDeConvidadoActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(ConvidadoConstants.CONVIDADOID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                convidadosViewModel.delete(id)
               convidadosViewModel.load(ConvidadoConstants.FILTER.EMPTY)
            }
        }

        mAdapter.attachListener(mListener)

        observe()


        return root
    }

    override fun onResume() {
        super.onResume()
        convidadosViewModel.load(ConvidadoConstants.FILTER.EMPTY)
    }

    private fun observe() {
        convidadosViewModel.convidadoList.observe(viewLifecycleOwner, {
            mAdapter.atualizaConvidados(it)

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}