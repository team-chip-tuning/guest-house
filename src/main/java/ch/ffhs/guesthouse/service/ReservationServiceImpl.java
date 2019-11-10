package ch.ffhs.guesthouse.service;

import ch.ffhs.guesthouse.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {
    @Override
    public Page<Reservation> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Reservation> findByRfId(Long rfId) {
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> read(Long id) {
        return Optional.empty();
    }

    @Override
    public void insert(Reservation reservation) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Reservation> update(Reservation reservation) {
        return Optional.empty();
    }
}
