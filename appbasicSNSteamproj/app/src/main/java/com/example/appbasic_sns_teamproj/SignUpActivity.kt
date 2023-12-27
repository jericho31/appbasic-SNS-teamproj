package com.example.appbasic_sns_teamproj

import android.os.Bundle
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
        val mails = arrayOf("gmail.com", "kakao.com", "naver.com", "직접 입력")
    }

    private val etName: EditText by lazy { findViewById(R.id.et_chall_name) }
    private val tvNameWarn: TextView by lazy { findViewById(R.id.tv_chall_name_warn) }
    private val etMail: EditText by lazy { findViewById(R.id.et_chall_mail) }
    private val etDomain: EditText by lazy { findViewById(R.id.et_chall_domain) }
    private val tvMailWarn: TextView by lazy { findViewById(R.id.tv_chall_mail_warn) }
    private val etPw: EditText by lazy { findViewById(R.id.et_chall_password) }
    private val tvPwWarn: TextView by lazy { findViewById(R.id.tv_chall_password_warn) }
    private val tvPwLength: TextView by lazy { findViewById(R.id.tv_chall_password_length) }
    private val etVerify: EditText by lazy { findViewById(R.id.et_chall_verify) }
    private val tvVerifyWarn: TextView by lazy { findViewById(R.id.tv_chall_verify_warn) }
    private val btn: Button by lazy { findViewById(R.id.btn_chall_signup) }
    private val spinner: Spinner by lazy { findViewById(R.id.spnr_chall_mail) }

    // EditText 배열
    private val arrET by lazy { arrayOf(etName, etMail, etDomain, etPw, etVerify) }

    private var okName = false
    private var okMail = false

    // TODO: [jericho] okDomain이랑 스피너 visible 두 조건 어떻게 잘 좀 통합
    private var okDomain = false
    private var okPw = false
    private var okVerify = false

    private val anim = AlphaAnimation(0.125f, 0.625f).apply {
        duration = 100L
        repeatCount = 3
    }
    private val pwWatcher by lazy {
        object : TextWatcher {
            private var beforePw = ""
            private var beforeCursor = 0

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d(TAG, "before:: start: $start, count: $count, after: $after")
                Log.d(TAG, "$s/selection: ${etPw.selectionStart}, ${etPw.selectionEnd}")
                beforePw = etPw.text.toString()
                beforeCursor = start
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(
                    TAG,
                    "on:: start: $start, before: $before, count: $count"
                )
                Log.d(
                    TAG,
                    "$s/selection: ${etPw.selectionStart}, ${etPw.selectionEnd}"
                )
            }

            // 사용자의 입력을 막아버리는 것은 UX상 좋지 않다. 안내를 해주는 편이 좋다.
            // 한글은 조합때문에 입력받고 무시하기 어려울텐데, inputType textPassword 가 알아서 막아주네.
            // 원표시₩는 그냥 입력이 안됨.
            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG, "after:: $s/selection: ${etPw.selectionStart}, ${etPw.selectionEnd}")
                if (!pwInputPattern.matcher(etPw.text.toString()).matches()) {
                    // warn 바꾸는 쪽으로. 그냥 바꾸면 잘 안보일듯. 깜빡여야. 그러려면 코루틴 블라킹으로...?
                    // AlphaAnimation 이라는 좋은 기능이 있었다. 역시 구글갓
                    tvPwWarn.text = "입력할 수 없는 문자입니다."
                    tvPwWarn.startAnimation(anim)
                    // TODO: [jericho] 입력 무시 -> 에러 안내로 변경
                    etPw.removeTextChangedListener(this)
                    etPw.setText(beforePw)
                    etPw.setSelection(beforeCursor)
                    etPw.addTextChangedListener(this)
                    return
                }
                "${etPw.text.length}/16".also { tvPwLength.text = it }
                check(etPw)
                Log.d(TAG, "== back from check ==")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        this.setSlide(Direction.RIGHT)

        // 처음에 기본으로 첫번째꺼가 선택되어 있는 이유는 어레이어댑터라서 그런가?
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mails)

        arrET.forEach { et -> if ((et == etPw).not()) et.addTextChangedListener { check(et) } }
        etPw.addTextChangedListener(pwWatcher)
        val onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus -> if (!hasFocus) check(v!!) }
        arrET.forEach { it.onFocusChangeListener = onFocusChangeListener }

        btn.setOnClickListener {
            if (spinner.isVisible) {
                intent.putExtra(Extra.id, "${etMail.text}@${spinner.selectedItem}")
            } else {
                intent.putExtra(Extra.id, "${etMail.text}@${etDomain.text}")
            }
            intent.putExtra(Extra.password, etPw.text.toString())
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
                    tvNameWarn.text = "이름을 입력해주세요."
                    okName = false
                } else {
                    tvNameWarn.text = ""
                    okName = true
                }
            }

            etMail, etDomain -> {
                okMail = etMail.text.isNotEmpty()
                okDomain = etDomain.text.isNotEmpty()

                if (spinner.isVisible) {
                    if (!okMail) tvMailWarn.text = "비밀번호를 입력해주세요."
                    else tvMailWarn.text = ""
                } else {
                    if (!okMail) tvMailWarn.text = "이메일${if (!okDomain) "과 도메인" else ""}을 입력해주세요."
                    else if (!okDomain) tvMailWarn.text = "도메인을 입력해주세요."
                    else tvMailWarn.text = ""
                }
            }

            etPw -> {
                val pw = etPw.text.toString()
                if (etPw.text.isEmpty()) {
                    tvPwWarn.text = "비밀번호를 입력해주세요."
                    okPw = false
                } else if (pw.length < 8) {
                    tvPwWarn.text = "비밀번호는 8자리 이상이어야 합니다."
                    okPw = false
                } else if (pw.length > 16) {
                    tvPwWarn.text = "비밀번호는 16자리 이하여야 합니다."
                    okPw = false
                } else if (!pwFinalPattern.matcher(pw).matches()) {
                    tvPwWarn.text = "영문, 숫자, 특수문자 혼합 8~16자"
                    okPw = false
                } else {
                    tvPwWarn.text = ""
                    okPw = true
                }
            }

            etVerify -> {
                if (etVerify.text.contentEquals(etPw.text)) {
                    tvVerifyWarn.text = ""
                    okVerify = true
                } else {
                    tvVerifyWarn.text = "비밀번호가 일치하지 않습니다."
                    okVerify = false
                }
            }
        }

        btn.isEnabled = okName && okMail && (spinner.isVisible || okDomain) && okPw && okVerify
    }

    private fun toastShort(s: String) = Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
}