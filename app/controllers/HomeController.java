package controllers;

import models.TripRating;
import play.mvc.*;
import views.html.*;
import models.Person;
import models.TripInfo;
import play.data.FormFactory;
import play.data.Form;
import javax.inject.Inject;
import java.util.List;
import io.ebean.Model;
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
        Form<TripInfo> tripForm = formFactory.form(TripInfo.class);
        Form<TripRating> tripRating = formFactory.form(TripRating.class);

        return ok(index.render(personForm, tripForm));
    }

    public Result addPerson(Http.Request request) {
        Form<Person> personForm = formFactory.form(Person.class).bindFromRequest(request);
        Person person = personForm.get();
        person.save();
        return redirect(routes.HomeController.index());
    }

    public Result saveRouteInfo(Http.Request request) {
        Form<TripInfo> tripInfoForm = formFactory.form(TripInfo.class).bindFromRequest(request);
        TripInfo tripData = tripInfoForm.get();
        tripData.save();
        return redirect(routes.HomeController.index());
    }

    public Result saveTripRating(Http.Request request) {
        Form<TripRating> tripRatingForm = formFactory.form(TripRating.class).bindFromRequest(request);
        TripRating rating = tripRatingForm.get();
        rating.save();
        return redirect(routes.HomeController.index());
    }

    public Result getPersons() {
        List<Person> persons = Person.find.all();
        return ok(Json.toJson(persons));
    }

    public Result getRoutes() {
        List<TripInfo> trips = TripInfo.find.all();
        return ok(Json.toJson(trips));
    }

    public Result getTripRating() {
        List<TripRating> ratings = TripRating.find.all();
        return ok(Json.toJson(ratings));
    }

    public Result testLink(Http.Request request) {

      return ok("Got it!" + request.body().asJson());

    }

}
