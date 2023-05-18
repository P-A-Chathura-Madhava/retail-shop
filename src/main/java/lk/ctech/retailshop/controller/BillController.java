package lk.ctech.retailshop.controller;

import lk.ctech.retailshop.dto.BillDTO;
import lk.ctech.retailshop.dto.ResponseDTO;
import lk.ctech.retailshop.service.BillService;
import lk.ctech.retailshop.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/retail-system")
@CrossOrigin
@EnableTransactionManagement
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addBill")
    public ResponseEntity addBill(@RequestBody BillDTO billDTO){
        try {
            billService.addOrder(billDTO);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(billDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
