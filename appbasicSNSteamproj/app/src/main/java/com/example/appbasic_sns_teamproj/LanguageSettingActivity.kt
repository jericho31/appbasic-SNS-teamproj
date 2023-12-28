package com.example.appbasic_sns_teamproj

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import java.util.Locale

class LanguageSettingActivity : AppCompatActivity() {

    private lateinit var korean: RadioButton
    private lateinit var english: RadioButton
    private lateinit var language_code: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_setting)

        korean = findViewById(R.id.rbtn_korean)
        english = findViewById(R.id.rbtn_english)

        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            Log.d("log", "language :"+language)
            language_code = language
        }

        if(language_code.equals("en") || language_code.equals("")){
            english.setChecked(true);
        }else{
            korean.setChecked(true);
        }

        korean.setOnClickListener {
            setLocate("ko")
            recreate()
        }
        english.setOnClickListener {
            setLocate("en")
            recreate()
        }

        val back = findViewById<ImageView>(R.id.iv_back)
        val check = findViewById<Button>(R.id.btn_check)
        back.setOnClickListener {
            finish()
        }
        check.setOnClickListener {
            finish()
        }

    }

    private fun setLocate(Lang: String) {
        Log.d("log", "setLocate")
        val locale = Locale(Lang)
        Locale.setDefault(locale)

        val config = Configuration()

        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

}