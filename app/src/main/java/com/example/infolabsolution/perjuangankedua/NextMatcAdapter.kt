package com.example.infolabsolution.perjuangankedua

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.infolabsolution.perjuangankedua.R
import com.example.infolabsolution.perjuangankedua.Event
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class NextMatchAdapter(private val context: Context, private val teams: List<Event>,
                       private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<NextMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(NextMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

}

class NextMatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(width = matchParent, height = wrapContent)

                cardView {
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        padding = dip(8)

                        textView("Wednesday, 12-08-2018") {
                            id = R.id.event_date
                            textColor = R.color.colorAccent
                        }.lparams(width = wrapContent, height = wrapContent) {
                            gravity = Gravity.CENTER_HORIZONTAL
                            margin = dip(4)
                        }
                        relativeLayout {
                            textView("Wednesday") {
                                id = R.id.event_home
                                textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                                textSize = 16f
                            }.lparams(width = wrapContent, height = wrapContent) {
                                alignParentStart()
                                margin = dip(4)
                            }
                            textView("1") {
                                id = R.id.event_home_score
                                textSize = 16f
                                typeface = Typeface.DEFAULT_BOLD
                            }.lparams(width = wrapContent, height = wrapContent) {
                                startOf(R.id.vs)
                                margin = dip(4)
                            }
                            textView("vs") {
                                id = R.id.vs
                                textSize = 16f
                            }.lparams(width = wrapContent, height = wrapContent) {
                                centerHorizontally()
                                margin = dip(4)
                            }
                            textView("2") {
                                id = R.id.event_away_score
                                textSize = 16f
                                typeface = Typeface.DEFAULT_BOLD
                            }.lparams(width = wrapContent, height = wrapContent) {
                                endOf(R.id.vs)
                                margin = dip(4)
                            }
                            textView("Wednesday") {
                                id = R.id.event_away
                                textSize = 16f
                            }.lparams(width = wrapContent, height = wrapContent) {
                                alignParentEnd()
                                margin = dip(4)
                            }
                        }.lparams(width = matchParent, height = wrapContent)
                    }.lparams(width = matchParent, height = wrapContent)
                }.lparams(width = matchParent, height = wrapContent) {
                    margin = dip(16)
                }
            }

        }
    }

}

class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventDate: TextView = view.find(R.id.event_date)
    private val eventHome: TextView = view.find(R.id.event_home)
    private val eventHomeScore: TextView = view.find(R.id.event_home_score)
    private val eventAway: TextView = view.find(R.id.event_away)
    private val eventAwayScore: TextView = view.find(R.id.event_away_score)

    fun bindItem(events: Event,listener: (Event) -> Unit) {
        eventDate.text = events.dateEvent
        eventHome.text = events.strHomeTeam
        eventAway.text = events.strAwayTeam
        eventHomeScore.text = events.intHomeScore
        eventAwayScore.text = events.intAwayScore

        itemView.setOnClickListener{
            listener(events)
        }

    }
}