package com.egen.challenge.UserManagement;

import static spark.Spark.*;
import static com.egen.challenge.UserManagement.Utility.*;

public class UserController {

	public UserController(final UserService userService) {

		get("/users", (req, res) -> userService.getAllUsers(), json());

		post("/users", (req, res) -> {
			User user = Utility.fromJson(req.body());
			User toRet = userService.createUser(user);
			if (toRet != null) {
				return toRet;
			}
			res.status(421);
			return new ResponseError("User creation failed. Server unable to process request.");
		} , json());

		put("/users", (req, res) -> {
			User user = Utility.fromJson(req.body());
			User toRet = userService.updateUser(user);
			if (toRet != null) {
				return toRet;
			}
			;
			res.status(404);
			return new ResponseError("User not found");
		} , json());

		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
	}
}
