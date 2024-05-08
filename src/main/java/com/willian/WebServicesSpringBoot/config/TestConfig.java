package com.willian.WebServicesSpringBoot.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.willian.WebServicesSpringBoot.entities.Category;
import com.willian.WebServicesSpringBoot.entities.Order;
import com.willian.WebServicesSpringBoot.entities.Product;
import com.willian.WebServicesSpringBoot.entities.User;
import com.willian.WebServicesSpringBoot.entities.enums.OrderStatus;
import com.willian.WebServicesSpringBoot.repositories.CategoryRepository;
import com.willian.WebServicesSpringBoot.repositories.OrderRepository;
import com.willian.WebServicesSpringBoot.repositories.ProductRepository;
import com.willian.WebServicesSpringBoot.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository prodcutRepository;
		
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
				
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), user1, OrderStatus.PAID);
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), user2, OrderStatus.WAITING_PAYMENT);
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), user1, OrderStatus.WAITING_PAYMENT);
				
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		prodcutRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
		
		product1.getCategroies().add(cat2);
		
		product2.getCategroies().add(cat1);
		product2.getCategroies().add(cat3);
		
		product3.getCategroies().add(cat3);
		product4.getCategroies().add(cat3);
		
		product5.getCategroies().add(cat2);
		
		prodcutRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
	}	
}
