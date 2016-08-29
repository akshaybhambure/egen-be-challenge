package com.egen.challenge.UserManagement;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class UserDAO {

	private static MongoClient dbCLient;
	private static final UserDAO dao = new UserDAO();
	private static DB db;

	private static DBCollection coll;

	public static UserDAO getInstance() {
		return dao;
	}

	@SuppressWarnings("deprecation")
	private UserDAO() {

		dbCLient = new MongoClient("localhost", 27017);
		db = dbCLient.getDB("EgenDB");
		coll = db.getCollection("Users");

	}

	public List<User> getAllUsers() {

		DBCursor entries = coll.find();
		List<User> toRet = new ArrayList<User>();
		while (entries.hasNext()) {
			DBObject dbobj = entries.next();
			User user = (new Gson()).fromJson(dbobj.toString(), User.class);
			toRet.add(user);
		}
		return toRet;

	}

	public User createUser(User user) {

		// validate inputs to respond for bad request
		if ((user.getFirstName() == null || user.getFirstName().isEmpty())
				|| (user.getLastName() == null || user.getLastName().isEmpty())
				|| (user.getEmail() == null || user.getEmail().isEmpty()))
			return null;
		// check if user already present
		BasicDBObject query = new BasicDBObject("firstName", user.getFirstName())
				.append("lastName", user.getLastName())
				.append("email", user.getEmail());
		User duplicate = findQueryObject(query);

		if (duplicate == null) {
			// create new User
			user.generateID();
			user.generateDate();
			BasicDBObject obj = (BasicDBObject) JSON.parse(Utility.toJson(user));
			if (coll.insert(obj).wasAcknowledged()) {
				return user;
			}
			return null;
		} else if (duplicate.getId().isEmpty() || duplicate.getId() == null) {
			// generating ID for already existing entry.
			duplicate.generateID();
			return duplicate;
		}

		return duplicate;
	}

	public User updateUser(User user) {

		// validate null id to return bad request
		if (user.getId() == null || user.getId().isEmpty())
			return null;

		BasicDBObject query = new BasicDBObject("id", user.getId());
		User duplicate = findQueryObject(query);
		if (duplicate != null) {

			// update user
			BasicDBObject obj = (BasicDBObject) JSON.parse(Utility.toJson(user));
			if (coll.update(query, obj).wasAcknowledged()) {
				return user;
			}
			return null;
		}

		return null;

	}

	public User findQueryObject(BasicDBObject query) {

		DBCursor entries = coll.find(query);
		if (entries.hasNext()) {

			DBObject dbobj = entries.next();
			User user = (new Gson()).fromJson(dbobj.toString(), User.class);
			return user;
		}
		return null;

	}

}
