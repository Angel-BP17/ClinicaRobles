package com.angelbustamante.clinicarobles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angelbustamante.clinicarobles.R
import com.angelbustamante.clinicarobles.model.Especialidad
import android.content.Intent
import com.angelbustamante.clinicarobles.DetalleEspecialistaActivity

class EspecialidadAdapter(private var especialidades: List<Especialidad>) :
    RecyclerView.Adapter<EspecialidadAdapter.EspecialidadViewHolder>() {

    class EspecialidadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivEspecialidad: ImageView = itemView.findViewById(R.id.ivEspecialidad)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvMedico: TextView = itemView.findViewById(R.id.tvMedico)
        val tvHorario: TextView = itemView.findViewById(R.id.tvHorario)
        val btnVerDetalles: Button = itemView.findViewById(R.id.btnVerDetalles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspecialidadViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_especialidad, parent, false)
        return EspecialidadViewHolder(view)
    }

    override fun onBindViewHolder(holder: EspecialidadViewHolder, position: Int) {
        val especialidad = especialidades[position]

        holder.ivEspecialidad.setImageResource(especialidad.imagenId)
        holder.tvNombre.text = especialidad.nombre
        holder.tvMedico.text = "Médico: ${especialidad.medicoResponsable}"
        holder.tvHorario.text = "Horario: ${especialidad.horario}"

        holder.btnVerDetalles.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleEspecialistaActivity::class.java).apply {
                putExtra("ESPECIALIDAD", especialidad)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = especialidades.size

    fun actualizarLista(nuevaLista: List<Especialidad>) {
        especialidades = nuevaLista
        notifyDataSetChanged()
    }
}