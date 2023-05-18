package lk.ctech.retailshop.service;

import lk.ctech.retailshop.dto.BillDTO;
import lk.ctech.retailshop.dto.ItemDTO;
import lk.ctech.retailshop.entity.Bill;
import lk.ctech.retailshop.repo.BillRepo;
import lk.ctech.retailshop.repo.ItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillService {
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ModelMapper modelMapper;

    public boolean addOrder(BillDTO billDTO){
        Bill bill = billRepo.save(modelMapper.map(billDTO, Bill.class));
        if (bill != null){
            ItemDTO itemDTO = itemService.searchItem(billDTO.getItemCode());
            int remainingItemQty = itemDTO.getQtyOnHand() - billDTO.getQty();
            String result = itemService.updateItem(new ItemDTO(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), remainingItemQty));
            if (result.equals("00")){
                return true;
            }
        }
        return false;
    }
}
