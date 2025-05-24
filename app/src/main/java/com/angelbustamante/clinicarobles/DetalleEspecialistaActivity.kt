package com.angelbustamante.clinicarobles

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.angelbustamante.clinicarobles.databinding.ActivityDetalleEspecialistaBinding
import com.angelbustamante.clinicarobles.model.Especialidad

class DetalleEspecialistaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleEspecialistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialización CORRECTA del binding
        binding = ActivityDetalleEspecialistaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // Establecer la vista raíz del binding

        val especialidad = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("ESPECIALIDAD", Especialidad::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("ESPECIALIDAD")
        }

        especialidad?.let {  // Corregido: 'Let' debe ser 'let' en minúscula
            binding.tvNombreEspecialidad.text = it.nombre
            binding.tvNombreMedico.text = it.medicoResponsable
            binding.tvHorario.text = "Horario: ${it.horario}"  // Corregido: $fit.horario! -> ${it.horario}
            binding.tvDescripcion.text = "Descripción detallada de la especialidad"  // Corregido: Description -> Descripcion
        }

        binding.btnSepararCita.setOnClickListener {
            especialidad?.let { esp ->  // Manejo seguro de nulos
                mostrarDialogoConfirmacion(esp)
            } ?: run {
                Toast.makeText(this, "Error: No se encontró información del especialista", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun mostrarDialogoConfirmacion(especialidad: Especialidad) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar cita")
            .setMessage("¿Desea separar cita con ${especialidad.medicoResponsable} para ${especialidad.nombre}?")
            .setPositiveButton("Confirmar") { dialog, which ->
                // Lógica cuando el usuario confirma
                mostrarMensajeExito(especialidad)
            }
            .setNegativeButton("Cancelar", null) // No hacer nada al cancelar
            .create()
            .show()
    }

    private fun mostrarMensajeExito(especialidad: Especialidad) {
        Toast.makeText(
            this,
            "Cita separada con éxito con ${especialidad.medicoResponsable}",
            Toast.LENGTH_LONG
        ).show()
    }
}