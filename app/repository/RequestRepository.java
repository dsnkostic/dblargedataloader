package repository;

import java.util.List;
import java.util.concurrent.CompletionStage;
import models.Request;

public interface RequestRepository {
  CompletionStage<Request> create(Request model);
  CompletionStage<List<Request>> createAll(List<Request> model);
  CompletionStage<Integer> deleteAll();
}
