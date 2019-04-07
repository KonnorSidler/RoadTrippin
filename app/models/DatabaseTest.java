package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DatabaseTest extends Model {

  @Id
  public long id;

  public String databaseMessage;

  public static final Finder<Long, DatabaseTest> find = new Finder<>(DatabaseTest.class);
}
