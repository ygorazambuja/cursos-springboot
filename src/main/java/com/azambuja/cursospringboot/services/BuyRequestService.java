package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.BilletPayment;
import com.azambuja.cursospringboot.domain.BuyRequest;
import com.azambuja.cursospringboot.domain.ItemRequest;
import com.azambuja.cursospringboot.domain.enums.PaymentState;
import com.azambuja.cursospringboot.repository.BuyRequestRepository;
import com.azambuja.cursospringboot.repository.ItemRequestRepository;
import com.azambuja.cursospringboot.repository.PaymentRepository;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyRequestService {
  @Autowired
  private BuyRequestRepository buyRequestRepository;

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private BilletService billetService;

  @Autowired
  private ItemRequestRepository itemRequestRepository;

  public BuyRequest getById(Integer id) {
    return buyRequestRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ObjectNotFoundException(
            "Object " + BuyRequest.class.getName() + " id: " + id + " not found"
          )
      );
  }

  public BuyRequest insert(BuyRequest buyRequest) {
    buyRequest.setId(null);
    buyRequest.setInstant(new Date());
    buyRequest.getPayment().setPaymentState(PaymentState.PENDING);
    buyRequest.getPayment().setBuyRequest(buyRequest);
    if (buyRequest.getPayment() instanceof BilletPayment) {
      BilletPayment payment = (BilletPayment) buyRequest.getPayment();
      billetService.fillPayment(payment, buyRequest.getInstant());
    }
    buyRequest = buyRequestRepository.save(buyRequest);
    paymentRepository.save(buyRequest.getPayment());
    for (ItemRequest itemRequest : buyRequest.getItemRequestSet()) {
      itemRequest.setDiscount(0.0);
      itemRequest.setProduct(productService.find(itemRequest.getProduct().getId()));
      itemRequest.setPrice(itemRequest.getProduct().getPrice());
      itemRequest.setBuyRequest(buyRequest);
    }
    itemRequestRepository.saveAll(buyRequest.getItemRequestSet());
    return buyRequest;
  }
}
