package pl.mwezyk.pugsbankapi.customer

import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import pl.mwezyk.pugsbankapi.currency.CurrencyService
import kotlin.test.assertEquals


class CustomerServiceTest {

    @Test
    fun getAllCustomersTest() {
        val repositoryMock: CustomerRepository = mock()
        whenever(repositoryMock.getAllCustomers()).thenReturn(provideCustomersList_withOnlyOneCustomer())

        val currencyServiceMock: CurrencyService = mock()
        whenever(currencyServiceMock.convertToCurrency(any(), any())).thenReturn("200")

        val customerService = CustomerService(repositoryMock, currencyServiceMock)
        val customers = customerService.getAllCustomers()

        assertEquals(1, customers.size)
        assertEquals("200", customers.first().accountBalance)

        verify(repositoryMock).getAllCustomers()
    }


    private fun provideCustomersList_withOnlyOneCustomer(): List<CustomerEntity> {
        return listOf(CustomerEntity(1, "Test 1", 0))
    }
}
