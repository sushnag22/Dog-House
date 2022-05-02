package tech.sushnag22.doghouse.backend.service;

import org.springframework.stereotype.Service;
import tech.sushnag22.doghouse.backend.entity.Users;
import tech.sushnag22.doghouse.backend.repository.UsersRepository;

import java.util.*;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public long count() {
        return usersRepository.count();
    }

    public void delete(Users users) {
        usersRepository.delete(users);
    }

    public void save(Users users){
        usersRepository.save(users);
    }

    public Users findById(Integer id) {

        for (Users u : usersRepository.findAll()) {
            if(u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public Users findUserByUsernameAndPassword(String username, String password) {

        for (Users u : usersRepository.findAll()) {
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

    public List<String> isValid(Users users) {
        List<String> notifications = new ArrayList<>();
        if(!usernameValidator(users.getUsername())) {
            notifications.add("The username must be between 4 and 20 characters");
        }
        if(!passwordValidator(users.getPassword())) {
            notifications.add("The password must be at least 4 characters and contain at least one number");
        }
        if(!emailValidator(users.getEmail())) {
            notifications.add("Wrong email format");
        }
        if(users.getFirstName() == null || users.getFirstName().length() == 0) {
            notifications.add("The first name can't be blank");
        }
        if(users.getLastName() == null || users.getLastName().length() == 0) {
            notifications.add("The last name can't be blank");
        }
        if(this.findByUsername(users.getUsername())) {
            notifications.add("The username already exists");
        }
        if(this.findByEmail(users.getEmail())) {
            notifications.add("There's already an user registered with this email");
        }
        if(notifications.size() == 0) {
            return null;
        }
        return notifications;
    }

    public boolean findByUsername(String username) {
        for (Users u : usersRepository.findAll()) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean findByEmail(String email) {
        for (Users u : usersRepository.findAll()) {
            if(u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
