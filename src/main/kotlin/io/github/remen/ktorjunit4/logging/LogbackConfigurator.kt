package io.github.remen.ktorjunit4.logging

import ch.qos.logback.classic.*
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.encoder.LayoutWrappingEncoder
import net.logstash.logback.encoder.LogstashEncoder

class LogbackConfigurator : BasicConfigurator() {
    override fun configure(lc: LoggerContext) {
        val consoleAppender = ConsoleAppender<ILoggingEvent>().apply {
            context = lc
            name = "console"
            encoder = if (System.getenv("LOG_FORMAT") == "json") {
                LogstashEncoder().apply {
                    start()
                }
            } else {
                LayoutWrappingEncoder<ILoggingEvent>().apply {
                    context = lc
                    layout = PatternLayout().apply {
                        context = lc
                        pattern = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %logger{36} - %msg%n"
                        start()
                    }
                    start()
                }

            }
            start()
        }

        lc.getLogger(Logger.ROOT_LOGGER_NAME).apply {
            level = Level.INFO
            addAppender(consoleAppender)
        }
    }
}
