/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Sobe;
import com.mycompany.methotels.interfaces.SobeWebServiceInterface;
import java.util.List;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.services.Response;

/**
 *
 * @author BASKETBALL IN HEART
 */
public class SobeWebServiceImpl implements SobeWebServiceInterface {

    @Inject
    private HotelsDaoImpl hd;

    @Override
    public List<Sobe> getAll() {
        return (hd.getListaSvihSoba());
    }

    @Override
    public Sobe getDrzava(Integer id) {
        return hd.getSobaById(id);
    }

    @Override
    public Response post(Sobe sobe) {
        hd.dodajSobuiliUpdateuj(sobe);
        return post(sobe);
    }

    @Match("*Sobe*")
    public static void adviseTransactionally(
            HibernateTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
        advisor.addTransactionCommitAdvice(receiver);
    }

    @Contribute(javax.ws.rs.core.Application.class)
    public static void configureRestResources(Configuration<Object> singletons,
            SobeWebServiceInterface drzaveWeb) {
        singletons.add(drzaveWeb);
    }
}
