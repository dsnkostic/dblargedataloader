package helper;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.inject.Inject;
import models.Request;
import play.Logger;
import play.Logger.ALogger;
import repository.RequestJPARepository;
import resources.bulk.CompanyBulkResource;
import resources.bulk.CreateBulkResource;
import resources.regular.CompanyResource;
import resources.regular.CreateResource;
import resources.response.CreateResponse;

public class ProcessingHandler {

  private static final ALogger log = Logger.of(ProcessingHandler.class);

  private final RequestJPARepository repository;
  private final BackendExecutionContext bec;

  @Inject
  public ProcessingHandler(RequestJPARepository repository, BackendExecutionContext bec) {
    this.repository = repository;
    this.bec = bec;
  }

  private String displaySWSec(Instant starts) {
    Instant ends = Instant.now();
    double result = Duration.between(starts, ends).toMillis() * 0.001;
    return String.format("%.4fs",result);
  }

  private CompletionStage<Void> saveRequestsForDepartment(String companyID, String departmentID, int numberOfRequests) {
    Instant starts = Instant.now();
    List<Request> requests = IntStream.range(0, numberOfRequests)
        .mapToObj(i -> new Request(companyID, departmentID, companyID + "_" + departmentID + "_" + i))
        .collect(Collectors.toList());
    return repository.createAll(requests)
        .thenAccept(no -> log.info("Company {}, department {}, created {} Requests: {}",
            companyID, departmentID, numberOfRequests, displaySWSec(starts)));
  }

  private CompletionStage<Void> saveRequestsForCompany(CompanyResource resource) {
      Instant starts = Instant.now();
      return CompletableFuture.allOf(resource.getDepartments().stream()
              .map(department -> saveRequestsForDepartment(resource.getCompanyID(),
                  department.getDepartmentID(), department.getNumberOfRequests()).toCompletableFuture())
              .toArray(CompletableFuture[]::new))
              .thenAccept(no -> log.info("Finished creating requests for Company {}: {}",
                  resource.getCompanyID(), displaySWSec(starts)));
  }

  private CompletionStage<Void> saveRequestsForCompanyBulk(CompanyBulkResource resource) {
    Instant starts = Instant.now();
    return CompletableFuture.allOf(IntStream
            .range(0, resource.getDepartmentRequests().size())
            .mapToObj(i -> saveRequestsForDepartment(resource.getCompanyID(), resource.getDepartmentBase() + i,
                resource.getDepartmentRequests().get(i)).toCompletableFuture()).toArray(CompletableFuture[]::new))
            .thenAccept(no -> log.info("Finished creating requests for Company {}: {}",
                resource.getCompanyID(), displaySWSec(starts)));
  }

  public CompletionStage<CreateResponse> generate(CreateResource resource) {
    runAsync(() -> {
      Instant starts = Instant.now();
      CompletableFuture.allOf(resource.getCompanies().stream()
              .map(companyResource -> saveRequestsForCompany(companyResource).toCompletableFuture())
              .toArray(CompletableFuture[]::new))
          .thenAccept(no -> log.info("Done regular import: {}", displaySWSec(starts)));
      log.info("I did my work. Bye!!");
    }, bec);
    return supplyAsync(() -> new CreateResponse(resource));
  }

  public CompletionStage<CreateResponse> generateBulk(CreateBulkResource resource) {
    runAsync(() -> {
      Instant starts = Instant.now();
      CompletableFuture.allOf(resource.getCompanies().stream()
              .map(companyResource -> saveRequestsForCompanyBulk(companyResource).toCompletableFuture())
              .toArray(CompletableFuture[]::new))
          .thenAccept(no -> log.info("Done bulk import: {}", displaySWSec(starts)));
      log.info("I did my work. Bye!!");
    }, bec);
    return supplyAsync(() -> new CreateResponse(resource));
  }

  public CompletionStage<Integer> deleteAll() {
    return repository.deleteAll();
  }
}
