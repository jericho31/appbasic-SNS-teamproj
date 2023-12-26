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

        val editTv_id = findViewById<EditText>(R.id.editTv_id).text.toString()
        val editTv_pw = findViewById<EditText>(R.id.editTv_pw).text.toString()

        val btn_goSignUpActivity = findViewById<Button>(R.id.btn_goSignUpActivity)

        btn_goSignUpActivity.setOnClickListener {

            val btn_logIn = findViewById<Button>(R.id.btn_logIn)

            if(editTv_id.isEmpty()){
                Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(editTv_pw.isEmpty()){
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
            }
        }



    }
}