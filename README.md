# greetings-knative Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

Follow the instructions located in Dockerfile files located in this repository.
If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

### Windows specificities

Follow this guide to install GraalVM and native-image (https://medium.com/graalvm/using-graalvm-and-native-image-on-windows-10-9954dc071311)

Once this is done, all maven and docker commands should be executed in a cmd augmented with VS capabilities:
"C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\VC\Auxiliary\Build\vcvars64.bat"

## Demonstration

- Build your image and push it to Docker Hub 
- Start Kubernetes from Docker Desktop
- In namespace default, create a new knative service (careful, knative does not like local repositories, use Docker Hub for now)
```
kubectl -n default apply -f greeting-service.yaml
```
- Observe our freshly-created service, get its URL 
```
kubectl -n default get service.serving.knative.dev
```
- Observe the pods running and terminating according to the requests
```
kubectl -n default get po
```
- Call http://xxx/hello to get an answer from the service
- Generate some load using `hey` (30 sec of 50 requests)
```
hey -z 30s -c 50 http://greeting-service.default.127.0.0.1.sslip.io/hello
```
- Update the env var
```
kn service update greeting-service --env GREETING_NAME=friends
```
- See how the change has been reflected by calling the endpoint again
- Look at the different revisions
```
kn revisions list
```
- Split the traffic between revisions
```
kn service update greeting-service --traffic greeting-service-00001=50 --traffic @latest=50
```
- Calling the endpoint multiple times will now end up with different behaviors
- Clean the service
```
kubectl -n default delete service.serving.knative.dev/greeting-service
```

## Useful documentation
- https://knative.dev/docs/serving/autoscaling/