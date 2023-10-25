package project.org.italiazonealert

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class NoConnectionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noconnection)
        val newButton = findViewById<View>(R.id.retry)
        newButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.retry -> checkConnection()
        }
    }

    private fun checkConnection(): Boolean {
        /* ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()){
            return true;
        }*/
        if ((getSystemService("connectivity") as ConnectivityManager).activeNetworkInfo == null) {
            return true
        }
        val extras = intent.extras
        val classname = extras!!.getString("class")
        var destinationActivity: Class<*>? = null
        try {
            if (classname != null) {
                destinationActivity = Class.forName(classname)
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        val i = Intent(this, destinationActivity)
        startActivity(i)
        finish()
        return true
    }
}