import io.github.remen.ktorjunit4.Application
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger(Application::class.java)
    logger.trace("I don't care")
    logger.debug("Whatever")
    logger.info("You've piqued my interest")
    logger.warn("Ok, no I'm worried")
    logger.error("Oh noes!!!")

    println("Hello World!")

}

data class User(val name: String, val age: Int)

fun foobar() {
    val user = User("Annie", 32)
    val olderUser = user.copy(age = user.age + 1)
}




