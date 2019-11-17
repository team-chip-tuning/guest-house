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
    @GetMapping("/readReservation/{reservationId}")
    public ModelAndView readReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("reservation", reservation.get());
        return new ModelAndView("readReservation");
    }

    /**
     * Handle Post insertReservation POST request
     *
     * @param reservation
     * @param bindingResult
     * @param model
     * @return ModelAndView
     */
    @PostMapping("/insertReservation")
    public ModelAndView insertReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("addReservation");
        }
        reservationService.insert(reservation);
        return new ModelAndView("redirect:/");
    }

    /**
     * Handle updateReservation GET request
     *
     * @param reservationId
     * @param model
     * @return ModelAndView
     */
    @GetMapping("/updateReservation/{reservationId}")
    public ModelAndView updateReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("reservation", reservation.get());
        return new ModelAndView("updateReservation");
    }

    /**
     * Handle updateReservation POST request
     *
     * @param reservation
     * @param bindingResult
     * @param model
     * @return ModelAndView
     */
    @PostMapping("/updateReservation")
    public ModelAndView updateReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("updateReservation");
        }
        reservationService.update(reservation);
        return new ModelAndView("redirect:/");
    }

    /**
     * Handle removeReservation GET request
     *
     * @param reservationId
     * @param model
     * @return ModelAndView
     */
    @GetMapping("/removeReservation/{reservationId}")
    public ModelAndView removeReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("reservation", reservation.get());
        return new ModelAndView("removeReservation");
    }

    /**
     * Handle removeReservation POST request
     *
     * @param reservation
     * @param model
     * @return ModelAndView
     */
    @PostMapping("/removeReservation")
    public ModelAndView removeReservation(Reservation reservation, Model model) {
        reservationService.deleteById(reservation.getId());
        return new ModelAndView("redirect:/");
    }
}
