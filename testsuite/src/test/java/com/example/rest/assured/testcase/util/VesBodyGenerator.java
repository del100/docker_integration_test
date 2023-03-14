
package com.example.rest.assured.testcase.util;

import org.json.JSONObject;

public class VesBodyGenerator {
    private static String VES_LISTENER_URI = "http://ves-listener:8080/eventListener/v1";

    public static String singleEvent() {
        final JSONObject commonEventHeaderObject = new JSONObject();
        commonEventHeaderObject.put("eventId", "#RandomString(20)");
        commonEventHeaderObject.put("sourceName", "PATCHED_sourceName");
        commonEventHeaderObject.put("version", 3.0);

        final JSONObject eventObject = new JSONObject();
        eventObject.put("commonEventHeader", commonEventHeaderObject);

        final JSONObject requestBody = new JSONObject();
        requestBody.put("vesServerUrl", VES_LISTENER_URI);
        requestBody.put("event", eventObject);

        return requestBody.toString();
    }
}
