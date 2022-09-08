package latecurio.ratpack;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Map;

public class GoodbyeHandler implements Handler {
    @Override
    public void handle(Context ctx) {
        Map<String, String> queryParams = ctx.getRequest().getQueryParams();
        String name = queryParams.get("name");
        ctx.render(String.format("Goodbye %s!", name));
    }
}
