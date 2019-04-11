import akka.actor.ActorSystem;
import controllers.AsyncController;
import controllers.CountController;
import controllers.UserAccountController;
import models.UserAccount;
import org.junit.Test;
import play.mvc.Result;
import scala.concurrent.ExecutionContextExecutor;

import java.util.concurrent.CompletionStage;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static play.test.Helpers.*;
import static org.mockito.Mockito.*;

/**
 * Unit testing does not require Play application start up.
 *
 * https://www.playframework.com/documentation/latest/JavaTest
 */
public class UnitTest {

    @Test
    public void getUsersWithSpy() {
        UserAccountController userAccountController = new UserAccountController();
        UserAccountController spyAccountController = spy(userAccountController);
        Result result = spyAccountController.getResultWithString("found json");
        doReturn(result).when(spyAccountController).listUsers();
        assertThat(contentAsString(spyAccountController.listUsers()).contains("found json"));
    }

    @Test
    public void testCreateUser() {
        UserAccount userAccount = new UserAccount(1L, "Niko", "State College", "password");
        assertEquals(1L, userAccount.getId());
        assertEquals("Niko", userAccount.getName());
        assertEquals("State College", userAccount.getLocation());
        assertEquals("password", userAccount.getPassword());
    }

    @Test
    public void testIdCreation(){
        UserAccount userAccount = new UserAccount(1L, "test", "test", "test");
        Long randomId = userAccount.createId();
        userAccount.setId(randomId);
        assertThat(randomId).isEqualTo(userAccount.getId());
    }

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

}
