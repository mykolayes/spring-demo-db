package com.example.demo_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoDbApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoDbApplication.class, args);

		System.out.println("Adding users:");

		UserService userService = applicationContext.getBean(UserService.class);
		UserEntity user = userService.createUser("firstName1", "lastName1", "email1@example.com");
		System.out.println("Saved new user: " + user);

		UserEntity user2 = userService.createUser("firstName2", "Smith", "email2@example.com");
		System.out.println("Saved new user: " + user2);

		UserEntity user3 = userService.createUser("frstNm3", "Smith", "email3@example.com");
		System.out.println("Saved new user: " + user3);

		System.out.println(System.lineSeparator());
		System.out.println("Finding all users:");

		List<UserEntity> allUsers = userService.findAllUsers();
		allUsers.forEach(userEntity -> System.out.println(userEntity.getFirstName() + " " + userEntity.getLastName()));

		System.out.println(System.lineSeparator());
		System.out.println("Finding users w/ last name Smith:");

		List<UserEntity> allSmithUsers = userService.findSmithUsers();
		allSmithUsers.forEach(userEntity -> System.out.println(userEntity.getFirstName() + " " + userEntity.getLastName()));

		System.out.println(System.lineSeparator());
		System.out.println("Finding users whose first or last name contain 'a':");

		List<UserEntity> allSpecifiedNameUsers = userService.findSpecifiedNameUsers("a");
		allSpecifiedNameUsers.forEach(userEntity -> System.out.println(userEntity.getFirstName() + " " + userEntity.getLastName()));

		System.out.println(System.lineSeparator());
	}

}
