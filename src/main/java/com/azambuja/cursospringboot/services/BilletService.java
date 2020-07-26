package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.BilletPayment;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class BilletService {

  public void fillPayment(BilletPayment payment, Date instant) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(instant);
    calendar.add(Calendar.DAY_OF_MONTH, 7);
    payment.setPaymentDate(calendar.getTime());
  }
}
