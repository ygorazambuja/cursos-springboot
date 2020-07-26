package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService {

    public void fillPayment(BilletPayment payment, Date instant) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setPaymentDate(calendar.getTime());
    }
}
