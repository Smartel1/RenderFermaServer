## Task service
This service can receive and handle tasks as well as return user's tasks list.

---

### Installation
Run boot application using gradle:
```shell script
 ./gradlew clean bootRun
```

---

### Usage
Register new user:
```
    POST /api/user
```
with json body (use Content-Type:application/json header)
```json
{
	"email":"john@doe.com",
	"password":"secret"
}
```

Post new task (attach basicAuth header, this endpoint is secured):
```
    POST /api/task
```


Get task's list (attach basicAuth header, this endpoint is secured):
```
    GET /api/task
```

