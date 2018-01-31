package io.github.remen.ktorjunit4.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class DefaultObjectMapper {
    companion object : ObjectMapper() {
        init {
            registerKotlinModule()
        }
    }
}
