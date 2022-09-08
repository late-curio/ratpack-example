package latecurio.ratpack;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class HelloHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.render("Hello Ratpack!");
    }
}
