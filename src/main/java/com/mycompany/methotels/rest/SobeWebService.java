package com.mycompany.methotels.rest;

import com.mycompany.methotels.entities.Sobe;
import com.mycompany.methotels.interfaces.HotelsDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.tapestry5.ioc.annotations.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BASKETBALL IN HEART
 */
@Path("/sobeservice")
public class SobeWebService {

    @Inject
    private HotelsDAO hd;

    @GET
    @Produces({"application/json"}) // uvde koristimo /json a mozemo i /xml
    public List<Sobe> getAll() {
        return (hd.getListaSvihSoba());
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"}) // uvde koristimo /json a mozemo i /xml
    public Sobe getSoba(@PathParam("id") Integer id) {
        return hd.getSobaById(id);
    }

}
