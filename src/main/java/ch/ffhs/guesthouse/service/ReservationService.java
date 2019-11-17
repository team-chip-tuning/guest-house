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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Interface ReservationService
 *
 * @author dani
 */
public interface ReservationService {

    /**
     * Find all active reservation
     *
     * @return List<Reservation>
     */
    @Transactional
    List<Reservation> findAll();

    /**
     * Find by RFID
     *
     * @param rfId
     * @return Optional<Reservation>
     */
    @Transactional
    Optional<Reservation> findByRfId(String rfId);

    /**
     * Read by reservation id
     *
     * @param id
     * @return Optional<Reservation>
     */
    @Transactional
    Optional<Reservation> readById(Long id);

    /**
     * Insert reservation
     *
     * @param reservation
     */
    @Transactional
    void insert(Reservation reservation);

    /**
     * Deleted reservation by id
     *
     * @param id
     */
    @Transactional
    void deleteById(Long id);

    /**
     * Update Reservation
     *
     * @param reservation
     */
    @Transactional
    void update(Reservation reservation);
}
