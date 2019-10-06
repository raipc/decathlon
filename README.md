[![Build Status](https://api.travis-ci.org/raipc/decathlon.svg?branch=master)](https://travis-ci.org/raipc/decathlon)
[![License](https://img.shields.io/badge/License-Apache%202-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
# decathlon

Decathlon score calculator. Reads athletes performance results from a CSV file and writes them to an XML file sorted by total score.

Usage:

```sh
mvn package # build jar
java -jar target/decathlon-1.0-SNAPSHOT.jar results.csv output.xml
```

Testing:

```sh
mvn test # run tests
mvn allure:report jetty:run -Djetty.port=1234 # create Allure report for testing results and run a webserver on port 1234 to view them
```
