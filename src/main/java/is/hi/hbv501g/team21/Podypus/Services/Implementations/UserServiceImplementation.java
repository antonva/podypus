package is.hi.hbv501g.team21.Podypus.Services.Implementations;


import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Persistences.Repositories.UserRepository;
import is.hi.hbv501g.team21.Podypus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public User loginUser(User user) {
        User exists = findByUsername(user.getUsername());
        if (exists != null) {
            //System.out.println(exists.getUsername());
            if (exists.getPassword().equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    //This is for demonstration purposes only, for final version an e-mail verification step would be included.
    @Override
    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null)
            user.setPassword(newPassword);
            userRepository.save(user);
        //else eitthvað og villa af því það er enginn notandi með þetta e-mail til
    }
}
