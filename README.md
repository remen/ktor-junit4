# Why kotlin?

Although both Java and Javascript are very safe choices, we have decided to adopt Kotlin full-heartedly, due to:

* Increased developer happiness
* Easier and more readable handling of non-blocking code compared to Java
* Interoperability with Java and the JVM

## Coroutines

Coroutines allows you to write non-blocking code in an imperative style. In other words, you can replace this:

```java

class Foobar {
    public CompletableFuture<Long> visitors(String id) {
        return findDocumentById(id).thenCompose(document -> 
                getVisitorsForUrl(document.getUrl())
        );
    }
}
```

with this:

````kotlin

class Foobar {
    suspend fun visitors(id: String) {
        val doc = findDocumentById(id)
        return getVisitorsForUrl(doc.url)
    }
}
````

## Developer happiness

```kotlin
// Null safe navigation
if(findUserByName("Annie")?.age == 32) {
    ...
}


// Immutable data classes
data class User(val name: String, val age: Int)

val user = User("Annie", 32)
val olderUser = user.copy(age = user.age + 1)
```

and much more.

## What alternatives were considered?

* Java
* Javascript

## Links

* https://kotlinlang.org/docs/reference/index.html
* https://kotlinlang.org/docs/tutorials/koans.html


# Why ktor.io?

In fact, any simple http server would do. In the end ktor.io was chosen due to its native support for kotlin coroutines.

## What alternatives were considered?

* [vert.x](http://vertx.io/docs/vertx-web/java/)
* [undertow](http://undertow.io/)
* [spring-boot](https://projects.spring.io/spring-boot/) - Too "heavy" for our liking

# Why junit5?

Although very new, and native gradle support [is not ready yet](https://github.com/gradle/gradle/pull/4116),
Junit5 integrates well with Kotlin and IntelliJ, allows for nested test classes and is a future-proof choice.

## What alternatives were considered?

* [http://junit.org/junit4/](Junit4) - `@BeforeClass` is awkward with Kotlin
* [http://spockframework.org/spock/docs/1.1/index.html](Spock) - The dynamic nature of the language isn't worth it
* [http://spekframework.org/](Spek) - A very good choice, but I worry about its future and IntelliJ-integration

## Links

* [Junit 5 User Guide](http://junit.org/junit5/docs/current/user-guide/)
