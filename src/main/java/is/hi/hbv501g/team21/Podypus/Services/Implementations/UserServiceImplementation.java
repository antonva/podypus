package is.hi.hbv501g.team21.Podypus.Services.Implementations;


import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisode;
import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.LoginForm;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Persistences.Repositories.UserRepository;
import is.hi.hbv501g.team21.Podypus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    }
    //This is for demonstration purposes only, for final version an e-mail verification step would be included.
    @Override
    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null)
            user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void logOutUser(String username) {
        this.loggedInUsers.remove(this.loggedInUsers.indexOf(username));
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request) {
        Cookie[] clist = request.getCookies();
        if (clist != null && clist.length > 0) {
            Cookie c = clist[0];
            if (this.loggedInUsers.indexOf(c.getValue()) >= 0) {
                return true;
            } else if (userRepository.findByUsername(c.getValue()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserFromCookie(HttpServletRequest request) {
        Cookie[] clist = request.getCookies();
        if (clist != null && clist.length > 0) {
            Cookie c = clist[0];
            if (this.loggedInUsers.indexOf(c.getValue()) >= 0 || userRepository.findByUsername(c.getValue()) != null) {
                return userRepository.findByUsername(c.getValue());
            }
        }
        return null;
    }

    @Override
    public UserEpisode getUserEpisodeById(User u, Long episode_id) {
        UserEpisode ue = userRepository.findByEpisodeId(episode_id, u.getUser_id());
        System.out.println(ue.getPlaybackPosition());
        System.out.println(ue.isPlayed());
        return ue;
    }
}
