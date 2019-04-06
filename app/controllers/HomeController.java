package controllers;

import models.User;
import play.mvc.*;
import views.html.*;
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
      Form<User> userForm = formFactory.form(User.class);
      Form<Trip> tripForm = formFactory.form(Trip.class);
      return ok(index.render(userForm, tripForm));
    }

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
