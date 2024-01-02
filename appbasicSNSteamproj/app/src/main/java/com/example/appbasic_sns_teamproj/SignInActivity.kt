package com.example.appbasic_sns_teamproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        setSlide(Direction.RIGHT, Direction.STAY)

        val btn_goSignUpActivity = findViewById<Button>(R.id.btn_goSignUpActivity)

        editTv_id = findViewById(R.id.editTv_id)
        editTv_pw = findViewById(R.id.editTv_pw)

        //회원가입시에 입력한 코드를 받아오기 위해 아래에 있는 함수를 호출
        registerForActivityResult()

        //회원가입을 눌렀을 때 SignUp액티비티로 넘어가고 입력된 값을 받아오기 위해 런처를 실행
        btn_goSignUpActivity.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            registerLauncher.launch(intent)
        }

        //아이디와 비밀번호가 DB에 저장된 값과 일치하는지 확인하고 일치할 때 메인화면으로 넘어가도록 함
        val btn_logIn = findViewById<Button>(R.id.btn_logIn)
        btn_logIn.setOnClickListener {
            val id = editTv_id.text.toString()
            val pw = editTv_pw.text.toString()
            if (id == "") {
                Toast.makeText(this, getString(R.string.signin_id_check), Toast.LENGTH_SHORT).show()
            } else if (pw == "") {
                Toast.makeText(this, getString(R.string.signin_pw_check), Toast.LENGTH_SHORT).show()
            } else if (MemberManager.getMember(id) == null) {
                Toast.makeText(this, getString(R.string.signin_id_check2), Toast.LENGTH_SHORT).show()
            } else if (MemberManager.getMember(id)!!.pw != pw) {
                Toast.makeText(this, getString(R.string.signin_pw_check2), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.signin_welcome), Toast.LENGTH_SHORT).show()
                finish()
                // 유저 값 설정 - LYJ
                CurrentUser.user = MemberManager.getMember(id)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        setSlide(Direction.STAY, Direction.RIGHT)
    }

    //SignUp에서 입력한 아이디와 비밀번호를 받아오는 함수
    fun registerForActivityResult() {
        registerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    var idData = result.data?.getStringExtra(Extra.id)
                    var pwData = result.data?.getStringExtra(Extra.password)
                    //SignUp에서 데이터를 제대로 받아왔는지 Logcat으로 확인
//                Log.d("ASDFFFF", "id = ${Extra.id}")
//                Log.d("ASDFFFF", "pw = ${Extra.password}")
                    editTv_id.setText(idData)
                    editTv_pw.setText(pwData)
                }
            }
    }
}