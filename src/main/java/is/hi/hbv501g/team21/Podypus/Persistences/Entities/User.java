package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String email;
    private String password;

    public User(){
    }

    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public long getId(){return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getName() { return this.userName; }

    public void setName(String name) {this.userName = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){return password;}

    public void setPassword(String password) {
        this.password = password;
    }
}
