package lk.ctech.retailshop.controller;

import lk.ctech.retailshop.dto.CustomerDTO;
import lk.ctech.retailshop.dto.ResponseDTO;
import lk.ctech.retailshop.service.CustomerService;
import lk.ctech.retailshop.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/retail-system")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveCustomer")
    public ResponseEntity saveCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            String result = customerService.saveCustomer(customerDTO);
            if (result.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (result.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Registered Customer");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateCustomer")
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            String result = customerService.updateCustomer(customerDTO);
            if (result.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (result.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Customer not found");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.NO_CONTENT);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllCustomers")
    public ResponseEntity getAllCustomers(){
        try {
            List<CustomerDTO> customerList = customerService.getAllCustomers();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(customerList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchCustomer/{customerId}")
    public ResponseEntity searchCustomer(@PathVariable int customerId){
        try {
            CustomerDTO customer = customerService.searchCustomer(customerId);
            if (customer != null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customer);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Customer Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.NO_CONTENT);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable int customerId){
        try {
            String result = customerService.deleteCustomer(customerId);
            if (result.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Customer Deleted");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Customer not found");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.NO_CONTENT);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
