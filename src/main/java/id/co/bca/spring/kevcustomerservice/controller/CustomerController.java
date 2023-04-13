package id.co.bca.spring.kevcustomerservice.controller;

import id.co.bca.spring.kevcustomerservice.entity.BaseResponse;
import id.co.bca.spring.kevcustomerservice.model.Customer;
import id.co.bca.spring.kevcustomerservice.service.CustomerService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<List<Customer>>> retrieveAllCustomer() {
        List<Customer> customers = customerService.retrieveAllCustomer();
        BaseResponse<List<Customer>> response = new BaseResponse<>();
        response.setPayload(customers);

        if(customers.isEmpty()) {
            response.setErrorCode("999");
            response.setErrorMessage("No data.");
        } else {
            response.setErrorCode("000");
            response.setErrorMessage("Success");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<Customer>> insertCustomer(@RequestBody Customer customer) {
        Customer result = customerService.insertCustomer(customer);
        BaseResponse<Customer> response = new BaseResponse<>();
        if (result == null) {
            response.setErrorCode("901");
            response.setErrorMessage("Failed insert customer.");
        } else {
            response.setErrorCode("000");
            response.setErrorMessage("Success");
        }
        response.setPayload(result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> deleteCustomerById(@PathVariable String id) {
        customerService.deleteCustomer(Integer.parseInt(id));

        BaseResponse<String> response = new BaseResponse<>();
        response.setErrorCode("300");
        response.setErrorMessage("Customer {} deleted");
        response.setPayload(id);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<BaseResponse<Customer>> updateCustomerById(@RequestParam String id, @RequestParam String name, @RequestParam String email, @RequestParam String phone) {
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(id));
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);

        Customer result = customerService.updateCustomer(customer);

        BaseResponse<Customer> response = new BaseResponse<>();
        response.setPayload(result);
        if(result == null) {
            response.setErrorCode("902");
            response.setErrorMessage("Failed update customer");
        } else {
            response.setErrorCode("000");
            response.setErrorMessage("Success");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<BaseResponse<Customer>> updatePasswordCustomerById(@PathVariable String id, @RequestBody Map<String,String> password) {
        Customer updateCustomer = customerService.retrieveCustomerById(Integer.parseInt(id));
        BaseResponse<Customer> response = new BaseResponse<>();
        response.setPayload(updateCustomer);

        if (updateCustomer == null) {
            response.setErrorMessage("Failed to update customer.");
            response.setErrorCode("902");
        } else {
            updateCustomer.setPassword(password.get("password"));
            Customer result = customerService.updateCustomer(updateCustomer);
            response.setPayload(result);
            response.setErrorCode("000");
            response.setErrorMessage("Success");
        }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
