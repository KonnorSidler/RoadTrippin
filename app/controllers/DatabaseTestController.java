package controllers;

import models.DatabaseTest;
import play.libs.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.databaseTest;

import javax.inject.Inject;
import java.util.List;

public class DatabaseTestController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result returnToDBTest(){
        int newInt = (int) (Math.random() * 100);
        Form<DatabaseTest> dbTestForm = formFactory.form(DatabaseTest.class);
        return ok(databaseTest.render(dbTestForm, newInt));
    }

    // Database function Testing
    public Result getDBTests() {
        List<DatabaseTest> dbTests = DatabaseTest.find.all();
        return ok(Json.toJson(dbTests));
    }

    public Result saveDatabaseTest(Http.Request request) {
        Form<DatabaseTest> dbTestForm = formFactory.form(DatabaseTest.class).bindFromRequest(request);
        DatabaseTest dbTest = dbTestForm.get();
        dbTest.save();
        return redirect(routes.HomeController.index());
    }
}
