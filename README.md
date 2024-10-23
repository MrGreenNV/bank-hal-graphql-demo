HAL - http://localhost:9595/
Database H2 - http://localhost:9595/h2-console/ (jdbc:h2:file:./data/bank_db)
SWAGGER - http://localhost:9595/swagger-ui/index.html#/

GRAPHQL - http://localhost:9595/graphiql?path=/graphql

QUERY:

# query {
#   allPeople {
#     id,name, accounts {
#       id,cards {
#         id, endDate
#       }
#     }
#   }
# }

# mutation {
#   createPerson(name: "create_in_mutation", age: 52, email: "mutation@mail.ru")
#   {name, age, email}
# }