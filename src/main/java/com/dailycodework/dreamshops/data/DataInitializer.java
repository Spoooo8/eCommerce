package com.dailycodework.dreamshops.data;

import com.dailycodework.dreamshops.model.Users;
import com.dailycodework.dreamshops.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dailycodework.dreamshops.model.Role;

import java.util.Optional;
import java.util.Set;

@Transactional
@Component
public class DataInitializer implements ApplicationListener<ApplicationEvent> {
   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_ADMIN", "ROLE_USER");
        createDefaultUserIfNotExists();
        createDefaultRoleIfNotExists(defaultRoles);
        createDefaultAdminIfNotExists();
    }

    private void createDefaultUserIfNotExists() {
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        if(userRole.isPresent()) {
            for (int i = 1; i <= 5; i++) {
                String defaultEmail = "user" + i + "@email.com";
                if (userRepository.existsByEmail(defaultEmail)) {
                    continue;
                }
                Users user = new Users();
                user.setFirstName("The User");
                user.setLastName("User " + i);
                user.setEmail(defaultEmail);
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRoles(Set.of(userRole.get()));
                userRepository.save(user);
                System.out.println("Default vet user" + i + "created successfully");
            }
        }
    }
    private void createDefaultAdminIfNotExists() {
        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
        if(adminRole.isPresent()) {
            for (int i = 1; i <= 2; i++) {
                String defaultEmail = "admin" + i + "@email.com";
                if (userRepository.existsByEmail(defaultEmail)) {
                    continue;
                }
                Users user = new Users();
                user.setFirstName("Admin");
                user.setLastName("Admin " + i);
                user.setEmail(defaultEmail);
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRoles(Set.of(adminRole.get()));
                userRepository.save(user);
                System.out.println("Default admin user" + i + "created successfully");
            }
        }

    }
    private void createDefaultRoleIfNotExists(Set<String> roles) {
        roles.stream()
                .filter(role -> roleRepository.findByName(role).isEmpty())
                .map(Role::new)
                .forEach(roleRepository::save);
    }

}
