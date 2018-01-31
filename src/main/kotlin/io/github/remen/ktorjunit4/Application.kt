package io.github.remen.ktorjunit4

import io.github.remen.ktorjunit4.jackson.JacksonFeature
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.event.Level
import java.util.concurrent.TimeUnit

class Application {
    data class ResponseMessage(val message: String)

    private val server : ApplicationEngine
    init {
        server = embeddedServer(Netty, 8080) {
            install(JacksonFeature)
            install(CallLogging) {
                level = Level.INFO
            }

            routing {
                get("/") {
                    call.respond(ResponseMessage("Hello World!"))
                }
                get("/health") {
                    call.respond(mapOf("status" to "OK"))
                }
            }
        }
    }

    fun start() {
        server.start(wait = false)
    }

    fun stop() {
        server.stop(0, 0, TimeUnit.SECONDS)
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application().start()
        }
    }
}
