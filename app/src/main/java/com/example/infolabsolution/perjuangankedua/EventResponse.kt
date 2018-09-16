package com.example.infolabsolution.perjuangankedua

import com.google.gson.annotations.SerializedName

data class EventResponse(
        val events: List<Event>
        //@SerializedName("events") val events: List<Event?>
)