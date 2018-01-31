package io.github.remen.ktorjunit4

import io.github.remen.ktorjunit4.jackson.JacksonFeature
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.concurrent.TimeUnit

class Application {
    private val server : ApplicationEngine
    init {
        server = embeddedServer(Netty, 8080) {
            install(JacksonFeature)

            routing {
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
