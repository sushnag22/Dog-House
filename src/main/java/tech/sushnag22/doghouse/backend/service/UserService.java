package tech.sushnag22.doghouse.backend.service;

import org.springframework.stereotype.Service;
import tech.sushnag22.doghouse.backend.entity.User;
import tech.sushnag22.doghouse.backend.repository.UserRepository;

import java.util.*;

@Service
public class UserService {


    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long count() {
        return userRepository.count();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public void save(User user){
        userRepository.save(user);
    }

    public User findById(Integer id) {

        for (User u : userRepository.findAll()) {
            if(u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public User findUserByUsernameAndPassword(String username, String password) {

        for (User u : userRepository.findAll()) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;

            }
        }
        return null;

    }

    public static boolean usernameValidator(String username) {
        String pattern = ".{4,20}";
        return username.matches(pattern);
    }

    public static boolean passwordValidator(String password) {
        String pattern = "(?=.*[0-9])(?=\\S+$).{4,}";
        return password.matches(pattern);
    }

    public static boolean emailValidator(String email) {
        String pattern = "^(.+)@(.+)$";
        return email.matches(pattern);
    }

    public List<String> isValid(User user) {
        List<String> notifications = new ArrayList<>();
        if(!usernameValidator(user.getUsername())) {
            notifications.add("The username must be between 4 and 20 characters");
        }
        if(!passwordValidator(user.getPassword())) {
            notifications.add("The password must be at least 4 characters and contain at least one number");
        }
        if(!emailValidator(user.getEmail())) {
            notifications.add("Wrong email format");
        }
        if(user.getFirstName() == null || user.getFirstName().length() == 0) {
            notifications.add("The first name can't be blank");
        }
        if(user.getLastName() == null || user.getLastName().length() == 0) {
            notifications.add("The last name can't be blank");
        }
        if(this.findByUsername(user.getUsername())) {
            notifications.add("The username already exists");
        }
        if(this.findByEmail(user.getEmail())) {
            notifications.add("There's already an user registered with this email");
        }
        if(notifications.size() == 0) {
            return null;
        }
        return notifications;
    }

    public boolean findByUsername(String username) {
        for (User u : userRepository.findAll()) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean findByEmail(String email) {
        for (User u : userRepository.findAll()) {
            if(u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
}
