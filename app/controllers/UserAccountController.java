package controllers;

import models.UserAccount;
import play.libs.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.createUser;
import views.html.logout;
import views.html.userSettings;
import views.html.login;

import javax.inject.Inject;
import java.util.List;

public class UserAccountController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result showLogout() {
        Form<UserAccount> userForm = formFactory.form(UserAccount.class);
        return ok(logout.render(userForm));
    }

    public Result showLogin() {
        Form<UserAccount> userForm = formFactory.form(UserAccount.class);
        return ok(login.render(userForm));
    }

    public Result login(Http.Request request){
        Form<UserAccount> userForm = formFactory.form(UserAccount.class).bindFromRequest(request);
        UserAccount acc = userForm.get();
        UserAccount findUser = UserAccount.find.byId(acc.getId());
        return redirect("/trip").addingToSession(request, "username", "");
    }

    public Result logout(Http.Request request){
        return redirect("/").removingFromSession(request, "username");
    }

    public Result createUserScreen(){
        Form<UserAccount> userForm = formFactory.form(UserAccount.class);
        return ok(createUser.render(userForm));
    }

    public Result userSettingsScreen(){
        Form<UserAccount> userForm = formFactory.form(UserAccount.class);
        return ok(userSettings.render(userForm));
    }

    public Result getUser(Http.Request request){
        Form<UserAccount> userForm = formFactory.form(UserAccount.class).bindFromRequest(request);
        UserAccount newUser = userForm.get();
        UserAccount foundUser = UserAccount.find.byId(newUser.getId());
        return ok(Json.toJson(foundUser));
    }

    public Result listUsers(){
        List<UserAccount> users = UserAccount.find.all();
        return ok(Json.toJson(users));
    }

    public Result createUser(Http.Request request){
        Form<UserAccount> userForm = formFactory.form(UserAccount.class).bindFromRequest(request);
        UserAccount user = userForm.get();
        UserAccount newUser = new UserAccount();
        newUser.setLocation(user.getLocation());
        newUser.setUsername(user.getUsername());
        newUser.save();
        return redirect("/trip").addingToSession(request, "user", newUser.longToString(newUser.getId()));
    }
}
