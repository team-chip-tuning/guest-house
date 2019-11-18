package ch.ffhs.guesthouse.service;

import ch.ffhs.guesthouse.entity.Reservation;
import ch.ffhs.guesthouse.repository.ReservationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests for ReservationService
 *
 * @author dani
 */
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    private ReservationService testee;

    @BeforeEach
    void setUp() {
        initMocks(this);
        testee = new ReservationServiceImpl(reservationRepository);
    }

    @Test
    void findAll() {
        List<Reservation> expected = Collections.emptyList();
        when(reservationRepository.findAllByIsDeletedFalse()).thenReturn(expected);

        var result = testee.findAll();

        verify(reservationRepository, times(1)).findAllByIsDeletedFalse();
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void findByRfIdReturnValidResult() {
        var expected = Optional.ofNullable(new Reservation());
        when(reservationRepository.findByRfIdAndIsDeletedTrue(anyString())).thenReturn(expected);

        var result = testee.findByRfId(anyString());

        verify(reservationRepository, times(1)).findByRfIdAndIsDeletedTrue(anyString());
        Assertions.assertThat(result.get().isDeleted()).isFalse();
    }

    @Test
    void findByRfIdReturnInvalidResult() {
        Optional<Reservation> expected = Optional.empty();
        when(reservationRepository.findByRfIdAndIsDeletedTrue(anyString())).thenReturn(expected);

        var result = testee.findByRfId(anyString());

        verify(reservationRepository, times(1)).findByRfIdAndIsDeletedTrue(anyString());
        Assertions.assertThat(result.isPresent()).isEqualTo(expected.isPresent());
    }

    @Test
    void readById() {
        Optional<Reservation> expected = Optional.empty();
        when(reservationRepository.findById(anyLong())).thenReturn(expected);

        var result = testee.readById(anyLong());

        verify(reservationRepository, times(1)).findById(anyLong());
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void insert() {
        var expected = new Reservation();
        when(reservationRepository.save(any())).thenReturn(expected);

        testee.insert(expected);

        verify(reservationRepository, times(1)).save(any());
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}