package project.org.italiazonealert

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val plashTimeOut = 2000
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, plashTimeOut.toLong())
    }

    public override fun onRestart() {
        super.onRestart()
        setContentView(R.layout.activity_main)
    }
}