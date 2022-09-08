package latecurio.ratpack;

import ratpack.server.RatpackServer;

public class Main {

    public static void main(String[] args) throws Exception {
        RatpackServer.start(spec -> spec
                .handlers(chain -> chain
                        .get(new DefaultHandler())
                        .get("hello", new HelloHandler())
                        .get("goodbye", new GoodbyeHandler() )
                )
        );
    }
}
