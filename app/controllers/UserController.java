package controllers;

import models.User;
import play.libs.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.UserSettings;

import javax.inject.Inject;
import java.util.List;

public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result returnHome(){
        Form<User> userForm = formFactory.form(User.class);
        return ok(UserSettings.render(userForm));
    }

    public Result getUser(Http.Request request){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        User newUser = userForm.get();
        User foundUser = User.find.byId(newUser.getId());
        return ok(Json.toJson(foundUser));
    }

    public Result listUsers(){
        List<User> users = User.find.all();
        return ok(Json.toJson(users));
    }

    public Result updateUser(Http.Request request){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        User user = userForm.get();
        User newUserInfo = User.find.byId(user.getId());
        newUserInfo.setLocation(user.getLocation());
        newUserInfo.setName(user.getName());
        newUserInfo.save();
        return ok(UserSettings.render(userForm));
    }

    public Result createUser(Http.Request request){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        User user = userForm.get();
        User newUser = new User();
        newUser.setLocation(user.getLocation());
        newUser.setId(System.currentTimeMillis());
        newUser.setName(user.getName());
        newUser.save();
        return ok(UserSettings.render(userForm));
    }
}