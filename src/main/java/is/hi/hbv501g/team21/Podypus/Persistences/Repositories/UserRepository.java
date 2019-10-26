package is.hi.hbv501g.team21.Podypus.Persistences.Repositories;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository {
    User findByUsername(String username);
}
