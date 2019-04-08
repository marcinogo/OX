#!/bin/bash

mvn clean install -q -DskipTests
java -cp target/OX-0.1.jar ogo.marcin.ox.main.Main