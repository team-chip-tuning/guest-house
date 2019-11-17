package ch.ffhs.guesthouse.repository;

import ch.ffhs.guesthouse.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Reservation Repository base is JpaRepository from springboot
    @author daniel
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
