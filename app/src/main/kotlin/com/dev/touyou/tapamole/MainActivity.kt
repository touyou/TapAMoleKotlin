package com.dev.touyou.tapamole

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import java.util.*
import kotlin.concurrent.*

class MainActivity : AppCompatActivity() {

    var scoreText: TextView? = null
    var timeText: TextView? = null

    val imageResources = arrayOf(
            R.id.imageView1, R.id.imageView2, R.id.imageView3,
            R.id.imageView4, R.id.imageView5, R.id.imageView6,
            R.id.imageView7, R.id.imageView8, R.id.imageView9,
            R.id.imageView10, R.id.imageView11, R.id.imageView12
    )

    var moles: Array<Mole>? = null

    var time: Int? = null
    var score: Int? = null

    var h: Handler? = null
    val random: Random = Random()
    var timerTasks: TimerTask? = null
    var timers: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreText = findViewById(R.id.scoreText) as TextView
        timeText = findViewById(R.id.timeText) as TextView
        moles = Array(12, {i -> Mole(findViewById(imageResources[i]) as ImageView)})    // この書き方いいね
        h = Handler()
    }

    fun start(v: View) {
        time = 60
        score = 0
        timeText?.setText(time.toString())
        scoreText?.setText(score.toString())
        timers = Timer()
        timerTasks = timerTask({
            h?.post({
                val r = random.nextInt(12)
                moles!![r].start()
                time = time!! - 1
                timeText?.setText(time.toString())
                if (time!! <= 0) {
                    timers?.cancel()
                }
            })
        })
        timers?.schedule(timerTasks, 0, 1000)
    }

    fun tapMole(v: View) {
        val tag_str = v.tag.toString()
        val tag_int = tag_str.toInt()
        score = score!! + moles!![tag_int].tapMole()
        scoreText?.setText(score.toString())
    }
}
