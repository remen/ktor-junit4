import com.fasterxml.jackson.databind.JsonNode
import io.github.remen.ktorjunit4.jackson.DefaultObjectMapper
import java.io.InputStream

inline fun <reified T> InputStream.parseJson(): T {
    return if (T::class == JsonNode::class) {
        DefaultObjectMapper.readTree(this)!! as T
    } else {
        DefaultObjectMapper.readValue(this, T::class.java)
    }
}
