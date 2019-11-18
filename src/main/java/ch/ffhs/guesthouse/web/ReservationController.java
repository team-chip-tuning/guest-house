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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static ch.ffhs.guesthouse.util.ConstUtil.*;

/**
 * Reservation controller for the app
 *
 * @author dani
 */
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Handle readReservation GET request with reservationId
     *
     * @param reservationId
     * @param model
     * @return ModelAndView
     */
    @GetMapping(ROUTE_READ_RESERVATION_ID)
    public ModelAndView readReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView(REDIRECT_INDEX);
        }
        model.addAttribute(ATTRIBUTE_RESERVATION, reservation.get());
        return new ModelAndView(VIEW_READ_RESERVATION);
    }

    /**
     * Handle Post insertReservation POST request
     * Convection based, model is not used but needed for return binding.
     * Parameter order matters, crazy..
     * if BindingResult is not second, error handling on the view is not working
     *
     * @param reservation
     * @param bindingResult
     * @param model
     * @return ModelAndView
     */
    @PostMapping(ROUTE_INSERT_RESERVATION)
    public ModelAndView insertReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(VIEW_ADD_RESERVATION);
        }
        reservationService.insert(reservation);
        return new ModelAndView(REDIRECT_INDEX);
    }

    /**
     * Handle updateReservation GET request
     *
     * @param reservationId
     * @param model
     * @return ModelAndView
     */
    @GetMapping(ROUTE_UPDATE_RESERVATION_ID)
    public ModelAndView updateReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView(REDIRECT_INDEX);
        }
        model.addAttribute(ATTRIBUTE_RESERVATION, reservation.get());
        return new ModelAndView(VIEW_UPDATE_RESERVATION);
    }

    /**
     * Handle updateReservation POST request
     * Convection based, model is not used but needed for return binding.
     * Parameter order matters, crazy..
     * if BindingResult is not second, error handling on the view is not working
     *
     * @param reservation
     * @param bindingResult
     * @param model
     * @return ModelAndView
     */
    @PostMapping(ROUTE_UPDATE_RESERVATION)
    public ModelAndView updateReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(VIEW_UPDATE_RESERVATION);
        }
        reservationService.update(reservation);
        return new ModelAndView(REDIRECT_INDEX);
    }

    /**
     * Handle removeReservation GET request
     *
     * @param reservationId
     * @param model
     * @return ModelAndView
     */
    @GetMapping(ROUTE_REMOVE_RESERVATION_ID)
    public ModelAndView removeReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView(REDIRECT_INDEX);
        }
        model.addAttribute(ATTRIBUTE_RESERVATION, reservation.get());
        return new ModelAndView(VIEW_REMOVE_RESERVATION);
    }

    /**
     * Handle removeReservation POST request
     *
     * @param reservation
     * @return ModelAndView
     */
    @PostMapping(ROUTE_REMOVE_RESERVATION)
    public ModelAndView removeReservation(Reservation reservation) {
        reservationService.deleteById(reservation.getId());
        return new ModelAndView(REDIRECT_INDEX);
    }
}
