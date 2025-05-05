#!/bin/bash
java -jar /app/appointment-0.0.1-SNAPSHOT.jar &
java -jar /app/notification-0.0.1-SNAPSHOT.jar
wait -n