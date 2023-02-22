Overview
========

Ves generator that receives request to generate an event(s) and sends them to the VES listener.

Dependencies
============

ves client

Build images
============

docker build -t mongo -f Dockerfile_mongo .
docker build -t ves-gen -f Dockerfile_gen .

Execute
=======

docker run -p 27017:27017 --name mongo mongo
docker run -p 5000:5000 --link ves-listener --link mongo --name ves-gen ves-gen 

Or if you want to overwrite any of the parameters without rebuilding the image:

docker run -p 27017:27017 -v $(pwd)/db:/docker-entrypoint-initdb.d -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=zXcVbN123! -e MONGO_INITDB_DATABASE=pnf_simulator --name mongo mongo

docker run -p 5000:5000 -v $(pwd)/logs:/var/log -v $(pwd)/templates:/app/templates -v $(pwd)/store:/app/store -v $(pwd)/properties/application.properties:/app/application.properties --link ves-listener --link mongo --restart on-failure --env USE_CERTIFICATE_FOR_AUTHORIZATION=false --name ves-gen ves-gen 


