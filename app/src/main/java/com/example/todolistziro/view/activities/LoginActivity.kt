package com.example.todolistziro.view.activities

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.example.todolistziro.R
import com.example.todolistziro.fullscreen

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_login)
        val logo = findViewById<View>(R.id.logo)
        val form = findViewById<View>(R.id.form)
        form.visibility = View.INVISIBLE

        val alphaAnimation = AlphaAnimation(0.2f, 1.0f)
        alphaAnimation.duration = 600
        alphaAnimation.repeatCount = 2
        alphaAnimation.repeatMode = Animation.REVERSE
        logo.startAnimation(alphaAnimation)

        val alphaAnimation2 = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 500

        alphaAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                form.visibility = View.VISIBLE
                form.startAnimation(alphaAnimation2)
                logo.animate().translationY(-(form.height / 2.0f))
                    .setInterpolator(LinearOutSlowInInterpolator()).duration = 1000
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        fullscreen()
    }
}