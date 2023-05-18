package lk.ctech.retailshop.controller;

import lk.ctech.retailshop.dto.ItemDTO;
import lk.ctech.retailshop.dto.ResponseDTO;
import lk.ctech.retailshop.service.ItemService;
import lk.ctech.retailshop.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/retail-system")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveItem")
    public ResponseEntity saveItem(@RequestBody ItemDTO itemDTO){
        try {
            String result = itemService.saveItem(itemDTO);
            if (result.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(itemDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (result.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Registered Item");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateItem")
    public ResponseEntity updateItem(@RequestBody ItemDTO itemDTO){
        try {
            String result = itemService.updateItem(itemDTO);
            if (result.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Item Updated");
                responseDTO.setContent(itemDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (result.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Item not found");
                responseDTO.setContent(itemDTO);
                return new ResponseEntity(responseDTO, HttpStatus.NO_CONTENT);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllItems")
    public ResponseEntity getAllItems(){
        try {
            List<ItemDTO> itemList = itemService.getAllItems();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(itemList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchItem/{code}")
    public ResponseEntity searchItem(@PathVariable int code){
        try {
            ItemDTO item = itemService.searchItem(code);
            if (item != null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(item);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Item not found");
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
    @DeleteMapping("/deleteItem/{code}")
    public ResponseEntity deleteItem(@PathVariable int code){
        try {
            String result = itemService.deleteItem(code);
            if (result.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Item Deleted");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Item not found");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
