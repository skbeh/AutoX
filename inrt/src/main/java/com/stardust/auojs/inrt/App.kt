package com.stardust.auojs.inrt

import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.fanjun.keeplive.KeepLive
import com.fanjun.keeplive.config.ForegroundNotification
import com.fanjun.keeplive.config.ForegroundNotificationClickListener
import com.linsh.utilseverywhere.Utils
import com.stardust.app.GlobalAppContext
import com.stardust.auojs.inrt.autojs.AutoJs
import com.stardust.auojs.inrt.autojs.GlobalKeyObserver
import com.stardust.auojs.inrt.pluginclient.AutoXKeepLiveService
import com.stardust.autojs.core.ui.inflater.ImageLoader
import com.stardust.autojs.core.ui.inflater.util.Drawables


/**
 * Created by Stardust on 2017/7/1.
 */

class App : Application() {

    var TAG = "inrt.application";
    override fun onCreate() {
        super.onCreate()
        GlobalAppContext.set(this)
        Utils.init(this);
        AutoJs.initInstance(this)
        GlobalKeyObserver.init()
        Drawables.setDefaultImageLoader(object : ImageLoader {
            override fun loadInto(imageView: ImageView, uri: Uri) {
                Glide.with(this@App)
                        .load(uri)
                        .into(imageView)
            }

            override fun loadIntoBackground(view: View, uri: Uri) {
                Glide.with(this@App)
                        .load(uri)
                        .into(object : SimpleTarget<Drawable>() {
                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
                                view.background = resource
                            }
                        })
            }

            override fun load(view: View, uri: Uri): Drawable {
                throw UnsupportedOperationException()
            }

            override fun load(view: View, uri: Uri, drawableCallback: ImageLoader.DrawableCallback) {
                Glide.with(this@App)
                        .load(uri)
                        .into(object : SimpleTarget<Drawable>() {
                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
                                drawableCallback.onLoaded(resource)
                            }
                        })
            }

            override fun load(view: View, uri: Uri, bitmapCallback: ImageLoader.BitmapCallback) {
                Glide.with(this@App)
                        .asBitmap()
                        .load(uri)
                        .into(object : SimpleTarget<Bitmap>() {
                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                                bitmapCallback.onLoaded(resource)
                            }
                        })
            }
        })
    }

}
