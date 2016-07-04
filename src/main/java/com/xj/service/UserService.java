package com.xj.service;

import com.xj.model.User;
import com.xj.service.exception.XdpGroupException;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
//@Path("/users") // Modified by wx Ver0.0.1.
public interface UserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    @GET
    //@Path("{id}")
	@Path("/user/{id}") // Modified by wx Ver0.0.1.
    User getUser(@PathParam("id") String userId);

    /**
     * Finds a user by their username.
     *
     * @param username the user's username used to login
     * @return User a populated user object
     */
    @GET
    @Path("/userByUsername/{username}")
    User getUserByUsername(@PathParam("username") String username);

    /**
     * Retrieves a list of all users.
     *
     * @return List
     */
    @GET
    @Path("/users") // Added by wx Ver0.0.1.
    List<User> getUsers();

    /**
     * Retrieves a list of all enabled users.
     *
     * @return List
     */
    @GET
    @Path("/usersEnabled")
    List<User> getUsersEnabled(); // Added by wx Ver0.0.1.

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @return updated user
     * @throws UserExistsException thrown when user already exists
     */
    @POST
    @Path("/user") // Added by wx Ver0.0.1.//altered by wmc 2013/10/26
    User saveUser(User user,String[] relatedXdpGroups) throws UserExistsException,XdpGroupException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    @DELETE
    @Path("/user") // Added by wx Ver0.0.1.
	//void removeUser(String userId);
    void removeUser(String userId) throws Exception; // Modified by wx Ver0.0.1.
}
