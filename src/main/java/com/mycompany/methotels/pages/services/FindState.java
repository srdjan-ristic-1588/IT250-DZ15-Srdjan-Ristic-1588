/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages.services;

import com.mycompany.methotels.entities.Sobe;
import com.mycompany.methotels.interfaces.HotelsDAO;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author BASKETBALL IN HEART
 */
public class FindState {

    @Inject
    private Request request;
    @Property
    private List<Sobe> sobe;
    @Property
    private Sobe soba;
    @Inject
    private HotelsDAO hd;

    Object onActivate(@RequestParameter("ime") String ime) {
        if (sobe == null) {
            sobe = new ArrayList<Sobe>();
        }
        String response = "<table class=\"navigation\" > <th>\n"
                + " Ime sobe\n"
                + " </th>\n"
                + " ";
        sobe = hd.getListaSobaPoImenu(ime);
        for (Sobe d : sobe) {
            response += (" <tr>\n"
                    + " <td> " + d.getImeSobe() + "</td>\n"
                    + " </tr>");
        }
        response += "</table>";
        return new TextStreamResponse("text/plain", response);
    }
}
