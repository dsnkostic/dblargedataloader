package helper;

import akka.actor.ActorSystem;
import javax.inject.Inject;
import play.libs.concurrent.CustomExecutionContext;

/**
 * This is how you define the execution context in the play framework.
 * Essentially, this is a thread pool that takes an id of the thread pool, defined in application.conf
 */
public class DatabaseExecutionContext extends CustomExecutionContext {

  @Inject
  public DatabaseExecutionContext(ActorSystem actorSystem) {
    super(actorSystem, "db-task");
  }
}
