# Goals

## Compile, Run the project and inspect the page

Note - This project is a Frontend app and need a backend API to work (see next section about using a backend) 

```
# download dependencies (one time operation or do it again when new libraries are added)
npm install 
# compile and hot-reload for development
npm run serve
# inspect page contents
```

## Run a backend demo API locally 

Locally checkout this repo: https://gitlab.com/sysdev-tutorials/springboot/auth_demo/-/tree/test.
Switch to 'test' branch. Then from the project folder, run

```
mvn spring-boot:run
```

Note the context path and port number given src/main/resources/application.properties file.
Correct port should be used from frontend part.
