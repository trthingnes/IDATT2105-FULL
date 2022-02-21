### Run docker image locally
Execute the following command.
```
docker run -p 8080:5000 registry.gitlab.com/sysdev-tutorials/springboot/auth_demo:basic-da977209
```

Then, test it using Postman or the CURL command
```
curl --location --request POST 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "user",
    "password": "pass"
}'
```

