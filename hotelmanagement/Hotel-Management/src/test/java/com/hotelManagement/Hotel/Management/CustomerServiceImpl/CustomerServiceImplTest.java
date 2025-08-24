package com.hotelManagement.Hotel.Management.CustomerServiceImpl;





import com.hotelManagement.Hotel.Management.Entities.Customer;
import com.hotelManagement.Hotel.Management.Repository.CustomerRepository;
import com.hotelManagement.Hotel.Management.ServiceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    public class CustomerServiceImplTest {

        @Mock
        private CustomerRepository customerRepository;

        @InjectMocks
        private CustomerServiceImpl customerService;

        @Test
        void createCustomer_shouldSaveAndReturnCustomer() {
            Customer c = new Customer();
            c.setCustName("Alice");

            when(customerRepository.save(c)).thenReturn(c);

            Customer result = customerService.createCustomer(c);

            assertEquals("Alice", result.getCustName());
            verify(customerRepository).save(c);
        }

        @Test
        void getCustomer_whenExists_returnsCustomer() {
            Customer c = new Customer();
            c.setCustId(1L);
            when(customerRepository.findById(1L)).thenReturn(Optional.of(c));

            Customer result = customerService.getCustomer(1L);

            assertEquals(1L, result.getCustId());
            verify(customerRepository).findById(1L);
        }

        @Test
        void getCustomer_whenNotFound_throwsException() {
            when(customerRepository.findById(1L)).thenReturn(Optional.empty());

            RuntimeException ex = assertThrows(RuntimeException.class,
                    () -> customerService.getCustomer(1L));

            assertEquals("Customer not found", ex.getMessage());
        }

        @Test
        void getAllCustomer_shouldReturnList() {
            when(customerRepository.findAll()).thenReturn(List.of(new Customer(), new Customer()));

            List<Customer> result = customerService.getAllCustomer();

            assertEquals(2, result.size());
            verify(customerRepository).findAll();
        }

        @Test
        void updateCustomer_shouldUpdateFields() {
            Customer existing = new Customer();
            existing.setCustId(1L);
            existing.setCustName("Old");

            Customer updated = new Customer();
            updated.setCustName("New");

            when(customerRepository.findById(1L)).thenReturn(Optional.of(existing));
            when(customerRepository.save(existing)).thenReturn(existing);

            Customer result = customerService.updateCustomer(1L, updated);

            assertEquals("New", result.getCustName());
            verify(customerRepository).save(existing);
        }
    }


