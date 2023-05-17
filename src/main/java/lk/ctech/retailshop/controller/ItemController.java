package lk.ctech.retailshop.controller;

import lk.ctech.retailshop.dto.ItemDTO;
import lk.ctech.retailshop.dto.ResponseDTO;
import lk.ctech.retailshop.service.ItemService;
import lk.ctech.retailshop.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
