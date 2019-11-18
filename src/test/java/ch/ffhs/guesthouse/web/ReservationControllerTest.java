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

package ch.ffhs.guesthouse.web;

import ch.ffhs.guesthouse.entity.Reservation;
import ch.ffhs.guesthouse.service.ReservationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static ch.ffhs.guesthouse.util.ConstUtil.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests for ReservationController
 *
 * @author dani
 */
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Captor
    private ArgumentCaptor<String> modelCaptorString;
    @Captor
    private ArgumentCaptor<Reservation> modelCaptor;

    private ReservationController testee;

    @BeforeEach
    void setUp() {
        initMocks(this);
        testee = new ReservationController(reservationService);
    }

    @Test
    void readReservationReturnRedirectIfReservationIsNotPresent() {
        when(reservationService.readById(anyLong())).thenReturn(Optional.empty());

        var result = testee.readReservation(42L, model);

        verify(reservationService, times(1)).readById(anyLong());
        Assertions.assertThat(result.getViewName().contains(REDIRECT_INDEX)).isTrue();
    }

    @Test
    void readReservation() {
        var reservation = new Reservation();
        reservation.setRfId("42");
        Optional<Reservation> expects = Optional.ofNullable(reservation);

        when(reservationService.readById(anyLong())).thenReturn(expects);

        var result = testee.readReservation(42L, model);

        verify(reservationService, times(1)).readById(anyLong());
        verify(model, times(1)).addAttribute(modelCaptorString.capture(), modelCaptor.capture());
        Assertions.assertThat(modelCaptorString.getAllValues().contains(ATTRIBUTE_RESERVATION)).isTrue();
        Assertions.assertThat(modelCaptor.getAllValues().contains(expects.get())).isTrue();
        Assertions.assertThat(result.getViewName().contains(VIEW_READ_RESERVATION)).isTrue();
    }

    @Test
    void insertReservationWhenBindingResultHasError() {
        var reservation = new Reservation();
        when(bindingResult.hasErrors()).thenReturn(true);

        var result = testee.insertReservation(reservation, bindingResult, model);
        Assertions.assertThat(result.getViewName().contains(VIEW_ADD_RESERVATION)).isTrue();
    }

    @Test
    void insertReservation() {
        var reservation = new Reservation();
        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(reservationService).insert(any());

        var result = testee.insertReservation(reservation, bindingResult, model);

        verify(reservationService, times(1)).insert(any());
        Assertions.assertThat(result.getViewName().contains(REDIRECT_INDEX)).isTrue();
    }

    @Test
    void updateReservationWhenIsPresentIsNotTrue() {
        when(reservationService.readById(anyLong())).thenReturn(Optional.empty());

        var result = testee.updateReservation(42L, model);

        verify(reservationService, times(1)).readById(any());
        Assertions.assertThat(result.getViewName().contains(REDIRECT_INDEX)).isTrue();
    }

    @Test
    void updateReservationWhenIsPresentIsNotFalseReturnExpectedReservation() {
        var reservation = new Reservation();
        Optional<Reservation> expects = Optional.ofNullable(reservation);
        when(reservationService.readById(anyLong())).thenReturn(expects);


        var result = testee.updateReservation(42L, model);

        verify(reservationService, times(1)).readById(any());
        verify(model, times(1)).addAttribute(modelCaptorString.capture(), modelCaptor.capture());
        Assertions.assertThat(modelCaptorString.getAllValues().contains(ATTRIBUTE_RESERVATION)).isTrue();
        Assertions.assertThat(modelCaptor.getAllValues().contains(expects.get())).isTrue();
        Assertions.assertThat(result.getViewName().contains(VIEW_UPDATE_RESERVATION)).isTrue();
    }

    @Test
    void updateReservationHasBindingErros() {
        when(bindingResult.hasErrors()).thenReturn(true);

        var result = testee.updateReservation(new Reservation(), bindingResult, model);

        Assertions.assertThat(result.getViewName().contains(VIEW_UPDATE_RESERVATION)).isTrue();
    }

    @Test
    void updateReservationHasNoBindingErrorsUpdate() {
        doNothing().when(reservationService).update(any());
        when(bindingResult.hasErrors()).thenReturn(false);

        var result = testee.updateReservation(new Reservation(), bindingResult, model);

        verify(reservationService, times(1)).update(any());
        Assertions.assertThat(result.getViewName().contains(REDIRECT_INDEX)).isTrue();
    }

    @Test
    void removeReservationIsPresentNotFalseThenRedirect() {
        when(reservationService.readById(anyLong())).thenReturn(Optional.empty());

        var result = testee.removeReservation(42L, model);

        verify(reservationService, times(1)).readById(any());
        Assertions.assertThat(result.getViewName().contains(REDIRECT_INDEX)).isTrue();
    }

    @Test
    void removeReservationIsPresentNotTrueThenReturnReservation() {
        var reservation = new Reservation();
        Optional<Reservation> expects = Optional.ofNullable(reservation);
        when(reservationService.readById(anyLong())).thenReturn(expects);

        var result = testee.removeReservation(42L, model);

        verify(reservationService, times(1)).readById(any());
        verify(model, times(1)).addAttribute(modelCaptorString.capture(), modelCaptor.capture());
        Assertions.assertThat(modelCaptorString.getAllValues().contains(ATTRIBUTE_RESERVATION)).isTrue();
        Assertions.assertThat(modelCaptor.getAllValues().contains(expects.get())).isTrue();
        Assertions.assertThat(result.getViewName().contains(VIEW_REMOVE_RESERVATION)).isTrue();
    }

    @Test
    void removeReservation() {
        var reservation = new Reservation();
        reservation.setId(42L);
        doNothing().when(reservationService).deleteById(any());

        var result = testee.removeReservation(reservation);

        verify(reservationService, times(1)).deleteById(any());
        Assertions.assertThat(result.getViewName().contains(REDIRECT_INDEX)).isTrue();
    }
}