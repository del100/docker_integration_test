Overview
--------

Simple REST applivation that receives a post request on the following url:

http://localhost:8080/eventListener/v1

It then sends an event to the JMS queue - myQueue

Dependencies
------------

- Activemq broker must be running in a container named 'activemq'
- Activemq container must expose the broker on port 61616

Update properties file if port or container name change:

src/main/resources/application.properties 
active-mq.broker-url=tcp://activemq:61616
active-mq.queue=myQueue

Execute
-------

docker build -t ves-listener .
docker run --name ves-listener --link activemq -p 8080:8080 ves-listener

Test
----

Send post request to url 'http://localhost:8080/eventListener/v1'

Sample VES event that must be passed as payload

{ "event": {
        "commonEventHeader": {
            "version": "4.1",
            "vesEventListenerVersion": "7.2.1",
            "domain": "fault",
            "eventName": "Fault_Vscf:Acs-Ericcson_PilotNumberPoolExhaustion",
            "eventId": "fault0000245",
            "sequence": 1,
            "priority": "High",
            "reportingEntityId": "cc305d54-75b4-431b-adb2-eb6b9e541234",
            "reportingEntityName": "ibcx0001vm002oam001",
            "sourceId": "de305d54-75b4-431b-adb2-eb6b9e546014",
            "sourceName": "scfx0001vm002cap001",
            "nfVendorName": "Ericsson",
            "nfNamingCode": "scfx",
            "nfcNamingCode": "ssc",
            "startEpochMicrosec": 1413378172000000,
            "lastEpochMicrosec": 1413378172000000,
            "timeZoneOffset": "UTC-05:30"
        },
        "faultFields": {
            "faultFieldsVersion": 4.0,
            "alarmCondition": "PilotNumberPoolExhaustion",
            "eventSourceType": "other",
            "specificProblem": "Calls cannot complete - pilot numbers are unavailable",
            "eventSeverity": "CRITICAL",
            "vfStatus": "Active",
            "alarmAdditionalInformation": {
                "PilotNumberPoolSize": "1000"
            }
        }
    }
}
 
