/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.interfaces.HotelsDAO;
import com.mycompany.methotels.entities.Sobe;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 *
 * @author BASKETBALL IN HEART
 */
@Import(library = "context:/js/AllTogether.js")
public class DodavanjeSobe {

    @Property
    private Sobe sobe;
    @Property
    private Sobe onesoba;
    @Inject
    private HotelsDAO hotelsDao;
    @Property
    private List<Sobe> sveSobe;
    @InjectComponent
    private Zone zoneSobe;
    @InjectComponent
    private Zone formZone;
    @Inject
    private Request request;
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
    @Inject
    private ComponentResources _componentResources;

    void onActivate() {
        sveSobe = hotelsDao.getListaSvihSoba();
    }

    @CommitAfter
    void setImeSobe(Long id, String value) {
        Sobe sobaa = (Sobe) hotelsDao.getSobaById(id.intValue());
        sobaa.setImeSobe(value);
        System.out.println("cuvam sobu");
        hotelsDao.dodajSobuiliUpdateuj(sobaa);
    }

    @CommitAfter
    Object onSuccess() {
        hotelsDao.dodajSobuiliUpdateuj(sobe);
        sveSobe = hotelsDao.getListaSvihSoba();
        sobe = new Sobe();
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(zoneSobe).addRender(formZone);
        }
        return request.isXHR() ? zoneSobe.getBody() : null;
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        hotelsDao.obrisiSobu(id);
        sveSobe = hotelsDao.getListaSvihSoba();
        return request.isXHR() ? zoneSobe.getBody() : null;
    }

    @CommitAfter
    Object onActionFromEdit(Sobe sobee) {
        sobe = sobee;
        return request.isXHR() ? formZone.getBody() : null;
    }
}
