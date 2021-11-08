package controllers;

import static play.mvc.Results.ok;

import play.Logger;
import play.Logger.ALogger;
import play.mvc.Result;

public class MainController  {

    private static final ALogger log = Logger.of(MainController.class);

    public Result index() {
        log.info("HIT!");
        return ok("cool");
    }
}
