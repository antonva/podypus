package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
//@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name="username", unique=true)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() { return this.username; }

    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){return password;}

    public void setPassword(String password) {
        this.password = password;
    }
}
