package org.sid.inventoryservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
   CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration) {

		restConfiguration.exposeIdsFor(Product.class);
		return(args -> {

			productRepository.save(new Product(null,"Phone",123,123));
			productRepository.save(new Product(null,"Clothes",213,1223));
			productRepository.save(new Product(null,"Accessories",123,133));


			productRepository.findAll().forEach(prod->{
				System.out.println(prod);
			});

		});
   }
}

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
class Product {

@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id ;
private String name ;
private double price ;
private double quantity ;

}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long>{

}






