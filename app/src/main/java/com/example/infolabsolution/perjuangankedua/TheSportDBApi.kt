package com.example.infolabsolution.perjuangankedua

import android.net.Uri
import com.example.infolabsolution.perjuangankedua.BuildConfig

object TheSportDBApi {

//    https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=Arsenal
    fun getTeams(team: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchteams.php")
                .appendQueryParameter("t", team)
                .build()
                .toString()
    }

    //  https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
    fun getPrevEvents(idEvent: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", idEvent)
                .build()
                .toString()
    }

    //  https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328
    fun getNextEvents(idEvent: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", idEvent)
                .build()
                .toString()
    }
}