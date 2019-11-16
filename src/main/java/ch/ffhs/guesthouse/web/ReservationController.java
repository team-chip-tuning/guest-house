package ch.ffhs.guesthouse.web;

import ch.ffhs.guesthouse.entity.Reservation;
import ch.ffhs.guesthouse.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/readReservation/{reservationId}")
    public ModelAndView readReservation(@PathVariable Long reservationId, Model model) {
        var reservation = reservationService.read(reservationId);
        if (!reservation.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("reservation", reservation.get());
        return new ModelAndView("readReservation");
    }


    @PostMapping("/insertReservation")
    public ModelAndView addReservation(@ModelAttribute("addReservation") Reservation reservation, Model model) {
        model.addAttribute("title", "Kata Backend");
        model.addAttribute("persons", 42);
        reservationService.insert(reservation);
        return new ModelAndView("addReservation");
    }

    @PostMapping("/updateReservation")
    public ModelAndView updateReservation(@ModelAttribute("addReservation") Reservation reservation, Model model) {
        model.addAttribute("title", "Kata Backend");
        model.addAttribute("persons", 42);
        reservationService.update(reservation);
        return new ModelAndView("updateReservation");
    }

    @GetMapping("/removeReservation/{reservationId}")
    public ModelAndView updateReservation(@PathVariable Long reservationId, Model model) {
        model.addAttribute("title", "Kata Backend");
        model.addAttribute("persons", 42);
        return new ModelAndView("updateReservation");
    }
}
