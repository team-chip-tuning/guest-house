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

package ch.ffhs.guesthouse.service;

import ch.ffhs.guesthouse.entity.Reservation;
import ch.ffhs.guesthouse.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Reservation Service implementation
 * This is the main class in the bussines layer
 *
 * @author dani
 */
@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Return all active guests
     *
     * @return List<Reservation>
     */
    @Override
    public List<Reservation> findAll() {
        var result = reservationRepository.findAllByIsDeletedFalse();
        log.info("FIND ALL RESERVATION, COUNT IS {}", result.size());
        return result;
    }

    /**
     * Read reservation on rfid if is not found return empty optional object.
     * If found an old reservation set deleted state to FALSE
     *
     * @param rfId
     * @return Optional<Reservation>
     */
    @Override
    public Optional<Reservation> findByRfId(String rfId) {
        log.info("FIND BY RFID: {}", rfId);
        var result = reservationRepository.findByRfIdAndIsDeletedTrue(rfId);
        if (result.isPresent()) {
            result.get().setDeleted(false);
            log.info("FOUND RECORD AND SET DELTED TO FALSE: {}", result.get().toString());
            return result;
        }
        return Optional.empty();
    }

    /**
     * Read reservation by id
     *
     * @param id
     * @return Optional<Reservation>
     */
    @Override
    public Optional<Reservation> readById(Long id) {
        log.info("READ BY ID RECORD: {}", id.toString());
        return reservationRepository.findById(id);
    }

    /**
     * Insert new Reservation
     *
     * @param reservation
     */
    @Override
    public void insert(Reservation reservation) {
        reservationRepository.save(reservation);
        log.info("INSERT RECORD: {}", reservation.toString());
    }

    /**
     * Delete reservation, this is a soft delete
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        var reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            var result = reservation.get();
            result.setDeleted(true);
            reservationRepository.save(result);
            log.info("DELETED RECORD: {}", result.toString());
        }
    }

    /**
     * Update reservation
     *
     * @param reservation
     */
    @Override
    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
        log.info("UPDATE RESERVATION: {}", reservation.toString());
    }
}
