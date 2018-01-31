package io.github.remen.ktorjunit4.jackson

import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.ApplicationFeature
import io.ktor.content.OutgoingContent
import io.ktor.content.transformDefaultContent
import io.ktor.http.ContentType
import io.ktor.http.withCharset
import io.ktor.response.ApplicationSendPipeline
import io.ktor.util.AttributeKey

/**
 * Due to
 */
class JacksonFeature {
    class Configuration {
        var objectMapper = DefaultObjectMapper
    }
    companion object Feature : ApplicationFeature<ApplicationCallPipeline, Configuration, JacksonFeature> {
        override val key: AttributeKey<JacksonFeature> = AttributeKey("JacksonFeature")

        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): JacksonFeature {
            val objectMapper = Configuration().apply(configure).objectMapper

            pipeline.sendPipeline.intercept(ApplicationSendPipeline.Render) {
                val rendered = transformDefaultContent(subject) ?: JsonContent(objectMapper.writeValueAsBytes(subject))
                proceedWith(rendered)
            }
            return JacksonFeature()
        }
    }
    class JsonContent(private val bytes: ByteArray) : OutgoingContent.ByteArrayContent() {
        override fun bytes(): ByteArray {
            return bytes
        }

        override val contentType: ContentType = ContentType.Application.Json.withCharset(Charsets.UTF_8)
    }
}
