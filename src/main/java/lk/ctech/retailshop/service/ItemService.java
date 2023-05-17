package lk.ctech.retailshop.service;

import lk.ctech.retailshop.dto.ItemDTO;
import lk.ctech.retailshop.entity.Item;
import lk.ctech.retailshop.repo.ItemRepo;
import lk.ctech.retailshop.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

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
}
