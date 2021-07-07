# pugs-bank-api
REST Api to fetch account balance for given customer

Two endpoints exposed:

To fetch all customers data (id, name, account balance)
GET /customers

To fetch account balance for given customer
GET /customers/{customer-id}

Customer account balance is returned as USD
Customer account balance is stored in database as PLN (Grosze)