
package com.example.testcase;

import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MyIT {

    private static String VES_SIMULATOR_URI = "http://localhost:5000/simulator/event";
    private static String VES_LISTENER_URI = "http://ves-listener:8080/eventListener/v1";

    @Test
    public void testPostRequest() {
        final JSONObject requestBody = new JSONObject();
        requestBody.put("vesServerUrl", VES_LISTENER_URI);

        final JSONObject eventObject = buildJsonBody();

        requestBody.put("event", eventObject);

        final Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post(VES_SIMULATOR_URI);

        assertTrue(response.getStatusCode() == 202);
    }

    private JSONObject buildJsonBody() {
        final JSONObject commonEventHeaderObject = new JSONObject();
        commonEventHeaderObject.put("eventId", "#RandomString(20)");
        commonEventHeaderObject.put("sourceName", "PATCHED_sourceName");
        commonEventHeaderObject.put("version", 3.0);

        final JSONObject eventObject = new JSONObject();
        eventObject.put("commonEventHeader", commonEventHeaderObject);

        return eventObject;
    }
}
