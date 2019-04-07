package controllers;

import models.User;
import play.libs.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.userSettings;

import javax.inject.Inject;
import java.util.List;

public class RatingsController extends Controller {

    public Result addRating(Http.Request request) {
        return ok("Got it!" + request.body().asJson());
        //TripRating rating = new TripRating(request.body.asJson().);
    }

    /*
    public Result createUser(Http.Request request){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        User user = userForm.get();
        User newUser = new User();
        newUser.setLocation(user.getLocation());
        newUser.setId(System.currentTimeMillis());
        newUser.setName(user.getName());
        newUser.save();
        return ok(userSettings.render(userForm));
    }
    */
}
