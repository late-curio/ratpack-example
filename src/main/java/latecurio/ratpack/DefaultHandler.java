package latecurio.ratpack;

import io.netty.buffer.PooledByteBufAllocator;
import ratpack.exec.Promise;
import ratpack.func.Pair;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;
import ratpack.server.ServerConfig;

import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class DefaultHandler implements Handler {
    private final HttpClient httpClient;

    public DefaultHandler() throws Exception {
        this.httpClient = HttpClient.of(httpClientSpec -> httpClientSpec.poolSize(10)
                .connectTimeout(Duration.of(60, ChronoUnit.SECONDS))
                .maxContentLength(ServerConfig.DEFAULT_MAX_CONTENT_LENGTH)
                .responseMaxChunkSize(16384)
                .readTimeout(Duration.of(60, ChronoUnit.SECONDS))
                .byteBufAllocator(PooledByteBufAllocator.DEFAULT));
    }

    @Override
    public void handle(Context ctx) {
        Map<String, String> queryParams = ctx.getRequest().getQueryParams();
        String name = queryParams.get("name");
        URI uri = URI.create("http://localhost:5050/hello?name=" + name);
        Promise<Pair<ReceivedResponse, ReceivedResponse>> helloResponse = httpClient.get(uri)
                .right(httpClient.get(URI.create("http://localhost:5050/goodbye?name=" + name)));
        helloResponse.map(pair -> pair.left.getBody().getText() + pair.right.getBody().getText())
                .then(responseText -> ctx.getResponse().send(responseText));
    }
}
