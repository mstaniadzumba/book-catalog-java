#compile the project
build:
	mvn compile

test: build
	mvn test

package: build
	mvn package

clean:
	mvn clean

run:
mvn exec:java

all: clean build test package

docker-build:
	docker build -t book-catalog .

docker-run:
	docker run --rm -p 8080:8080 book-catalog