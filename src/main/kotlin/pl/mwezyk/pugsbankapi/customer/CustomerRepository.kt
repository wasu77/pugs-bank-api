package pl.mwezyk.pugsbankapi.customer

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, String> {

    @Query("SELECT * FROM CUSTOMERS")
    fun getAllCustomers(): List<Customer>

    fun getCustomerById(id: Long): Customer
}