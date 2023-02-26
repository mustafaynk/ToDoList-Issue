package com.example.todolistziro.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.lifecycle.Observer
import com.example.todolistziro.R
import com.example.todolistziro.architecture.network.Utils
import com.example.todolistziro.architecture.repositories.LoginRepositoryImpl
import com.example.todolistziro.architecture.viewModel
import com.example.todolistziro.databinding.ActivitySplashLoginBinding
import com.example.todolistziro.architecture.extensions.fullscreen
import com.example.todolistziro.architecture.extensions.getStringDataToLocal
import com.example.todolistziro.architecture.extensions.saveStringDataToLocal
import com.example.todolistziro.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashLoginBinding
    private val loginViewModel by viewModel(::initViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_login)
        binding.lifecycleOwner = this
        if (getStringDataToLocal(Utils.LocalDataKeys.TOKEN).isNotEmpty()) {
            navigateToMainActivity(getStringDataToLocal(Utils.LocalDataKeys.TOKEN))
        }
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
                logo.animate().translationY(-(form.height / 1.5f))
                    .setInterpolator(LinearOutSlowInInterpolator()).duration = 1000
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            loginViewModel.login(
                binding.edtMail.text.toString(),
                binding.edtPassword.text.toString()
            )
        }

        loginViewModel.loginResponse.observe(this) {
            it?.let {
                if(it.jwt.isNotEmpty()) {
                    val sharedPref = getPreferences(MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString(Utils.LocalDataKeys.TOKEN, it.jwt)
                        commit()
                    }
                    navigateToMainActivity(it.jwt)
                }
            }
        }

        fullscreen()
    }

    private fun navigateToMainActivity(token: String) {
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putString(Utils.LocalDataKeys.TOKEN, token)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    private fun initViewModel() = LoginViewModel(LoginRepositoryImpl())

}