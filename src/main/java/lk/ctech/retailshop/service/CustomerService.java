package lk.ctech.retailshop.service;

import lk.ctech.retailshop.dto.CustomerDTO;
import lk.ctech.retailshop.entity.Customer;
import lk.ctech.retailshop.repo.CustomerRepo;
import lk.ctech.retailshop.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    public String updateCustomer(CustomerDTO customerDTO){
        if (customerRepo.existsById(customerDTO.getId())){
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList = customerRepo.findAll();
        return modelMapper.map(customerList, new TypeToken<ArrayList<CustomerDTO>>(){}.getType());
    }
    public CustomerDTO searchCustomer(Integer id){
        if (customerRepo.existsById(id)){
            Customer customer = customerRepo.findById(id).orElse(null);
            return modelMapper.map(customer, CustomerDTO.class);
        }else {
            return null;
        }
    }
    public String deleteCustomer(int id){
        if (customerRepo.existsById(id)){
            customerRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
