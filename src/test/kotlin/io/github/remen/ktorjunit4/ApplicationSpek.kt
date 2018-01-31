import io.github.remen.ktorjunit4.Application
import org.apache.http.HttpResponse
import org.apache.http.client.fluent.Request
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it


class ApplicationSpek : Spek({
    val application = Application()
    beforeGroup {
        application.start()
    }

    afterGroup {
        application.stop()
    }

    describe("GET /health") {
        lateinit var response: HttpResponse
        beforeGroup {
            response = Request
                .Get("http://localhost:8080/health")
                .execute()
                .returnResponse()
        }

        it("is successful") {
            assertThat(response.statusLine.statusCode).isEqualTo(200)
        }

        it("has Content-type application/json; charset=UTF-8") {
            assertThat(response.getFirstHeader("Content-Type").value).isEqualTo("application/json; charset=UTF-8")
        }

        it("returns {\"status\" : \"OK\"}") {
            val map : Map<String, String> = response.entity.content.parseJson()
            assertThat(map).isEqualTo(mapOf("status" to "OK"))
        }
    }
})
