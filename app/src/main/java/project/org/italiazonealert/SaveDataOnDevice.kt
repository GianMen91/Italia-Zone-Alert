package project.org.italiazonealert

import android.content.Context
import android.util.Log
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object SaveDataOnDevice {
    private const val STORAGE_FILENAME = "ITALIA ZONE ALERT DATA"
    fun saveToInternalStorage(context: Context, agreements: String?) {
        try {
            val fos = context.openFileOutput(STORAGE_FILENAME, Context.MODE_PRIVATE)
            val of = ObjectOutputStream(fos)
            of.writeObject(agreements)
            of.flush()
            of.close()
            fos.close()
        } catch (e: Exception) {
            Log.e("InternalStorage", e.message!!)
        }
    }

    fun readFromInternalStorage(context: Context): String {
        var agreements = ""
        val fis: FileInputStream
        try {
            fis = context.openFileInput(STORAGE_FILENAME)
            val oi = ObjectInputStream(fis)
            try {
                agreements = oi.readObject() as String
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
            oi.close()
        } catch (e: FileNotFoundException) {
            Log.e("InternalStorage", e.message!!)
        } catch (e: IOException) {
            Log.e("InternalStorage", e.message!!)
        }
        return agreements
    }
}