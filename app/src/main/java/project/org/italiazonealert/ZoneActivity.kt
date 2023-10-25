package project.org.italiazonealert

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ZoneActivity : AppCompatActivity() {
    private lateinit var buttonYellow: Button
    private lateinit var buttonOrange: Button
    private lateinit var buttonRed: Button
    private lateinit var zoneTitle: TextView
    private lateinit var zoneDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zone)
        buttonYellow = findViewById(R.id.button_yellow_zone)
        buttonOrange = findViewById(R.id.button_orange_zone)
        buttonRed = findViewById(R.id.button_red_zone)
        zoneTitle = findViewById(R.id.zone)
        zoneDescription = findViewById(R.id.zone_description)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        buttonYellow.setOnClickListener {
            zoneTitle.setText(R.string.zona_gialla_oneline)
            zoneTitle.setTextColor(resources.getColor(R.color.zona_gialla_text))
            zoneDescription.setText(R.string.zona_gialla_text)
        }
        buttonOrange.setOnClickListener {
            zoneTitle.setText(R.string.zona_arancione_oneline)
            zoneTitle.setTextColor(resources.getColor(R.color.zona_arancione))
            zoneDescription.setText(R.string.zona_arancione_text)
        }
        buttonRed.setOnClickListener {
            zoneTitle.setText(R.string.zona_rossa_oneline)
            zoneTitle.setTextColor(resources.getColor(R.color.zona_rossa))
            zoneDescription.setText(R.string.zona_rossa_text)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}