package pl.mwezyk.pugsbankapi.customer

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customers")
@Api
class CustomerController(val customerService: CustomerService) {

    @ApiOperation(value = "Get list of all customers")
    @GetMapping
    fun getCustomers(): List<Customer> {
        return customerService.getAllCustomers()
    }

    @ApiOperation(value = "Get customer with provided customerId")
    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable customerId: String): Customer {
        return customerService.getCustomerById(customerId.toLong())
    }

}