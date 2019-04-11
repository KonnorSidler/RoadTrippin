package controllers;

import models.UserAccount;
import play.libs.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.userSettings;

import javax.inject.Inject;
import java.util.List;

public class UserAccountController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result returnHome(){
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

    public Result updateUser(Http.Request request){
        Form<UserAccount> userForm = formFactory.form(UserAccount.class).bindFromRequest(request);
        UserAccount user = userForm.get();
        UserAccount newUserInfo = UserAccount.find.byId(user.getId());
        newUserInfo.setLocation(user.getLocation());
        newUserInfo.setName(user.getName());
        newUserInfo.save();
        return ok(userSettings.render(userForm));
    }

    public Result createUser(Http.Request request){
        Form<UserAccount> userForm = formFactory.form(UserAccount.class).bindFromRequest(request);
        UserAccount user = userForm.get();
        UserAccount newUser = new UserAccount(1L,"","","");
        newUser.setLocation(user.getLocation());
        newUser.setId(System.currentTimeMillis());
        newUser.setName(user.getName());
        newUser.save();
        return ok(userSettings.render(userForm));
    }

    public Result getResultWithString(String response) {

        StringBuilder sb = new StringBuilder(response);

        return ok("success");
    }
}
