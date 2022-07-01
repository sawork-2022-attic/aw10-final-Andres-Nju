package gatling;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class PosTest extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8084")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");




    ScenarioBuilder scn =
            scenario("Pos_customer")
                    .exec(http("list_products").get("/products"))
                    .pause(1);
    {
        setUp(scn.injectOpen(atOnceUsers(500)).protocols(httpProtocol));
    }

}