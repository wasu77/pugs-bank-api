package pl.mwezyk.pugsbankapi.customer

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerEntity, String> {

    @Query("SELECT * FROM CUSTOMERS")
    fun getAllCustomers(): List<CustomerEntity>

    @Query("SELECT * FROM CUSTOMERS WHERE ID = :id")
    fun getCustomerEntityByIdEquals(id: Long): CustomerEntity
}