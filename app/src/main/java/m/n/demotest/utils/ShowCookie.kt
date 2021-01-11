package m.n.demotest.utils

import android.app.Activity
import m.n.demotest.ui.home.MainActivity
import org.aviran.cookiebar2.CookieBar

fun showCookie(activity: Activity, title: String, message: String) {
    CookieBar.build(activity)           // Provide activity, using this@MainActivity / getActivity() or otherwise
            .setTitle(title)         // String resources are also supported
            .setMessage(message)     // i.e. R.string.message
            .setDuration(3* 1000)
            .show()
}
