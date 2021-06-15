package com.ardhacodes.subs1_jetpack.ui.appintro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.ui.main.MainActivity
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment

class AppIntroActivity : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        addSlide(
            AppIntroFragment.newInstance(
            "Welcome!",
            "Ini Adalah Submission 3 Android Jetpack",
            imageDrawable = R.drawable.welcomepng,
            backgroundDrawable = R.drawable.back_slide1,
            titleTypefaceFontRes = R.font.poppins_semibold,
            descriptionTypefaceFontRes = R.font.poppins_light
        ))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}