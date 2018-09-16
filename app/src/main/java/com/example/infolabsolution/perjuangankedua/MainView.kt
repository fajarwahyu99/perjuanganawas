package com.example.infolabsolution.perjuangankedua

import com.example.infolabsolution.perjuangankedua.Event
import com.example.infolabsolution.perjuangankedua.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showHomeTeamList(data: List<Team>)
    fun showAwayTeamList(data: List<Team>)
    fun showMatchList(data: List<Event>)
}