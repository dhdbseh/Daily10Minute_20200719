package kr.co.tjoeun.daily10minute_20200719

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.pwEdt
import kotlinx.android.synthetic.main.activty_sign_up.*

class SignUpActivity : BaseActivity() {


    override fun setupEvents() {

//        회원가입 버튼 누르면 -> 빈 입력값이 있는지 검사하고
//        -> 괜찮으면 실제로 서버에 가입 요청

        okBtn.setOnClickListener {
//            입력한 이메일부터 검사하자
            val inputEmail = emailEdt.text.toStriong()

            if (inputEmail.isEmpty()){
                Toast.makeText(mContext, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()

//                가입절차 강제종료
                return@setOnClickListener
            }
            else if (!inputEmail.contains("@")) {
//                @가 없다면, 이메일 양식이 아닌걸로 간주하자.
                Toast.makeText(mContext, "이메일 양식으로 입력해주세요.", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

//            이메일 검사는 모두 통과한 상황
//            비밀번호 길이가 8자 이상인지

            val inputPw = pwEdt.text.toString()

            if (inputPw.length < 8){
                Toast.makeText(mContext, "비밀번호가 너무 짧습니다.", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

//            닉네임은 입력했는지만 검사할게

            val inputNickname = nickNameEdt.text.toString()

            if (inputNickname.isEmpty()) {
                Toast.makeText(mContext, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            이메일 / 비번 / 닉네임 검사를 모두 통과한 상황
//            서버에 실제로 가입 신청.

//            ServerUtil.po

        }

//        EditText (비번 입력칸)에 글자를 타이핑하는 이벤트 체크
        pwEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //비번 확인 로직 실행
                checkPasswords()
            }

        })

    }

//    비밀번호 + 비밀번호 확인 동시에 검사하는 함수

    fun checkPasswords() {

//        입력한 비밀번호
        val inputPw = pwEdt.text.toString()

//        글자 수 -> 0자 : 비밀번호를 입력해주세요.
//        1~7자 : 비밀번호가 너무 짧습니다.
//        8자 이상 : 사용해도 좋은 비밀번호 입니다.

        if (inputPw.isEmpty()){
            pwCheckResultTxt.text = "비밀번호를 입력해주세요."
        }
        else if (inputPw.length < 8) {
            pwCheckResultTxt.text = "비밀번호가 너무 짧습니다."
        }
        else{
            pwCheckResultTxt.text = "사용해도 좋은 비밀번호 입니다."
        }

    }

    override fun setupValues() {

    }
}