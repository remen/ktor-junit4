import java.util.concurrent.CompletableFuture;

public interface Application {
    public CompletableFuture<Document> findDocumentById(String id);
    public CompletableFuture<Long> getVisitorsForUrl(String url);


    public interface Document {
        public String getUrl();
    }

    public static void main(String[] args) {
// Immutable data classes
        data class User(val name: String, val age: Int)

        val user = User("Annie", 32)
        val olderUser = user.age + 1

    }
}
