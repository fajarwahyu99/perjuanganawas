package com.example.infolabsolution.perjuangankedua

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.infolabsolution.perjuangankedua.R
import com.example.infolabsolution.perjuangankedua.ApiRepository
import com.example.infolabsolution.perjuangankedua.invisible
import com.example.infolabsolution.perjuangankedua.visible
import com.example.infolabsolution.perjuangankedua.Event
import com.example.infolabsolution.perjuangankedua.Team
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class DetailActivity : AppCompatActivity(), MainView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressBar

    lateinit var ivHomeBadge: ImageView
    lateinit var ivAwayBadge: ImageView
    lateinit var tvDate: TextView
    lateinit var tvHomeTeam: TextView
    lateinit var tvHomeScore: TextView
    lateinit var tvHomeKp: TextView
    lateinit var tvHomeFw: TextView
    lateinit var tvHomeMid: TextView
    lateinit var tvHomeDef: TextView
    lateinit var tvAwayTeam: TextView
    lateinit var tvAwayScore: TextView
    lateinit var tvAwayKp: TextView
    lateinit var tvAwayFw: TextView
    lateinit var tvAwayMid: TextView
    lateinit var tvAwayDef: TextView

    var homeTeam : String? = ""
    var awayTeam : String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            backgroundResource = R.color.background
            orientation = LinearLayout.VERTICAL

            progressBar = progressBar {
            }.lparams {
                gravity = Gravity.CENTER_HORIZONTAL
            }

            cardView {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(8)

                    tvDate = textView("Wednesday, 12-08-2018") {
                        id = R.id.detail_date
                        textColor = R.color.colorAccent
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                        margin = dip(4)
                    }
                    relativeLayout {
                        tvHomeTeam = textView("Wednesday") {
                            id = R.id.tv_home_team
                            textSize = 16f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentStart()
                            margin = dip(4)
                        }
                        tvHomeScore = textView("-") {
                            id = R.id.event_home_score
                            textSize = 20f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent, height = wrapContent) {
                            margin = dip(4)
                            startOf(R.id.tv_vs)
                        }
                        textView("vs") {
                            id = R.id.tv_vs
                            textSize = 18f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            centerHorizontally()
                            margin = dip(4)
                        }
                        tvAwayScore = textView("-") {
                            id = R.id.event_away_score
                            textSize = 20f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent, height = wrapContent) {
                            margin = dip(4)
                            endOf(R.id.tv_vs)
                        }
                        tvAwayTeam = textView("away_team") {
                            id = R.id.tv_away_team
                            textSize = 16f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentEnd()
                            margin = dip(4)
                        }
                    }.lparams(width = matchParent, height = wrapContent)
                    relativeLayout {
                        ivHomeBadge = imageView {
                            id = R.id.iv_home_badge
                        }.lparams(width = 80, height = 80) {
                            alignParentStart()
                            margin = dip(4)
                        }
                        ivAwayBadge = imageView {
                            id = R.id.iv_away_badge
                        }.lparams(width = 80, height = 80) {
                            alignParentEnd()
                            margin = dip(4)
                        }
                    }.lparams(width = matchParent, height = wrapContent)
                    view {
                        backgroundResource = R.color.black
                    }.lparams(width = matchParent, height = dip(1)) {
                        topMargin = dip(16)
                    }
                    textView("Lineups") {
                        textSize = 16f
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                        margin = dip(4)
                    }
                    relativeLayout {
                        tvHomeKp = textView("Wednesday") {
                            id = R.id.tv_home_gk
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentStart()
                            margin = dip(4)
                        }
                        textView("Goalkeeper") {
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            centerHorizontally()
                            margin = dip(4)
                        }
                        tvAwayKp = textView("Wednesday") {
                            id = R.id.tv_away_gk
                            textSize = 14f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentEnd()
                            margin = dip(4)
                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(16)
                    }
                    relativeLayout {
                        tvHomeFw = textView("Wednesday") {
                            id = R.id.tv_home_fw
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentStart()
                            margin = dip(4)
                        }
                        textView("Forward") {
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            centerHorizontally()
                            margin = dip(4)
                        }
                        tvAwayFw = textView("Midfield") {
                            id = R.id.tv_away_fw
                            textSize = 14f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentEnd()
                            margin = dip(4)
                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(16)
                    }
                    relativeLayout {
                        tvHomeMid = textView("Wednesday") {
                            id = R.id.tv_home_mid
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentStart()
                            margin = dip(4)
                        }
                        textView("Midfield") {
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            centerHorizontally()
                            margin = dip(4)
                        }
                        tvAwayMid = textView("Wednesday") {
                            id = R.id.tv_away_mid
                            textSize = 14f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentEnd()
                            margin = dip(4)
                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(16)
                    }
                    relativeLayout {
                        tvHomeDef = textView("Wednesday") {
                            id = R.id.tv_home_def
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentStart()
                            margin = dip(4)
                        }
                        textView("Defense") {
                            textSize = 14f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            centerHorizontally()
                            margin = dip(4)
                        }
                        tvAwayDef = textView("Wednesday") {
                            id = R.id.tv_away_def
                            textSize = 14f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams(width = wrapContent, height = wrapContent) {
                            alignParentEnd()
                            margin = dip(4)
                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(16)
                    }
                }.lparams(width = matchParent, height = wrapContent)
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(16)
            }
        }

        val intent = intent
        val item = intent.getParcelableExtra<Event>("event")
//item.strHomeLineupGoalkeeper
//        val image = item.image
        val date = item.dateEvent
        homeTeam = item.strHomeTeam
        val homeKp = StringConverter(item.strHomeLineupGoalkeeper,";")
        val homeFw = StringConverter(item.strHomeLineupForward,";")
        val homeMid = StringConverter(item.strHomeLineupMidfield,";")
        val homeDef = StringConverter(item.strHomeLineupDefense,";")
        awayTeam = item.strAwayTeam
        val awayKp = StringConverter(item.strAwayLineupGoalkeeper,";")
        val awayFw = StringConverter(item.strAwayLineupForward,";")
        val awayMid = StringConverter(item.strAwayLineupMidfield,";")
        val awayDef = StringConverter(item.strAwayLineupDefense,";")

        tvDate.text = date
        tvHomeTeam.text = homeTeam
        tvHomeKp.text = homeKp
        tvHomeFw.text = homeFw
        tvHomeMid.text = homeMid
        tvHomeDef.text = homeDef
        tvAwayTeam.text = awayTeam
        tvAwayKp.text = awayKp
        tvAwayFw.text = awayFw
        tvAwayMid.text = awayMid
        tvAwayDef.text = awayDef
//        Glide.with(this).load(image).into(imageLogoView)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getTeamList(homeTeam,awayTeam)
    }

    fun StringConverter(text: String?, delimeter: String): String?{
        val textList: List<String>? = text?.split(delimeter)?.map { it.trim() }
        val output = textList?.joinToString("\n")
        return output
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showHomeTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        Picasso.get().load(teams[0].teamBadge).into(ivHomeBadge)
    }

    override fun showAwayTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        Picasso.get().load(teams[0].teamBadge).into(ivAwayBadge)
    }
    override fun showMatchList(data: List<Event>) {
    }
}