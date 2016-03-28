package com.dev.touyou.tapamole

import android.widget.ImageView


/**
 * Created by touyou on 16/03/28.
 */
class Mole {
    var state: Int? = null
    var moleImage: ImageView? = null
    var h: android.os.Handler? = null
    var hide: Runnable? = null


    constructor(imageView: ImageView) {
        state = 0
        moleImage = imageView
        moleImage?.setImageResource(R.drawable.mole1)
        h = android.os.Handler()
        hide = Runnable {
            state = 0
            moleImage?.setImageResource(R.drawable.mole1)
        }

    }

    fun start() {
        if (state!! == 0) {
            state = 1
            moleImage?.setImageResource(R.drawable.mole2)
            h?.postDelayed(hide, 1000)
        }
    }

    fun tapMole(): Int {
        if (state!! == 1) {
            state = 2
            moleImage?.setImageResource(R.drawable.mole3)
            h?.removeCallbacks(hide)
            h?.postDelayed(hide, 1000)
            return 1
        }
        return 0
    }
}