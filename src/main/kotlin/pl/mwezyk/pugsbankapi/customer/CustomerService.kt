package pl.mwezyk.pugsbankapi.customer

import org.springframework.stereotype.Service

@Service
class CustomerService(val repository: CustomerRepository) {

    fun getAllCustomers() : List<Customer> {
        return repository.getAllCustomers()
    }

    fun getCustomerById(customerId: Long): Customer {
        return repository.getCustomerById(customerId)
    }

}