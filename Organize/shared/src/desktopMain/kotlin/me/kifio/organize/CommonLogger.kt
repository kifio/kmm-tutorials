package me.kifio.organize

actual object CommonLogger {
    actual fun log(logLevel: LogLevel, tag: String, msg: String) {
        when (logLevel) {
            LogLevel.ERR -> System.err.println("$tag: $msg")
            else -> println("$tag: msg")
        }
    }
}