# zuora_hw

#Description
This is a simple java web app developed by Jingxiao Gu.

#Assumption
1. Only leaf requirement can be deleted.
2. Owners can be added and deleted after the requirement is created.

#Note
1. We use maven and MongoDB
2. We use BFS(Breadth-First-search) to return all requirements that a user has access to.
3. This app is runnable. Please put your own configuration in "src/main/java/config/Config.java" if you want to run it.
4. You can use both "postman" and test file(/src/test/java) to test this app.

#How to run with maven
1. mvn clean install
2. mvn jetty:run

