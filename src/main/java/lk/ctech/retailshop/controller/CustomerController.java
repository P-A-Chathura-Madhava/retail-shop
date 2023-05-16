package lk.ctech.retailshop.controller;

import lk.ctech.retailshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/retail-system")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

}
