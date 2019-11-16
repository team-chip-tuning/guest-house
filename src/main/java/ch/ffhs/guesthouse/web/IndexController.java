package ch.ffhs.guesthouse.web;

import ch.ffhs.guesthouse.entity.Reservation;
import ch.ffhs.guesthouse.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    private final ReservationService reservationService;

    public IndexController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Kata Backend");
        model.addAttribute("persons", 42);
        return "index";
    }

    @PostMapping("/guestReservation")
    public String guestReservation(@ModelAttribute("guestReservation") Reservation reservation, Model model) {
        if (reservation.getRfId().isEmpty()) {
            return "index";
        }
        var oldReservation = reservationService.findByRfId(reservation.getRfId());
        if (oldReservation.isPresent()) {
            model.addAttribute("reservation", oldReservation.get());
        } else {
            model.addAttribute("reservation", new Reservation());
        }
        return "index";
    }
}
