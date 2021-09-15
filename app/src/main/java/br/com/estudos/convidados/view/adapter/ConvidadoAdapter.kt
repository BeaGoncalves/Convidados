package br.com.estudos.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.estudos.convidados.R
import br.com.estudos.convidados.service.model.ConvidadoModel
import br.com.estudos.convidados.view.listener.ConvidadoListener
import br.com.estudos.convidados.view.viewholder.ConvidadoViewHolder

class ConvidadoAdapter : RecyclerView.Adapter<ConvidadoViewHolder>() {

    private var mConvidadoList : List<ConvidadoModel> = arrayListOf()
    private lateinit var mListener: ConvidadoListener

    // criar o layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvidadoViewHolder {

        val item = LayoutInflater.from(parent.context).inflate(R.layout.linha_convidado, parent, false)
        return ConvidadoViewHolder(item, mListener)

    }
    //passar a lista de convidados, aquantidade de registro
    override fun getItemCount(): Int {
        return mConvidadoList.count()

    }

    override fun onBindViewHolder(holder: ConvidadoViewHolder, position: Int) {
        holder.bind(mConvidadoList[position])
    }

    fun atualizaConvidados(list: List<ConvidadoModel>) {
        mConvidadoList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener : ConvidadoListener ) {
        mListener = listener
    }

}