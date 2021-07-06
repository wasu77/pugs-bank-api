package pl.mwezyk.pugsbankapi.customer

import org.springframework.stereotype.Service
import pl.mwezyk.pugsbankapi.currency.CurrencyService

@Service
class CustomerService(val repository: CustomerRepository, val currencyService: CurrencyService) {

    fun getAllCustomers() : List<Customer> {
        return repository.getAllCustomers().map(::mapToCustomer)
    }

    fun getCustomerById(customerId: Long): Customer {
        return mapToCustomer(repository.getCustomerEntityByIdEquals(customerId))
    }

    fun mapToCustomer(customerEntity: CustomerEntity): Customer {
        return Customer(customerEntity.id, customerEntity.name, currencyService.convertToCurrency(customerEntity.balance))
    }

}