package me.kifio.organize

// Для разных уровней логирования
enum class LogLevel {
    ERR, WARN, INFO
}

expect object CommonLogger {
    fun log(logLevel: LogLevel, tag: String, msg: String)
}