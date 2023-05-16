package lk.ctech.retailshop.service;

import lk.ctech.retailshop.dto.CustomerDTO;
import lk.ctech.retailshop.entity.Customer;
import lk.ctech.retailshop.repo.CustomerRepo;
import lk.ctech.retailshop.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveCustomer(CustomerDTO customerDTO){
        if (customerRepo.existsById(customerDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_SUCCESS;
        }
    }
}
