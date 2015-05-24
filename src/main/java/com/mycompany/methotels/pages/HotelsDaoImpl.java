/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Hoteli;
import com.mycompany.methotels.interfaces.HotelsDAO;
import com.mycompany.methotels.entities.Sobe;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BASKETBALL IN HEART
 */
public class HotelsDaoImpl implements HotelsDAO {

    @Inject
    private Session session;

    @Override
    public List<Sobe> getListaSvihSoba() {
        return session.createCriteria(Sobe.class).list();
    }

    @Override
    public void dodajSobuiliUpdateuj(Sobe sobe) {
        session.persist(sobe);
    }

    @Override
    public List<Hoteli> getListaSvihHotela() {
        return session.createCriteria(Hoteli.class).list();
    }

    @Override
    public Hoteli getHotelIme(String ime) {
        return (Hoteli) session.createCriteria(Hoteli.class).add(Restrictions.eq("ime", ime)).uniqueResult();
    }

    @Override
    public List<Sobe> getListaSobaPoImenu(String ime) {
        return session.createCriteria(Sobe.class).add(Restrictions.ilike("imeSobe", ime + "%")).list();
    }

    @Override
    public List<Sobe> loadActiveFromTo(int from) {
        int page = (from - 1) * 20;
        List<Sobe> lista
                = session.createCriteria(Sobe.class).setFirstResult(page).setMaxResults(20).addOrder(Order.asc("id")).setResultTransformer(
                        Criteria.DISTINCT_ROOT_ENTITY).list();
        return lista;
    }

    @Override
    public int allActiveSizeDrzave() {
        Long l = (Long) session.createCriteria(Sobe.class).setProjection(Projections.rowCount()).uniqueResult();
        return l.intValue();
    }

    @Override
    public void obrisiSobu(Integer id) {
        Sobe drzave = (Sobe) session.createCriteria(Sobe.class).add(Restrictions.eq("id",
                id)).uniqueResult();
        session.delete(drzave);
    }

    @Override
    public Sobe getSobaById(Integer id) {
        return (Sobe) session.createCriteria(Sobe.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

}
