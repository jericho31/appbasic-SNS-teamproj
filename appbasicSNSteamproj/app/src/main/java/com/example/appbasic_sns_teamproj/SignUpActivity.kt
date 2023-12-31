package com.example.appbasic_sns_teamproj

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.animation.AlphaAnimation
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import java.util.regex.Pattern

// xml로 넣으려했더니 getString 하려면 context가 있어야하네. 그냥 전역으로 냅두는걸로.
// 입력 길이도 정규식에서 체크할 수 있지만 메세지를 달리 하려면 체크하는 쪽에서도 해야 함.
//val sample = """^[0-9a-zA-Z!@#$%^+\-=]*$"""  // 참고용
//val sample = """^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^+\-=])(?=\S+$).*$"""  // 참고용
//val sample = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}$"  // 참고용
//val sample = "^(?=.*[A-Za-z])(?=.*[$@$!%*#?&.])[A-Za-z$@$!%*#?&.]{8,20}$"  // 참고용
//val sample = "^(?=.*[0-9])(?=.*[$@$!%*#?&.])[[0-9]$@$!%*#?&.]{8,20}$"  // 참고용
const val pwInputRegex = """^[0-9a-zA-Z!"#$%&'()*+,-./:;<=>?@\[\]^_`{|}~]*$"""
const val pwFinalRegex =
    """^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!"#$%&'()*+,-./:;<=>?@\[\]^_`{|}~])[0-9a-zA-Z0-9a-zA-Z!"#$%&'()*+,-./:;<=>?@\[\]^_`{|}~]{8,16}$"""
val pwInputPattern: Pattern = Pattern.compile(pwInputRegex)
val pwFinalPattern: Pattern = Pattern.compile(pwFinalRegex)

class SignUpActivity : AppCompatActivity() {
    // 클래스 변수명 정규식인듯: """_?[a-z][A-Za-z\d]*"""
    private val TAG = "mine"

    companion object {
        // 나중엔 서버에서 받아오는 식.
        val mails = arrayOf("gmail.com", "kakao.com", "naver.com", "직접입력")
        val tracks = arrayOf("Android", "iOS", "Unity", "기타")
    }

    private val etName: EditText by lazy { findViewById(R.id.et_chall_name) }
    private val tvNameWarn: TextView by lazy { findViewById(R.id.tv_chall_name_warn) }
    private val etMail: EditText by lazy { findViewById(R.id.et_chall_mail) }
    private val etDomain: EditText by lazy { findViewById(R.id.et_chall_domain) }
    private val tvMailWarn: TextView by lazy { findViewById(R.id.tv_chall_mail_warn) }
    private val etPw: EditText by lazy { findViewById(R.id.et_chall_password) }
    private val tvPwWarn: TextView by lazy { findViewById(R.id.tv_chall_password_warn) }
    private val tvPwLength: TextView by lazy { findViewById(R.id.tv_chall_password_length) }
    private val btn: Button by lazy { findViewById(R.id.btn_chall_signup) }
    private val spinner: Spinner by lazy { findViewById(R.id.spinner_chall_mail) }
    private val spinnerTrack: Spinner by lazy { findViewById(R.id.spinner_signUp_track) }

    // EditText 배열
    private val arrET by lazy { arrayOf(etName, etMail, etDomain, etPw) }

    private var okName = false
    private var okMail = false

    private var okDomain = false
    private var okPw = false

    private val anim = AlphaAnimation(0.125f, 0.625f).apply {
        duration = 100L
        repeatCount = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setSlide(Direction.RIGHT, Direction.STAY)

        // 처음에 기본으로 첫번째꺼가 선택되어 있는 이유는 어레이어댑터라서 그런가?
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mails)
        spinnerTrack.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tracks)

        arrET.forEach { et -> et.addTextChangedListener { check(et) } }
        val onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus -> if (!hasFocus) check(v!!) }
        arrET.forEach { it.onFocusChangeListener = onFocusChangeListener }


        //회원가입 버튼이 눌렸을 때 회원가입한 정보를 DB에 저장하고 새로 생성한 ID, PW을 SignIn액티비티로 정보를 넘김
        btn.setOnClickListener {

            //SignIn액티비티로 ID, PW 정보를 넘김
            var id = if (spinner.isVisible) {
                "${etMail.text}@${spinner.selectedItem}"
            } else {
                "${etMail.text}@${etDomain.text}"
            }
            intent.putExtra(Extra.id, id)
            intent.putExtra(Extra.password, etPw.text.toString())
            intent.putExtra("track", spinnerTrack.selectedItem.toString())

            // DB에 유저 넣기
            val newUser = User(
                id,
                etPw.text.toString(),
                etName.text.toString(),
                spinnerTrack.selectedItem.toString()
            )
            MemberManager.addMember(newUser)

            setResult(RESULT_OK, intent)
            finish()
        }

        spinner.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    if (position == spinner.adapter.count - 1) {
                        etDomain.isVisible = true
                        spinner.isVisible = false
                    }

                    check(spinner)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun onPause() {
        super.onPause()

        setSlide(Direction.STAY, Direction.RIGHT)
    }

    private var checkCount = 0  // for debug
    fun check(v: View) {
        Log.d(
            TAG, "v: ${
                v.toString().run { substring(length - 36, length - 15) }
            }, check count: ${++checkCount}"
        )
        when (v) {
            etName -> {
                if (etName.text.isEmpty()) {
                    tvNameWarn.text = getString(R.string.signup_name)
                    okName = false
                } else {
                    tvNameWarn.text = getString(R.string.Quotation_mack)
                    okName = true
                }
            }

            etMail, etDomain -> {
                okMail = etMail.text.isNotEmpty()
                okDomain = etDomain.text.isNotEmpty()

                if (spinner.isVisible) {
                    if (!okMail) tvMailWarn.text = getString(R.string.signup_id)
                    else tvMailWarn.text = getString(R.string.Quotation_mack)
                } else {
                    if (!okMail) tvMailWarn.text = "이메일${if (!okDomain) "과 도메인" else ""}을 입력해주세요."
                    else if (!okDomain) tvMailWarn.text = getString(R.string.signup_domain)
                    else tvMailWarn.text = getString(R.string.Quotation_mack)
                }
            }

            etPw -> {
                val pw = etPw.text.toString()
                "${pw.length}/16".also { tvPwLength.text = it }
                if (etPw.text.isEmpty()) {
                    tvPwWarn.text = getString(R.string.signup_pw)
                    okPw = false
                } else if (!pwInputPattern.matcher(etPw.text.toString()).matches()) {
                    tvPwWarn.text = getString(R.string.signup_pw_check1)
                    tvPwWarn.startAnimation(anim)
                    okPw = false
                } else if (pw.length < 8) {
                    tvPwWarn.text = getString(R.string.signup_pw_check2)
                    okPw = false
                } else if (pw.length > 16) {
                    tvPwWarn.text = getString(R.string.signup_pw_check3)
                    okPw = false
                } else if (!pwFinalPattern.matcher(pw).matches()) {
                    tvPwWarn.text = getString(R.string.signup_pw_check4)
                    okPw = false
                } else {
                    tvPwWarn.text = getString(R.string.Quotation_mack)
                    okPw = true
                }
            }
        }

        btn.isEnabled = okName && okMail && (spinner.isVisible || okDomain) && okPw
    }

    private fun toastShort(s: String) = Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
}