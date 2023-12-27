package com.example.appbasic_sns_teamproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        this.setSlide(Direction.STAY)

        val btn_goSignUpActivity = findViewById<Button>(R.id.btn_goSignUpActivity)

        btn_goSignUpActivity.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        val btn_logIn = findViewById<Button>(R.id.btn_logIn)
         btn_logIn.setOnClickListener {
             val editTv_id = findViewById<EditText>(R.id.editTv_id).text.toString()
             val editTv_pw = findViewById<EditText>(R.id.editTv_pw).text.toString()
             if(editTv_id.isEmpty()){
                 Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
             }
             else if (editTv_pw.isEmpty()){
                 Toast.makeText(this, "패스워드를 확인해주세요", Toast.LENGTH_SHORT).show()
             }
             else{
                 Toast.makeText(this, "환영합니다", Toast.LENGTH_SHORT).show()
                 val intent = Intent(this, MainPageActivity::class.java)
                 startActivity(intent)
             }
         }
    }
}