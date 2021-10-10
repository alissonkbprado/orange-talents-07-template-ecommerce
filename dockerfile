FROM openjdk:11
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
# RUN java -jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080

