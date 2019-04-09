import org.junit.Test;
import play.test.WithApplication;
import play.twirl.api.Content;
import models.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A functional test starts a Play application for every test.
 *
 * https://www.playframework.com/documentation/latest/JavaFunctionalTest
 */
public class FunctionalTest extends WithApplication {

    @Test
    public void renderTemplate() {
        // If you are calling out to Assets, then you must instantiate an application
        // because it makes use of assets metadata that is configured from
        // the application.

        Form<UserAccount> userForm = formFactory.form(UserAccount.class);
        Form<TripInfo> tripForm = formFactory.form(TripInfo.class);

        Content html = views.html.index.render(userForm, tripForm);
        assertThat("text/html").isEqualTo(html.contentType());
        assertThat(html.body()).contains("Route");
    }
}
