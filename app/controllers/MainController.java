package controllers;

import static play.mvc.Results.ok;

import helper.ProcessingHandler;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import play.Logger;
import play.Logger.ALogger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import resources.bulk.CreateBulkResource;
import resources.regular.CreateResource;

public class MainController  {

    private static final ALogger log = Logger.of(MainController.class);

    private final ProcessingHandler handler;

    @Inject
    public MainController(ProcessingHandler handler) {
        this.handler = handler;
    }

    public Result index() {
        log.info("HIT!");
        return ok("cool");
    }

    public CompletionStage<Result> create(Http.Request request) {
        CreateResource resource = Json.fromJson(request.body().asJson(), CreateResource.class);
        log.info(resource.toString());
        log.info("Executing regular import");
        return handler.generate(resource)
            .thenApply(Json::toJson)
            .thenApply(Results::created);
    }

    public CompletionStage<Result> createBulk(Http.Request request) {
        CreateBulkResource resource = Json.fromJson(request.body().asJson(), CreateBulkResource.class);
        log.info(resource.toString());
        log.info("Executing regular bulk import");
        return handler.generateBulk(resource)
            .thenApply(Json::toJson)
            .thenApply(Results::created);
    }

    public CompletionStage<Result> deleteAll() {
        log.info("Delete request executing...");
        return handler.deleteAll().thenApply(num -> Results.created("Number of requests deleted: " + num));
    }
}
