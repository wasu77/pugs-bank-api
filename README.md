# pugs-bank-api
REST Api to fetch account balance for given customer

Two endpoints exposed:

To fetch all customers data (id, name)
GET /customers

To fetch account balance for given customer
GET /customers/{customer-id}/balance

Acceptance criteria:
- return account balance in USD
- store account balance in PLN (in Polish Grosze)

TODO:
+ Swagger Doc? Spring Rest Docs?