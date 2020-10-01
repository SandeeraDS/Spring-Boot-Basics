package com.springboot.jpa.jpalearning.DAO;

import com.springboot.jpa.jpalearning.DAO.UserDAOService;
import com.springboot.jpa.jpalearning.DAO.UserDaoServiceCommandLineRunner;
import com.springboot.jpa.jpalearning.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoRepServiceCommandLineRunner implements CommandLineRunner {

	private static Logger logger = LogManager.getLogger(UserDaoServiceCommandLineRunner.class);


	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		User user = new User("Jill", "Admin");
		userRepository.save(user);
		logger.info("New User is created : " + user);

		Optional<User> userWithIdOne = userRepository.findById(1L);
		logger.info("User is retrived : " + userWithIdOne);

		List<User> users = userRepository.findAll();
		logger.info("All Users : " + users);
	}
}