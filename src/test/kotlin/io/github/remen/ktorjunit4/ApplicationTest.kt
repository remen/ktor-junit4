//package io.github.remen.ktorjunit4
//
//import org.assertj.core.api.Assertions.assertThat
//import org.asynchttpclient.AsyncHandler
//import org.asynchttpclient.AsyncHttpClient
//import org.asynchttpclient.BoundRequestBuilder
//import org.asynchttpclient.Dsl.asyncHttpClient
//import org.junit.*
//import kotlin.coroutines.experimental.suspendCoroutine
//
//
//class ApplicationTest {
//    val asyncHttpClient : AsyncHttpClient = asyncHttpClient()
//
//    fun BoundRequestBuilder.await() {
//        suspendCoroutine<> { cont ->
//            this.execute(object: AsyncHandler {
//
//            });
//
//        }
//
//    }
//
//    companion object {
//        private val application: Application = Application()
//        @BeforeClass @JvmStatic
//        fun setUp() {
//            application.start()
//        }
//
//        @AfterClass @JvmStatic
//        fun tearDown() {
//            application.stop()
//        }
//    }
//
//    @Test
//    fun `GET health succeeds`() {
//        val response = asyncHttpClient
//            .prepareGet("http://localhost:8080")
//            .execute()
//            .get()
//
//        assertThat(response.statusCode).isEqualTo(200)
//    }
//}
