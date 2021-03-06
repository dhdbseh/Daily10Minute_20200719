package kr.co.tjoeun.daily10minute_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minute_20200719.utils.ContextUtil
import kr.co.tjoeun.daily10minute_20200719.utils.MainActivity2
import kr.co.tjoeun.daily10minute_20200719.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setupValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {

            //입력한 아이디 / 비번 받아오기
            val inputId = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            //서버에 로그인 요청
            ServerUtil.postRequestLogin(mContext, inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    //code에 적힌 숫자가 몇인지 저장하자.
                    val codeNum = json.getInt("code")

                    //codeNum이 200일땐 로그인 성공
                    //그 외의 모든 숫자는 로그인 실패.

                    if (codeNum == 200){
                        //로그인 성공

                        val data = json.getJSONObject("data")

                        val token = data.getJSONObject("token")

                        ContextUtil.setLoginUserToken(mContext, token)

                        val myIntent = Intent(mContext, MainActivity2::class.java)
                        startActivity(myIntent)

                        finish()

                    }
                    else{
                        //로그인 실패

                        //로그인 실패 사유를 서버가 받도록

                        val failReason = json.getString("message")

                        //서버 통신 중에 UI에 영향을 주려면 runOnUiThread 활용하자
                        runOnUiThread {

                            Toast.makeText(mContext, failReason, Toast.LENGTH_SHORT).show()


                        }
                    }

                }

            })

        }

    }

    override fun setupValues() {

    }


}