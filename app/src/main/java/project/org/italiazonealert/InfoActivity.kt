package project.org.italiazonealert

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Objects

class InfoActivity : AppCompatActivity() {
    private lateinit var versionCode: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        versionCode = findViewById(R.id.appVersion)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        try {
            versionCode.setText(
                versionCode.getText().toString() + " " +
                        Objects.requireNonNull(this).packageManager.getPackageInfo(
                            this.packageName,
                            0
                        ).versionName
            )
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_message -> {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("mailto:" + "giancarlo.mennillo@gmail.com")
                )
                intent.putExtra(Intent.EXTRA_SUBJECT, " ")
                intent.putExtra(Intent.EXTRA_TEXT, " ")
                startActivity(intent)
                return true
            }

            android.R.id.home -> {
                finish() // close this activity and return to preview activity (if there is any)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}