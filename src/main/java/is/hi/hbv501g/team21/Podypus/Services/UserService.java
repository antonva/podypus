package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;

import java.util.List;


public interface UserService {
  User save(User user);
  void delete(User user);
  List<User> findAll();
  User findByUsername(String username);
  User findByEmail(String email);
  boolean loginUser(User user);
  void logOutUser(String username);
  boolean isAuthenticated(User user);
}
