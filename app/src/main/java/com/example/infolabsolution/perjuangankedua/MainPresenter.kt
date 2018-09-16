package com.example.infolabsolution.perjuangankedua

import com.example.infolabsolution.perjuangankedua.ApiRepository
import com.example.infolabsolution.perjuangankedua.TheSportDBApi
import com.example.infolabsolution.perjuangankedua.EventResponse
import com.google.gson.Gson
import com.example.infolabsolution.perjuangankedua.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getTeamList(home: String?, away: String?) {
        view.showLoading()
        doAsync {
            val dataHome = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(home)),
                    TeamResponse::class.java
            )
            val dataAway = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(away)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showHomeTeamList(dataHome.teams)
                view.showAwayTeamList(dataAway.teams)
            }
        }
    }

    fun getPrevEventList (eventId : String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPrevEvents(eventId)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getNextEventList (eventId : String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextEvents(eventId)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

}