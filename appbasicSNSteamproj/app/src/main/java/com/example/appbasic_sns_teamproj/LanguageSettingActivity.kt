package com.example.appbasic_sns_teamproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton

class LanguageSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_setting)

        val back = findViewById<ImageButton>(R.id.ibtn_back)
        val korean = findViewById<RadioButton>(R.id.rbtn_korean)

        back.setOnClickListener {
            finish()
        }


    }
}