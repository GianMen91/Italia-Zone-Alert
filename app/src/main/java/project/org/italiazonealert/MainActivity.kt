package project.org.italiazonealert

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import java.io.IOException
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var map: String
    private lateinit var progressDialog: ProgressDialog
    private lateinit var webView: WebView
    private lateinit var textRulesActive: TextView
    private lateinit var lastupdate: String
    private lateinit var imageView: ImageView



    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        imageView = findViewById(R.id.whiteMap)

        textRulesActive = findViewById(R.id.textRulesActive)
        val file = SaveDataOnDevice.readFromInternalStorage(applicationContext)
        if (isNetworkConnected) {
            map = ""
            lastupdate = ""
            GetMap().execute()
        } else {
            if (file.isNullOrEmpty()) {
                if (!isNetworkConnected) {
                    val i = Intent(this, NoConnectionActivity::class.java)
                    i.putExtra("class", "project.org.italiazonealert.MainActivity")
                    startActivity(i)
                    finish()
                } else {
                    map = ""
                    lastupdate = ""
                    GetMap().execute()
                }
            } else {
                map = file
            }
        }
        webView.getSettings().javaScriptEnabled = true
        webView.getSettings().loadWithOverviewMode = true
        webView.getSettings().builtInZoomControls = true
        webView.getSettings().useWideViewPort = true
        webView.addJavascriptInterface(WebAppInterface(), "Android")
        webView.loadDataWithBaseURL(
            null,
            "<html><head><style>img{display:none;} body{margin-left: 20;text-align:center;}</style></head><body>$map</body></html>",
            "text/html",
            "UTF-8",
            "about:blank"
        )
    }

    private inner class GetMap : AsyncTask<Void?, Void?, Void?>() {
        override fun onPreExecute() {
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setMessage(getString(R.string.loading))
            progressDialog.setCancelable(false)
            progressDialog.show()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                val url =
                    "http://www.governo.it/it/articolo/domande-frequenti-sulle-misure-adottate-dal-governo/15638?gclid=CjwKCAiAmrOBBhA0EiwArn3mfGOUsxe4VKo9AdCTUuMvpM_AjFgSC7U4_pUHY2y-7VX4rWrtTllZBRoCcq0QAvD_BwE#zone"
                map = Jsoup.connect(url).get().getElementsByClass("contenitore_svg").toString()
                val el =
                    Jsoup.connect(url).get().getElementsContainingOwnText("PAGINA AGGIORNATA AL")
                lastupdate = el.toString()
                if (lastupdate != "") {
                    lastupdate = lastupdate.substring(
                        lastupdate.indexOf("<p>") + 3,
                        lastupdate.indexOf("</p>")
                    )
                    lastupdate = lastupdate.replace("&#8216;", "'").replace("&#8217;", "'")
                        .replace("&#038;", "&").replace("&#8211;", "-").replace("&#8211;", "-")
                        .replace("&nbsp;", " ")
                    lastupdate = lastupdate.replace("PAGINA AGGIORNATA AL ", "")
                    lastupdate = lastupdate.substring(0, 4)
                        .uppercase(Locale.getDefault()) + lastupdate!!.substring(4).lowercase(
                        Locale.getDefault()
                    )
                    if (Locale.getDefault().language == "en") {
                        lastupdate = lastupdate!!.replace("Gennaio", "January")
                            .replace("Febbraio", "February")
                            .replace("Marzo", "March")
                            .replace("Aprile", "April")
                            .replace("Maggio", "May")
                            .replace("Giugno", "June")
                            .replace("Luglio", "July")
                            .replace("Agosto", "August")
                            .replace("Settembre", "September")
                            .replace("Ottobre", "October")
                            .replace("Novembre", "November")
                            .replace("Dicembre", "December")
                    }
                }
                SaveDataOnDevice.saveToInternalStorage(applicationContext, map)
            } catch (e: IOException) {
                e.printStackTrace()
                map="error"
            }
            return null
        }

        @SuppressLint("SetTextI18n", "SetJavaScriptEnabled", "AddJavascriptInterface")
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            if (progressDialog.isShowing) {
                progressDialog!!.dismiss()
            }
            if(map!="error"){
                if (lastupdate != "") {
                    textRulesActive.text =
                        """${textRulesActive.text}
${getString(R.string.fromDate)} $lastupdate)"""
                }
                webView.settings.javaScriptEnabled = true
                webView.settings.loadWithOverviewMode = true
                webView.settings.builtInZoomControls = true
                webView.settings.useWideViewPort = true
                webView.addJavascriptInterface(WebAppInterface(), "Android")
                webView.loadDataWithBaseURL(
                    null,
                    "<html><head><style>img{display:none;} body{margin-left: 20;text-align:center;}</style></head><body>$map</body></html>",
                    "text/html",
                    "UTF-8",
                    "about:blank"
                )
            }else{
                textRulesActive.text = applicationContext.getString(R.string.rules_not_available)
                webView.visibility = View.GONE
                imageView.visibility = View.VISIBLE

            }

        }
    }

    inner class WebAppInterface {
        /** Show a toast from svg  */
        @JavascriptInterface
        fun showToast(toast: String?) {
            Toast.makeText(this@MainActivity, toast, Toast.LENGTH_SHORT).show()
        }
    }

    private val isNetworkConnected: Boolean
        get() {
            val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.rule_zone -> {
                val zoneActivityIntent = Intent(this, ZoneActivity::class.java)
                startActivity(zoneActivityIntent)
                true
            }

            R.id.info_app -> {
                val infoActivityIntent = Intent(this, InfoActivity::class.java)
                startActivity(infoActivityIntent)
                true
            }

            R.id.links -> {
                val usefulLinksActivityIntent = Intent(this, UsefulLinksActivity::class.java)
                startActivity(usefulLinksActivityIntent)
                true
            }

            R.id.shareit -> {
                val googlePlayLink =
                    "https://play.google.com/store/apps/details?id=project.org.italiazonealert"
                val intent = Intent("android.intent.action.SEND")
                intent.type = "text/plain"
                intent.putExtra("android.intent.extra.TEXT", googlePlayLink)
                startActivity(Intent.createChooser(intent, resources.getString(R.string.sharing)))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}