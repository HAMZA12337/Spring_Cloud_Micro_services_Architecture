package org.sid.billingservice.web;


import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.sid.billingservice.feign.CusotmerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {


private BillRepository billRepository;
private ProductItemRepository productItemRepository;
private CusotmerRestClient customerRestClient;
private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CusotmerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path="/fullBill/{id}")
   public Bill getBill(@PathVariable(name="id") Long id){

        Bill bill=billRepository.findById(id).get();
        Customer customer=customerRestClient.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(p->{
            Product product=productItemRestClient.getProductById(p.getProductId());
           // p.setProduct(product);
            // if we want jist name but in product item should add product name wuth transient anotations
          //   p.setProduct(product.getName());
        });

    return bill;}









}
