package me.kifio.organize

import platform.Foundation.NSLog

actual object CommonLogger {
    actual fun log(logLevel: LogLevel, tag: String, msg: String) {
        NSLog("$tag: $msg")
    }
}