package ch.ffhs.guesthouse.service;

import ch.ffhs.guesthouse.entity.Reservation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Interface ReservationService
 *
 * @author dani
 */
public interface ReservationService {

    @Transactional
    List<Reservation> findAll();

    @Transactional
    Optional<Reservation> findByRfId(String rfId);

    @Transactional
    Optional<Reservation> readById(Long id);

    @Transactional
    void insert(Reservation reservation);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void update(Reservation reservation);
}
