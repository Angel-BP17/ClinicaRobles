package com.angelbustamante.clinicarobles.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Especialidad(
    val nombre: String,
    val medicoResponsable: String,
    val horario: String,
    val imagenId: Int
) : Parcelable