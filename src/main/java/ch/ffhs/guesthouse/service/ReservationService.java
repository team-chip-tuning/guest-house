package ch.ffhs.guesthouse.service;

import ch.ffhs.guesthouse.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReservationService {

    @Transactional
    Page<Reservation> findAll(Pageable pageable);

    @Transactional
    Optional<Reservation> findByRfId(Long rfId);

    @Transactional
    Optional<Reservation> read(Long id);

    @Transactional
    void insert(Reservation reservation);

    @Transactional
    void deleteById(Long id);

    @Transactional
    Optional<Reservation> update(Reservation reservation);
}
