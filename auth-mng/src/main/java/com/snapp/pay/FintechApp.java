package com.snapp.pay;

import com.snapp.pay.auth.model.Role;
import com.snapp.pay.auth.model.User;
import com.snapp.pay.auth.repository.RoleRepository;
import com.snapp.pay.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class FintechApp {
    public static void main(String[] args) {
        SpringApplication.run(FintechApp.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                List<Role> roles = List.of("ADMIN", "TELLER").stream().map(role -> roleRepository.save(new Role(role))).collect(Collectors.toList());
                roles.forEach(role -> userRepository.save(new User(null, null, role.getName().toLowerCase(),
                        passwordEncoder.encode(role.getName().toLowerCase()), role)));
                // initializing dummy customers
                Role customerRole = roleRepository.save(new Role("CUSTOMER"));
                List<User> customers = List.of(
                        new User("Akbar", "Akbari", "akbar", passwordEncoder.encode("akbar"), customerRole),
                        new User("Asghar", "Asghari", "asghar", passwordEncoder.encode("asghar"), customerRole));
                userRepository.saveAll(customers);
            }
        };
    }

}