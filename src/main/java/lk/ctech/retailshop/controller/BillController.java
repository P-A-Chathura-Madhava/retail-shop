package lk.ctech.retailshop.controller;

import lk.ctech.retailshop.dto.BillDTO;
import lk.ctech.retailshop.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/retail-system")
@CrossOrigin
@EnableTransactionManagement
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/addBill")
    public BillDTO addBill(@RequestBody BillDTO billDTO){
        billService.addOrder(billDTO);
        return billDTO;
    }
}
