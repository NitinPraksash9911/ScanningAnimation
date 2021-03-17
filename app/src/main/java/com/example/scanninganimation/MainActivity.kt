package com.example.scanninganimation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.REVERSE
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {
    lateinit var view: View
    lateinit var linearLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        actionBar?.hide()
        supportActionBar?.setDisplayShowTitleEnabled(false)

        view = findViewById(R.id.view)
        linearLayout = findViewById(R.id.mainLayout)


        val displayMetrics =
            DisplayMetrics().also { windowManager.defaultDisplay.getRealMetrics(it) }
//        ObjectAnimator.ofFloat(view, "translationY", displayMetrics.heightPixels.toFloat())
//            .apply {
//                duration = 1000
//                repeatMode = REVERSE
//                interpolator = AccelerateInterpolator()
//                repeatCount = -1
//                start()
//            }



        ObjectAnimator.ofInt(linearLayout, "backgroundColor",  Color.RED).apply {
            duration= 30000
//            repeatMode= REVERSE
            interpolator = AccelerateInterpolator()
//            repeatCount = -1
            start()

        }


    }
}