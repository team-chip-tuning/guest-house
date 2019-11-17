package ch.ffhs.guesthouse.web;

import ch.ffhs.guesthouse.entity.Reservation;
import ch.ffhs.guesthouse.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private final ReservationService reservationService;

    public IndexController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("")
    public ModelAndView index(Model model) {
        model.addAttribute("reservationList", reservationService.findAll());
        return new ModelAndView("index");
    }

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
