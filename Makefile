#compile the project
build:
	mvn compile

test: build
	mvn test

package:
	mvn package

clean:
	mvn clean

run:
mvn exec:java

all: clean build test package