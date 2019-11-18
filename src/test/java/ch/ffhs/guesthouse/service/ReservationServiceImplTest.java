/*
 * MIT License
 *
 * Copyright (c) 2019
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
    void deleteByIdWhenReservationIsFound() {
        var expected = Optional.ofNullable(new Reservation());
        when(reservationRepository.findById(anyLong())).thenReturn(expected);
        when(reservationRepository.save(any())).thenReturn(expected.get());

        testee.deleteById(anyLong());

        verify(reservationRepository, times(1)).findById(anyLong());
        verify(reservationRepository, times(1)).save(any());
    }

    @Test
    void deleteByIdWhenReservationsIsNotFound() {
        Optional<Reservation> expected = Optional.empty();
        when(reservationRepository.findById(anyLong())).thenReturn(expected);
        when(reservationRepository.save(any())).thenReturn(new Reservation());

        testee.deleteById(anyLong());

        verify(reservationRepository, times(1)).findById(anyLong());
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void update() {
        var expected = new Reservation();
        when(reservationRepository.save(any())).thenReturn(expected);

        testee.update(expected);

        verify(reservationRepository, times(1)).save(any());
    }
}