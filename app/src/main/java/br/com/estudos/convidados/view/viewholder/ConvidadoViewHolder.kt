package br.com.estudos.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import br.com.estudos.convidados.R
import br.com.estudos.convidados.service.model.ConvidadoModel
import br.com.estudos.convidados.view.listener.ConvidadoListener

class ConvidadoViewHolder(itemView: View, val listener: ConvidadoListener) : RecyclerView.ViewHolder(itemView) {
    fun bind(convidado: ConvidadoModel) {

       val textName =  itemView.findViewById<TextView>(R.id.item_text_name)
        textName.text = convidado.nome

        textName.setOnClickListener {
            listener.onClick(convidado.id)
        }

        textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { dialog, which ->
                    listener.onDelete(convidado.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()

            true
        }
    }
}