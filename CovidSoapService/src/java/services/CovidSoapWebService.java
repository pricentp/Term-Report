/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Covidweek;

/**
 *
 * @author price
 */
@WebService(serviceName = "CovidSoapWebService")
public class CovidSoapWebService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CovidSoapServicePU");

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findAllCovidWeek")
    public List<Covidweek> findAllCovidWeek() {
        EntityManager em = emf.createEntityManager();
        List<Covidweek> coList = (List<Covidweek>) em.createNamedQuery("Covidweek.findAll").getResultList();
        return coList;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertCovidWeek")
    @Oneway
    public void insertCovidWeek(@WebParam(name = "covid") Covidweek covid) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(covid);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    
    
}
