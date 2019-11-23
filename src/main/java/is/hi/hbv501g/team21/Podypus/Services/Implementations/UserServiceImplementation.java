package is.hi.hbv501g.team21.Podypus.Services.Implementations;


import is.hi.hbv501g.team21.Podypus.Persistences.Entities.LoginForm;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Persistences.Repositories.UserRepository;
import is.hi.hbv501g.team21.Podypus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private List<String> loggedInUsers;


    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.loggedInUsers = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User loginUser(LoginForm loginForm) {
        User exists = findByUsername(loginForm.getUsername());
        if (exists != null) {
            if (exists.getPassword().equals(loginForm.getPassword())) {
                this.loggedInUsers.add(loginForm.getUsername());
                return exists;
            }
        }
        return null;
        //return "fragments/Login :: noUser"; TODO: ÞARF AÐ SKILA ÞESSU
    }

    @Override
    public void logOutUser(String username) {
        this.loggedInUsers.remove(this.loggedInUsers.indexOf(username));
    }

    @Override
    public boolean isAuthenticated(String username) {
        if (this.loggedInUsers.indexOf(username) >= 0) {
            return true;
        }
        return false;
    }
}
