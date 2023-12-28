package com.example.appbasic_sns_teamproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class SignInActivity : AppCompatActivity() {
    private lateinit var registerLauncher: ActivityResultLauncher<Intent>
    private lateinit var editTv_id: EditText
    private lateinit var editTv_pw: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val btn_goSignUpActivity = findViewById<Button>(R.id.btn_goSignUpActivity)

        editTv_id = findViewById(R.id.editTv_id)
        editTv_pw = findViewById(R.id.editTv_pw)

        registerForActivityResult()
        btn_goSignUpActivity.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            registerLauncher.launch(intent)
        }


        val btn_logIn = findViewById<Button>(R.id.btn_logIn)
        btn_logIn.setOnClickListener {
            if (editTv_id.text.isEmpty()) {
                Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else if (editTv_pw.text.isEmpty()) {
                Toast.makeText(this, "패스워드를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "환영합니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainPageActivity::class.java)
                registerLauncher.launch(intent)
            }
        }
    }

    fun registerForActivityResult() {
        registerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    var idData = result.data?.getStringExtra(Extra.id)
                    var pwData = result.data?.getStringExtra(Extra.password)
//                Log.d("ASDFFFF", "id = ${Extra.id}")
//                Log.d("ASDFFFF", "pw = ${Extra.password}")
                    editTv_id.setText(idData)
                    editTv_pw.setText(pwData)
                }
            }
    }
}