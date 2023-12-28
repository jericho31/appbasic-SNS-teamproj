package com.example.appbasic_sns_teamproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class LanguageSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_setting)

        val back = findViewById<ImageView>(R.id.iv_back)
        val korean = findViewById<RadioButton>(R.id.rbtn_korean)
        val english = findViewById<RadioButton>(R.id.rbtn_english)

        back.setOnClickListener {
            finish()
        }

    }
}