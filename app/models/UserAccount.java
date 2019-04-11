package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserAccount extends Model {

  @Id
  private long id;

  private String name;

  private String location;

  private String password;

    public static final Finder<Long, UserAccount> find = new Finder<>(UserAccount.class);

    public UserAccount(long id, String name, String location, String password) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.password = password;
    }

    public long createId(){
        Long newId;
        return newId = System.currentTimeMillis();

    }

    public long getId() {
    return id;
  }

    public void setId(long id) {
    this.id = id;
  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    this.password = password;
  }


}