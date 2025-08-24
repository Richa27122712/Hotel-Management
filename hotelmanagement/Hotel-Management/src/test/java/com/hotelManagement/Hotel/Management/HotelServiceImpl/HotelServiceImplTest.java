package com.hotelManagement.Hotel.Management.HotelServiceImpl;





import com.hotelManagement.Hotel.Management.Entities.Hotel;
import com.hotelManagement.Hotel.Management.Repository.HotelRepository;
import com.hotelManagement.Hotel.Management.ServiceImpl.HotelServiceImpl;
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
    public class HotelServiceImplTest {

        @Mock
        private HotelRepository hotelRepository;

        @InjectMocks
        private HotelServiceImpl hotelService;

        @Test
        void createHotel_shouldSaveAndReturn() {
            Hotel h = new Hotel();
            h.setHotelName("Taj");

            when(hotelRepository.save(h)).thenReturn(h);

            Hotel result = hotelService.createHotel(h);

            assertEquals("Taj", result.getHotelName());
            verify(hotelRepository).save(h);
        }

        @Test
        void getHotel_whenExists_returnsHotel() {
            Hotel h = new Hotel();
            h.setHotelId(1L);
            when(hotelRepository.findById(1L)).thenReturn(Optional.of(h));

            Hotel result = hotelService.getHotel(1L);

            assertEquals(1L, result.getHotelId());
            verify(hotelRepository).findById(1L);
        }

        @Test
        void getHotel_whenNotFound_throwsException() {
            when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

            RuntimeException ex = assertThrows(RuntimeException.class,
                    () -> hotelService.getHotel(1L));

            assertEquals("Hotel not found", ex.getMessage());
        }

        @Test
        void getAllHotels_shouldReturnList() {
            when(hotelRepository.findAll()).thenReturn(List.of(new Hotel(), new Hotel(), new Hotel()));

            List<Hotel> result = hotelService.getAllHotels();

            assertEquals(3, result.size());
            verify(hotelRepository).findAll();
        }

        @Test
        void updateHotel_shouldUpdateAndSave() {
            Hotel existing = new Hotel();
            existing.setHotelId(1L);
            existing.setHotelName("Old");

            Hotel updated = new Hotel();
            updated.setHotelName("New");

            when(hotelRepository.findById(1L)).thenReturn(Optional.of(existing));
            when(hotelRepository.save(existing)).thenReturn(existing);

            Hotel result = hotelService.updateHotel(1L, updated);

            assertEquals("New", result.getHotelName());
            verify(hotelRepository).save(existing);
        }
    }


