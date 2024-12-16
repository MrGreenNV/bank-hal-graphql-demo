# Bank Service

---

---

## BUILD and RUN

### **START RABBIT_MQ and POSTGRESQL and PG_ADMIN**
```
cd <dir with docker-compose.yaml>
docker-compose up -d
```

### **STOP RABBIT_MQ and POSTGRESQL and PG_ADMIN**
```
docker-compose down -v
```

### **BUILD PROJECT**
```
cd <dir with docker-compose.yaml>
mvn clean install
```

### **START REST_SERVICE**
```
run BankApplication
```

### **START GRPC_SERVICE**
```
run GrpcServiceApplication
```
---
## REST

REST + HAL - <http://localhost:9595/>

SWAGGER - <http://localhost:9595/swagger-ui/index.html#/>

GRAPHQL - <http://localhost:9595/graphiql?path=/graphql>

RABBIT_MQ - <http://localhost:15672>
```
login - guest
password - guest
queue_name - reports
```

PG_ADMIN - <http://localhost:8080>
```
login - admin@admin.com
password - admin
```
Add server to pgAdmin:
```
name - pgs
host - db_postgres
username - posgres
password - posgres
```

QUERY:
```
query {
  allPeople {
    id,name, accounts {
      id,cards {
        id, endDate
      }
    }
  }
}
```
```
mutation {
  createPerson(name: "create_in_mutation", age: 52, email: "mutation@mail.ru")
  {name, age, email}
}
```

---

Получение отчета с применением gRPC для пользователя с id = 1:
```
GET <http://localhost:9595/grpc/reports?personId=1>
```
*Файл с отчётом будет доступен по полученному Path*

---

Получение отчета с использованием RabbitMQ для пользователя с id = 1:
```
POST <http://localhost:9595/ampq/reports>
body: 1
type: row -> text
```
*Ссылка будет отображена в логах gRPC сервиса*

---