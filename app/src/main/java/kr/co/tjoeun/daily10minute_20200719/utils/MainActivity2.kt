package kr.co.tjoeun.daily10minute_20200719.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.tjoeun.daily10minute_20200719.BaseActivity

class MainActivity2 : BaseActivity() {
    override fun setupEvents() {

    }

    override fun setupValues() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setupEvents()
        setValues()
    }
}