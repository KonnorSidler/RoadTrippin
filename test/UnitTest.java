import akka.actor.ActorSystem;
import controllers.AsyncController;
import controllers.CountController;
import controllers.*;
import models.*;
import models.UserAccount;
import org.junit.Test;
import play.mvc.Result;
import scala.concurrent.ExecutionContextExecutor;

import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static play.test.Helpers.contentAsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Unit testing does not require Play application start up.
 *
 * https://www.playframework.com/documentation/latest/JavaTest
 */
public class UnitTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    // Unit test a controller
    @Test
    public void testCount() {
        final CountController controller = new CountController(() -> 49);
        Result result = controller.count();
        assertThat(contentAsString(result)).isEqualTo("49");
    }

    // Unit test a controller with async return
    @Test
    public void testAsync() {
        final ActorSystem actorSystem = ActorSystem.create("test");
        try {
            final ExecutionContextExecutor ec = actorSystem.dispatcher();
            final AsyncController controller = new AsyncController(actorSystem, ec);
            final CompletionStage<Result> future = controller.message();

            // Block until the result is completed
            await().untilAsserted(() ->
                    assertThat(future.toCompletableFuture())
                        .isCompletedWithValueMatching(result -> contentAsString(result).equals("Hi!"))
            );
        } finally {
            actorSystem.terminate();
        }
    }

    @Test
    public void spyRoutes() {
        final HomeController controller = new HomeController();
        final HomeController  spyController = spy(controller);
        Result result = spyController.getRoutes();
        assertThat(result);
        verify(spyController).getRoutes();
    }

    @Test
    public void spySetRating() {
        final RatingsController controller = new RatingsController();
        final RatingsController  spyController = spy(controller);
        Result result = spyController.getRatings();
        assertThat(result);
        verify(spyController).getRatings();
    }

    @Test
    public void spyListUsers() {
        final UserAccountController controller = new UserAccountController();
        final UserAccountController  spyController = spy(controller);
        Result result = spyController.listUsers();
        assertThat(result);
        verify(spyController).listUsers();

    }

}
