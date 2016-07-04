package com.xj.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.xj.model.Demo;
import com.xj.service.exception.UniqueException;

@WebService
public interface DemoManager extends GenericManager<Demo, Long> {

    /**
     * Retrieves a demo by id.
     *
     * @param id the identifier for the demo
     * @return demo
     */
    @GET
    @Path("/demo/{id}")
    Demo getDemo(@PathParam("id") String id);

    /**
     * Retrieves a list of all demo.
     *
     * @return List
     */
	@GET
    @Path("/demos")
    List<Demo> getDemos();

    /**
     * Retrieves a list of all enabled demo.
     *
     * @return List
     */
	@GET
    @Path("/demosEnabled")
    List<Demo> getDemosEnabled();

    /**
     * Search a demo list for search terms.
     * 
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
	@GET
    @Path("/demosSearch/{searchTerm}")
    List<Demo> searchDemos(@PathParam("searchTerm") String searchTerm);

    /**
     * Retrieves a new demo.
     *
     * @return demo
     */
    @GET
    @Path("/demoNew")
    Demo getDemoNew();

    /**
     * Saves a demo's information.
     *
     * @param demo the demo information
     * @return demo the updated demo object
     */
	@POST
    @Path("/demo")
	Demo saveDemo(Demo demo) throws UniqueException, Exception;

    /**
     * Removes a demo from the database by their id
     *
     * @param id the demo's id
     */
    @DELETE
    @Path("/demo/{id}")
    void removeDemo(@PathParam("id") String id) throws Exception;

}