package ch.ffhs.guesthouse.service;

import ch.ffhs.guesthouse.entity.Reservation;
import ch.ffhs.guesthouse.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findByRfId(String rfId) {
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> readById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void insert(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
