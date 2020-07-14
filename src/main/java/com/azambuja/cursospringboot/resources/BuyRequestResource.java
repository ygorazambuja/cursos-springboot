package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.BuyRequest;
import com.azambuja.cursospringboot.services.BuyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/buyRequests")
public class BuyRequestResource {

    @Autowired
    BuyRequestService buyRequestService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        BuyRequest buyRequest = buyRequestService.getById(id);
        return ResponseEntity.ok().body(buyRequest);
    }
}
