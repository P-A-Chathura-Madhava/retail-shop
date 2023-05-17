package lk.ctech.retailshop.service;

import lk.ctech.retailshop.dto.CustomerDTO;
import lk.ctech.retailshop.dto.ItemDTO;
import lk.ctech.retailshop.entity.Item;
import lk.ctech.retailshop.repo.ItemRepo;
import lk.ctech.retailshop.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getCode())) {
            return VarList.RSP_DUPLICATED;
        } else {
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateItem(ItemDTO itemDTO){
        if (itemRepo.existsById(itemDTO.getCode())){
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<ItemDTO> getAllItems(){
        List<Item> itemList = itemRepo.findAll();
        return modelMapper.map(itemList, new TypeToken<ArrayList<ItemDTO>>(){}.getType());
    }
    public ItemDTO searchItem(int code){
        if (itemRepo.existsById(code)){
            Item item = itemRepo.findById(code).orElse(null);
            return modelMapper.map(item, ItemDTO.class);
        }else {
            return null;
        }
    }
    public String deleteItem(int code){
        if (itemRepo.existsById(code)){
            itemRepo.deleteById(code);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
