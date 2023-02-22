Start the activemq container
============================

docker build -t my-activemq-image .
docker run -d --name activemq -p 8161:8161 my-activemq-image

Login to the web console
========================

http://localhost:8161/admin/ 
user/password - admin
