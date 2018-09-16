package com.example.infolabsolution.perjuangankedua

import android.support.v4.app.Fragment
import com.example.infolabsolution.perjuangankedua.R
import com.example.infolabsolution.perjuangankedua.NextFragment
import com.example.infolabsolution.perjuangankedua.PrevFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    PREV(0, R.id.home),
    NEXT(1, R.id.dashboard);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.PREV.id -> BottomNavigationPosition.PREV
    BottomNavigationPosition.NEXT.id -> BottomNavigationPosition.NEXT
    else -> BottomNavigationPosition.PREV
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.PREV -> PrevFragment.newInstance()
    BottomNavigationPosition.NEXT -> NextFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.PREV -> PrevFragment.TAG
    BottomNavigationPosition.NEXT -> NextFragment.TAG
}

