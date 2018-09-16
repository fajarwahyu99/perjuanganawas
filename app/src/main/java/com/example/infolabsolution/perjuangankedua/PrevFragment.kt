package com.example.infolabsolution.perjuangankedua

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.infolabsolution.perjuangankedua.R
import com.example.infolabsolution.perjuangankedua.ApiRepository
import com.example.infolabsolution.perjuangankedua.invisible
import com.example.infolabsolution.perjuangankedua.visible
import com.example.infolabsolution.perjuangankedua.Event
import com.example.infolabsolution.perjuangankedua.Team
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class PrevFragment : Fragment(), MainView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: PrevMatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var idEvent: String

    companion object {
        val TAG: String = PrevFragment::class.java.simpleName
        fun newInstance() = PrevFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_prev_match)

        val ui =UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                backgroundResource = R.color.background
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listEvent = recyclerView {
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
                        }
                    }
                }
            }
        }.view

        adapter = PrevMatchAdapter(ctx, events) {
            requireActivity().startActivity<DetailActivity>("event" to it)
        }

        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        idEvent = "4328"
        presenter.getPrevEventList(idEvent)

        swipeRefresh.onRefresh {
            presenter.getPrevEventList(idEvent)
        }
        return ui
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showHomeTeamList(data: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAwayTeamList(data: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun showMatchList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}