package com.hotelManagement.Hotel.Management.BookingServiceImpl;

import com.hotelManagement.Hotel.Management.Entities.Booking;
import com.hotelManagement.Hotel.Management.Entities.Customer;
import com.hotelManagement.Hotel.Management.Entities.Hotel;
import com.hotelManagement.Hotel.Management.Repository.BookingRepository;
import com.hotelManagement.Hotel.Management.Repository.CustomerRepository;
import com.hotelManagement.Hotel.Management.Repository.HotelRepository;
    import com.hotelManagement.Hotel.Management.ServiceImpl.BookingServiceImpl;
    import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    public class BookingServiceImplTest {

        @Mock private BookingRepository bookingRepository;
        @Mock private HotelRepository hotelRepository;
        @Mock private CustomerRepository cutomerRepository;

        @InjectMocks
        private BookingServiceImpl bookingService;

        @Test
        void createBooking_shouldSetCustomerAndHotel() {
            Long hotelId = 1L, custId = 2L;
            Customer customer = new Customer(); customer.setCustId(custId);
            Hotel hotel = new Hotel(); hotel.setHotelId(hotelId);

            Booking booking = new Booking();

            when(cutomerRepository.findById(custId)).thenReturn(Optional.of(customer));
            when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
            when(bookingRepository.save(booking)).thenReturn(booking);

            Booking result = bookingService.createBooking(booking, hotelId, custId);

            assertSame(customer, result.getCustomer());
            assertSame(hotel, result.getHotel());
            verify(bookingRepository).save(booking);
        }

        @Test
        void getBooking_shouldReturnAll() {
            when(bookingRepository.findAll()).thenReturn(List.of(new Booking(), new Booking()));

            List<Booking> result = bookingService.getBooking();

            assertEquals(2, result.size());
            verify(bookingRepository).findAll();
        }

        @Test
        void getBookingById_whenExists_returnsBooking() {
            Booking b = new Booking();
            when(bookingRepository.findById(1L)).thenReturn(Optional.of(b));

            Booking result = bookingService.getBookingById(1L);

            assertSame(b, result);
        }

        @Test
        void getBookingById_whenNotFound_throwsException() {
            when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

            RuntimeException ex = assertThrows(RuntimeException.class,
                    () -> bookingService.getBookingById(1L));

            assertEquals("Booking not found", ex.getMessage());
        }

        @Test
        void uploadReceipt_shouldSaveFileName() {
            Booking b = new Booking();
            MultipartFile file = mock(MultipartFile.class);

            when(file.getOriginalFilename()).thenReturn("receipt.pdf");
            when(bookingRepository.findById(1L)).thenReturn(Optional.of(b));

            bookingService.uploadReceipt(1L, file);

            assertEquals("receipt.pdf", b.getFilepath());
            verify(bookingRepository).save(b);
        }

        @Test
         void cancleBooking_shouldDeleteById() {
            bookingService.cancleBooking(10L);
            verify(bookingRepository).deleteById(10L);
        }
    }


