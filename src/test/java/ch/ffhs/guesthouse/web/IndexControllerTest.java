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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ch.ffhs.guesthouse.util.ConstUtil.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests for IndexController
 *
 * @author dani
 */
class IndexControllerTest {

    @Mock
    private ReservationService reservationService;
    @Mock
    private Model model;
    @Captor
    private ArgumentCaptor<List<Reservation>> modelCaptorList;
    @Captor
    private ArgumentCaptor<String> modelCaptorString;
    @Captor
    private ArgumentCaptor<Reservation> modelCaptor;

    private IndexController testee;

    @BeforeEach
    void setUp() {
        initMocks(this);
        testee = new IndexController(reservationService);
    }

    @Test
    void index() {
        List<Reservation> expects = new ArrayList<>();
        when(reservationService.findAll()).thenReturn(expects);

        var result = testee.index(model);

        verify(reservationService, times(1)).findAll();
        verify(model, times(1)).addAttribute(modelCaptorString.capture(), modelCaptorList.capture());
        Assertions.assertThat(modelCaptorString.getAllValues().contains(ATTRIBUTE_RESERVATION_LIST)).isTrue();
        Assertions.assertThat(modelCaptorList.getAllValues().contains(expects)).isTrue();
        Assertions.assertThat(result.getViewName().contains(VIEW_INDEX)).isTrue();
    }

    @Test
    void addReservationWhenRfidIsEmptyReturnRedirect() {
        var reservation = new Reservation();
        reservation.setRfId("");

        var result = testee.addReservation(reservation, model);

        Assertions.assertThat(result.getViewName().contains(REDIRECT_INDEX)).isTrue();
    }

    @Test
    void addReservationWhenOldReservationIsPresent() {
        var reservation = new Reservation();
        reservation.setRfId("42");
        Optional<Reservation> expects = Optional.ofNullable(reservation);
        when(reservationService.findByRfId(reservation.getRfId())).thenReturn(expects);

        var result = testee.addReservation(reservation, model);

        verify(model, times(1)).addAttribute(modelCaptorString.capture(), modelCaptor.capture());
        Assertions.assertThat(modelCaptorString.getAllValues().contains(ATTRIBUTE_RESERVATION)).isTrue();
        Assertions.assertThat(modelCaptor.getAllValues().contains(reservation)).isTrue();
        Assertions.assertThat(result.getViewName().contains(VIEW_ADD_RESERVATION)).isTrue();
    }

    @Test
    void addReservationWhenOldReservationIsNotPresent() {
        var reservation = new Reservation();
        reservation.setRfId("42");
        Optional<Reservation> expects = Optional.empty();
        when(reservationService.findByRfId(reservation.getRfId())).thenReturn(expects);

        var result = testee.addReservation(reservation, model);

        verify(model, times(1)).addAttribute(modelCaptorString.capture(), modelCaptor.capture());
        Assertions.assertThat(modelCaptorString.getAllValues().contains(ATTRIBUTE_RESERVATION)).isTrue();
        Assertions.assertThat(modelCaptor.getAllValues().contains(reservation)).isTrue();
        Assertions.assertThat(result.getViewName().contains(VIEW_ADD_RESERVATION)).isTrue();
    }
}