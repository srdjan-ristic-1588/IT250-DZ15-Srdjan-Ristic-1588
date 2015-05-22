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
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author BASKETBALL IN HEART
 */
public class PaginationGrid {

    @Inject
    private HotelsDAO hd;
    private int size = 20;

    Object onActivate(@RequestParameter("page") int page) {
        Class<?> act = null;
        int sizeOfAll = hd.allActiveSizeDrzave();
        List<Sobe> lista = new ArrayList<Sobe>();
        String response = "<table class=\"navigation\" > <th>\n"
                + " Ime sobe\n"
                + " </th>\n"
                + " ";
        lista = hd.loadActiveFromTo(page);
        for (Sobe d : lista) {
            response += (" <tr>\n"
                    + " <td> " + d.getImeSobe() + "</td>\n"
                    + " </tr>");
        }
        response += "</table>";
        float ceil = (float) sizeOfAll / (float) 20;
        int totalPageSizes = (int) Math.ceil(ceil);
        response += "<ul class=\"pagination\">";
        for (int i = 1; i <= totalPageSizes; i++) {
            if (page == i) {
                response += ("<li class=\"callservice active\"><a href=\"#\">" + i + "</a></li>\n");
            } else {
                response += ("<li class=\"callservice\"><a href=\"#\">" + i + "</a></li>\n");
            }
        }
        response += "</ul>";
        return new TextStreamResponse("text/plain", response);
    }
}
