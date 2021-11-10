package repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import helper.DatabaseExecutionContext;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import models.Request;
import play.db.jpa.JPAApi;

@Singleton
public class RequestJPARepository implements RequestRepository {

  private final JPAApi jpaApi;
  private final DatabaseExecutionContext ec;

  @Inject
  public RequestJPARepository(JPAApi jpaApi, DatabaseExecutionContext ec) {
    this.jpaApi = jpaApi;
    this.ec = ec;
  }

  @Override
  public CompletionStage<Request> create(Request model) {
    return supplyAsync(() -> wrap(em -> insert(em, model)), ec);
  }

  @Override
  public CompletionStage<List<Request>> createAll(List<Request> models) {
    return supplyAsync(() -> wrap(em -> models.stream().map(model -> insert(em, model)).collect(Collectors.toList())), ec);
  }

  @Override
  public CompletionStage<Integer> deleteAll() {
    return supplyAsync(() -> wrap(em -> em.createQuery("DELETE FROM Request pr").executeUpdate()), ec);
  }

  private <T> T wrap(Function<EntityManager, T> function) {
    return jpaApi.withTransaction(function);
  }

  private Request insert(EntityManager em, Request prData) {
    return em.merge(prData);
  }
}
