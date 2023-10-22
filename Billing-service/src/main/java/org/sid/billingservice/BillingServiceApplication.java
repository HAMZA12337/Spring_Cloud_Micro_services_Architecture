package org.sid.billingservice;

import jakarta.ws.rs.BeanParam;
import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CusotmerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}



	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CusotmerRestClient cusotmerRestClient,
							ProductItemRestClient productItemRestClient
	){
		return args->{

			// Add bILL
			Customer customer=cusotmerRestClient.getCustomerById(1L);
			Bill bill=billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
			PagedModel<Product> productPagedModel=productItemRestClient.pageProduct();

			productPagedModel.forEach(e->{
				ProductItem productItem= new ProductItem();
				productItem.setPrice(e.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
				productItem.setProductId(productItem.getId());
				productItem.setBill(bill);
               productItemRepository.save(productItem);
			});

			System.out.println("---------------------------");
			System.out.println(customer.getId());
			System.out.println(customer.getName());
			System.out.println(customer.getEmail());





		};
	}






}
