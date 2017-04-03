package controllers;

import play.mvc.*;
/**
 * Manage a database of computers
 */
public class HomeController  extends Controller {

    public Result index() {
        return ok("It works!");
    }

}
            
