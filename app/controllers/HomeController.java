package controllers;

import play.mvc.*;
import views.html.*;
import models.Person;
import models.Trip;
import play.data.FormFactory;
import play.data.Form;
import javax.inject.Inject;
import java.util.List;

import play.libs.Json;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

  @Inject
  FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
      Form<Person> personForm = formFactory.form(Person.class);
      Form<Trip> tripForm = formFactory.form(Trip.class);
      return ok(index.render(personForm, tripForm));
    }

    public Result addPerson(Http.Request request) {
      Form<Person> personForm = formFactory.form(Person.class).bindFromRequest(request);
      Person person = personForm.get();
      person.save();
      return redirect(routes.HomeController.index());
    }

    public Result getPersons() {
      List<Person> persons = Person.find.all();
      return ok(Json.toJson(persons));
    }

//    public Result updateUserLocation(Http.Request request){
//      Form<Person> locationForm = formFactory.form(Person.class).bindFromRequest(request);
//      Person personData = locationForm.get();
//      personData.save();
//      return redirect(routes.HomeController.index());
//    }

    public Result addTrip(Http.Request request) {
      Form<Trip> tripInfoForm = formFactory.form(Trip.class).bindFromRequest(request);
      Trip tripData = tripInfoForm.get();
      tripData.save();
      return redirect(routes.HomeController.index());
    }

    public Result getRoutes() {
      List<Trip> trips = Trip.find.all();
      return ok(Json.toJson(trips));
    }

}
