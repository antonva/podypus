package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.LoginForm;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UserService {
  User save(User user);
  void delete(User user);
  List<User> findAll();
  User findByUsername(String username);
  User findByEmail(String email);
  void resetPassword(String email, String newPassword);
  User loginUser(LoginForm user);
  void logOutUser(String username);
  boolean isAuthenticated(/*String username,*/ HttpServletRequest request);
}
