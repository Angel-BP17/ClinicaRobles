package com.angelbustamante.clinicarobles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.angelbustamante.clinicarobles.adapters.EspecialidadAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angelbustamante.clinicarobles.model.Especialidad

class MainActivity : AppCompatActivity() {

    private lateinit var especialidadAdapter: EspecialidadAdapter
    private lateinit var listaOriginal: List<Especialidad>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configurar RecyclerView
        val rvEspecialidades = findViewById<RecyclerView>(R.id.rvEspecialidades)
        rvEspecialidades.layoutManager = LinearLayoutManager(this)

        // Inicializar lista de especialidades
        listaOriginal = obtenerListaEspecialidades()
        especialidadAdapter = EspecialidadAdapter(listaOriginal)
        rvEspecialidades.adapter = especialidadAdapter

        // Configurar SearchView de manera segura
        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filtrarEspecialistas(it) }
                return true
            }
        })
    }

    private fun obtenerListaEspecialidades(): List<Especialidad> {
        return listOf(
            Especialidad(
                "Traumatología",
                "Dr. Alberto Garcia Cerna",
                "L-V 8:00-18:00",
                R.drawable.ic_medical_services
            ),
            Especialidad(
                "Cardiología",
                "Dr. Roberto Chavesta Bernal",
                "L-V 9:00-17:00",
                R.drawable.ic_medical_services
            ),
            Especialidad(
                "Cirugía Cardiovascular",
                "Dr. Romel Zamuno Suya",
                "L-J 10:00-15:00",
                R.drawable.ic_medical_services
            ),
            Especialidad(
                "Pediatría",
                "Dr. Marcos Vasquez Tantas",
                "L-S 8:00-20:00",
                R.drawable.ic_medical_services
            ),
            // Agrega aquí el resto de especialidades según el listado proporcionado
        )

    }

    private fun filtrarEspecialistas(textoBusqueda: String) {
        val listaFiltrada = if (textoBusqueda.isEmpty()) {
            listaOriginal
        } else {
            listaOriginal.filter { especialidad ->
                especialidad.nombre.contains(textoBusqueda, true) ||
                        especialidad.medicoResponsable.contains(textoBusqueda, true)
            }
        }
        especialidadAdapter.actualizarLista(listaFiltrada)
    }
}