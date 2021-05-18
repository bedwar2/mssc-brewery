package guru.springframework.msscbrewery.web.mappers;

import guru.springframework.msscbrewery.domain.Customer;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerMapperTest {

    //CustomerMapper mapper = new CustomerMapperImpl();
    @Autowired
    CustomerMapper mapper;

    @Test
    void customerDtoToCustomer() {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("Test").build();
        Customer customer = mapper.customerDtoToCustomer(customerDto);

        System.out.println(customerDto.toString());
        System.out.println(customer.toString());
        Assert.notNull(customer, "Is not null");

    }

    @Test
    void customerToCustomerDto() {


    }
}