
package com.example.rest.assured.testcase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.junit.Test;

import com.example.rest.assured.testcase.util.VesBodyGenerator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Contains integration tests for the VES Listener.
 */
public class VesListenerIT extends BaseClass {

    private static String VES_SIMULATOR_URI = "http://localhost:5000/simulator/event";

    @Test
    public void WHEN_singleVesEventGenerated_THEN_singleJmsMessageProduced() throws JMSException {

        // WHEN - a single VES event is generated by the simulator
        final Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(VesBodyGenerator.singleEvent())
                .post(VES_SIMULATOR_URI);

        assertTrue(response.getStatusCode() == 202);

        // THEN - a JMS message is produced by the VES listener
        final ObjectMessage vesMessage = (ObjectMessage) consumer.receive(1000);
        assertNotNull("No message received", vesMessage);
        assertTrue("Received message is not an ObjectMessage", vesMessage instanceof ObjectMessage);
    }
}