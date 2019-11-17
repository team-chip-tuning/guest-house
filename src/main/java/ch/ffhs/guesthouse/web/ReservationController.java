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

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/readReservation/{reservationId}")
    public ModelAndView readReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("reservation", reservation.get());
        return new ModelAndView("readReservation");
    }

    @PostMapping("/insertReservation")
    public ModelAndView insertReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("addReservation");
        }
        reservationService.insert(reservation);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/updateReservation/{reservationId}")
    public ModelAndView updateReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("reservation", reservation.get());
        return new ModelAndView("updateReservation");
    }

    @PostMapping("/updateReservation")
    public ModelAndView updateReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("updateReservation");
        }
        reservationService.update(reservation);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/removeReservation/{reservationId}")
    public ModelAndView removeReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.readById(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("reservation", reservation.get());
        return new ModelAndView("removeReservation");
    }

    @PostMapping("/removeReservation")
    public ModelAndView removeReservation(Reservation reservation, Model model) {
        reservationService.deleteById(reservation.getId());
        return new ModelAndView("redirect:/");
    }
}
