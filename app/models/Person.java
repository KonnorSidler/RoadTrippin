package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person extends Model {

  @Id
  public long id;

  public String name;

  public String location;

  public static final Finder<Long, Person> find = new Finder<>(Person.class);
}
