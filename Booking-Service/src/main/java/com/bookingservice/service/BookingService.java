package com.bookingservice.service;

import com.bookingservice.dto.BookingResponse;
import com.bookingservice.dto.RequestBooking;

public interface BookingService {

    BookingResponse requestBooking(RequestBooking requestBooking);


}
