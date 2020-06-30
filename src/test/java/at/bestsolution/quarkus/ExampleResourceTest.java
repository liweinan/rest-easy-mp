package at.bestsolution.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.builder.MultiPartSpecBuilder;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.io.InputStream;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testProviderTest() {
        given()
            .queryParam("metaData", "{}")
          .when().get("/hello/provider")
          .then()
             .statusCode(200)
             .body(is("barfoo"));
    }

    @Test
    public void multiPartClass() {
        var in = getClass().getClassLoader().getResourceAsStream("dummy.txt");
        given()
            .contentType("multipart/form-data")
            .multiPart(new MultiPartSpecBuilder(in).controlName("document").fileName("test.docx").build())
            .multiPart(new MultiPartSpecBuilder("{ \"metaInfo\" : \"foobar\" }").controlName("metaData").mimeType("application/json").build())
            .when()
            .post("/hello/multi-class")
            .then()
                .statusCode(200)
                .body(is("foobar"));
    }

    @Test
    public void multiPartInterface() {
        var in = getClass().getClassLoader().getResourceAsStream("dummy.txt");
        given()
            .contentType("multipart/form-data")
            .multiPart(new MultiPartSpecBuilder(in).controlName("document").fileName("test.docx").build())
            .multiPart(new MultiPartSpecBuilder("{ \"metaInfo\" : \"foobar\" }").controlName("metaData").mimeType("application/json").build())
            .when()
            .post("/hello/multi-interface")
            .then()
                .statusCode(200)
                .body(is("barfoo"));
    }
}