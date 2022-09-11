package me.kifio.organize

import android.util.Log

actual object CommonLogger {
    actual fun log(logLevel: LogLevel, tag: String, msg: String) {
        when (logLevel) {
            LogLevel.ERR -> Log.e(tag, msg)
            LogLevel.INFO -> Log.i(tag, msg)
            LogLevel.WARN -> Log.w(tag, msg)
        }
    }
}