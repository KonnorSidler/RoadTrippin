package controllers;

import models.Account;
import play.libs.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.accountSettings;

import javax.inject.Inject;
import java.util.List;

public class AccountController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result returnHome(){
        Form<Account> accountForm = formFactory.form(Account.class);
        return ok(accountSettings.render(accountForm));
    }

    public Result getAccount(Http.Request request){
        Form<Account> accountForm = formFactory.form(Account.class).bindFromRequest(request);
        Account newAccount = accountForm.get();
        Account foundAccount = Account.find.byId(newAccount.getId());
        return ok(Json.toJson(foundAccount));
    }

    public Result listAccounts(){
        List<Account> accounts = Account.find.all();
        return ok(Json.toJson(accounts));
    }

    public Result updateAccount(Http.Request request){
        Form<Account> accountForm = formFactory.form(Account.class).bindFromRequest(request);
        Account account = accountForm.get();
        Account newAccountInfo = Account.find.byId(account.getId());
        newAccountInfo.setLocation(account.getLocation());
        newAccountInfo.setName(account.getName());
        newAccountInfo.save();
        return ok(accountSettings.render(accountForm));
    }

    public Result createAccount(Http.Request request){
        Form<Account> accountForm = formFactory.form(Account.class).bindFromRequest(request);
        Account account = accountForm.get();
        Account newAccount = new Account();
        newAccount.setLocation(account.getLocation());
        newAccount.setId(System.currentTimeMillis());
        newAccount.setName(account.getName());
        newAccount.save();
        return ok(accountSettings.render(accountForm));
    }
}
