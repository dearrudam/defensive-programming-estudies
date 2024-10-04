package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.net.http.HttpClient;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class RegistrationResourceTest {

    @Test
    void shouldProcessSuccessfully() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body("""
                        {
                        	"email": "dearrudam@gmail.com",
                        	"data": {
                        		"name": "Maximillian",
                        		"age": 42
                        	}
                        }
                        """)
                .post("/api/registration")
                .then()
                .statusCode(204);
    }

    // bad scenarios should be implemented

}