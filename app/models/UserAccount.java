package models;

import io.ebean.Model;
import io.ebean.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccount extends Model {

  @Id
  private long id;

  @Column(unique=true)
  private String username;

  private String location;

  public static final Finder<Long, UserAccount> find = new Finder<>(UserAccount.class);

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public static String longToString(Long id) {
    return "" + id + "";
  }

}