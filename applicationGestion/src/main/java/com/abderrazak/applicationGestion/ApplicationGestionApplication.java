package com.abderrazak.applicationGestion;

import com.abderrazak.applicationGestion.model.*;
import com.abderrazak.applicationGestion.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ApplicationGestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationGestionApplication.class, args);
//		Users user = context.getBean(Users.class);
//		user.setUsername("Kamal");
//		user.setEmail("Kamal@gmail.com");
//		user.setPassword("azer789456");
//		user.setRole("user");
//
//		UsersRepo repo = context.getBean(UsersRepo.class);
//		repo.save(user);
//
//		System.out.println(repo.findAll());


	}

	@Bean
	public UserRowMapper userRowMapper() {
		return new UserRowMapper();
	}

	@Bean
	public OrderRowMapper orderRowMapper() {
		return new OrderRowMapper();
	}

	@Bean
	public UserRepository userRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
		return new UserRepositoryImpl(jdbcTemplate, userRowMapper);
	}

	@Bean
	public OrderRepository orderRepository(JdbcTemplate jdbcTemplate, OrderRowMapper orderRowMapper) {
		return new OrderRepositoryImpl(jdbcTemplate, orderRowMapper);
	}

	@Bean
	public CommandLineRunner testUserRepository(UserRepository userRepository, OrderRepository orderRepository, JdbcTemplate jdbcTemplate) {
		return args -> {
			// Save a user
			Users user = new Users();
			user.setUsername("Kamal");
			user.setEmail("Kamal@example.com");
			user.setPassword("securepass_2");
			user.setRole(Role.ADMIN);
			user.setIs_active(true);
			user.setFirst_login(true);
//			user.setCreated_at(LocalDateTime.now());

			Orders order = new Orders();
			order.setUserId(7);
			order.setTitle("USB cable");
			order.setType(OrderType.PRODUCT);
			order.setQuantity(1);
			order.setStatus(OrderStatus.EN_COURS);
			order.setComment("Vous allez resevoir votre produit Demain inchaalah");


//			orderRepository.save(order);
//			System.out.println("order saved");


//			userRepository.save(user);
//			System.out.println("User saved!");

			// Fetch by username
			userRepository.findByUsername("abdo").ifPresentOrElse(
					u -> System.out.println("User found: " + u.getEmail()),
					() -> System.out.println("User not found")
			);

			userRepository.deleteById(6L);

			// Count users
			long total = userRepository.countAll();
			System.out.println("Total users: " + total);

			// Fetch and show orders
//			List<Orders> orders = orderRepository.findAllByIsDeletedFalse(0, 14);
//			System.out.println("Orders before deletion:");
//			orders.forEach(o -> System.out.println(" - " + o.getTitle() + " (" + o.getStatus() + ")"));

			userRepository.findByEmail("Kamal@example.com").ifPresentOrElse(
					u -> System.out.println("User found 2: " + u.getUsername()),
					() -> System.out.println("User not found 2")
			);

			// Soft delete
//			orderRepository.softDeleteById(order.getId());
//			System.out.println("Order soft deleted");

			// Fetch again
//			orders = orderRepository.findAllByIsDeletedFalse(0, 10);
//			System.out.println("Orders after deletion:");
//			orders.forEach(o -> System.out.println(" - " + o.getTitle()));
		};
	}

}
