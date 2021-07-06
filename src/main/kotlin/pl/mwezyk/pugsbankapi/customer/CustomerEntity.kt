package pl.mwezyk.pugsbankapi.customer

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("customers")
data class CustomerEntity(@Id val id: Long,
                          @Column("cust_name") val name: String,
                          val balance: Long)