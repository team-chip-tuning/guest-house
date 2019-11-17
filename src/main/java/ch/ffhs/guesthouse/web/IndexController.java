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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * IndexController, this is the start controller for the app
 *
 * @author dani
 */
@Controller
public class IndexController {

    private final ReservationService reservationService;

    public IndexController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Handle index GET request
     *
     * @param model
     * @return ModelAndView
     */
    @GetMapping("")
    public ModelAndView index(Model model) {
        model.addAttribute("reservationList", reservationService.findAll());
        return new ModelAndView("index");
    }

    /**
     * Handle addReservation POST request
     *
     * @param reservation
     * @param model
     * @return ModelAndView
     */
    @PostMapping("/addReservation")
    public ModelAndView addReservation(@ModelAttribute("addReservation") Reservation reservation, Model model) {
        if (reservation.getRfId().isEmpty()) {
            return new ModelAndView("redirect:/");
        }
        var oldReservation = reservationService.findByRfId(reservation.getRfId());
        if (oldReservation.isPresent()) {
            model.addAttribute("reservation", oldReservation.get());
        } else {
            model.addAttribute("reservation", reservation);
        }
        return new ModelAndView("addReservation");
    }
}
