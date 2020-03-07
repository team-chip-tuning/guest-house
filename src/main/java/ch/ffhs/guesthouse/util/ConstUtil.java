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

package ch.ffhs.guesthouse.util;

/**
 * constance utils
 *
 * @author dani
 */
public class ConstUtil {
    // APP CONFIG
    public static final String CONFIG_INDEX = "index";
    public static final String CONFIG_ROUTE = "/";
    public static final String CONFIG_ROUTE_INDEX = "/index";
    public static final String CONFIG_ENCODING = "UTF-8";

    // APP CONSTANCE
    public static final String REDIRECT_INDEX = "redirect:/";

    // APP VIEWS
    public static final String VIEW_INDEX = "index";
    public static final String VIEW_ADD_RESERVATION = "addReservation";
    public static final String VIEW_READ_RESERVATION = "readReservation";
    public static final String VIEW_UPDATE_RESERVATION = "updateReservation";
    public static final String VIEW_REMOVE_RESERVATION = "removeReservation";

    // APP ROUTING
    public static final String ROUTE_INDEX = "";
    public static final String ROUTE_ADD_RESERVATION = "/addReservation";
    public static final String ROUTE_REMOVE_RESERVATION = "/removeReservation";
    public static final String ROUTE_REMOVE_RESERVATION_ID = "/removeReservation/{reservationId}";
    public static final String ROUTE_UPDATE_RESERVATION = "/updateReservation";
    public static final String ROUTE_UPDATE_RESERVATION_ID = "/updateReservation/{reservationId}";
    public static final String ROUTE_INSERT_RESERVATION = "/insertReservation";
    public static final String ROUTE_READ_RESERVATION_ID = "/readReservation/{reservationId}";

    // VIEW MODEL ATTRIBUTE
    public static final String ATTRIBUTE_RESERVATION = "reservation";
    public static final String ATTRIBUTE_RESERVATION_LIST = "reservationList";
}
