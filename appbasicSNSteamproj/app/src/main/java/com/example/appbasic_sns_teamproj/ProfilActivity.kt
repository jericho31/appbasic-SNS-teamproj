package com.example.spatraforest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val track = intent.getStringExtra("track")
        val id = intent.getStringExtra("id")
        val btnLanguageSetting = findViewById<Button>(R.id.btn_languageSetting)

        track.setText(track)
        id.setText(id)

        btnLanguageSetting.setOnClickListener{
            val intent = Intent(this, LanguageSettingActivity::class.java)
            startActivity(intent)
        }
    }
}