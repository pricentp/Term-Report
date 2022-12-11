
package services;

import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CovidSoapWebService", targetNamespace = "http://services/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CovidSoapWebService {


    /**
     * 
     * @return
     *     returns java.util.List<services.Covidweek>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAllCovidWeek", targetNamespace = "http://services/", className = "services.FindAllCovidWeek")
    @ResponseWrapper(localName = "findAllCovidWeekResponse", targetNamespace = "http://services/", className = "services.FindAllCovidWeekResponse")
    @Action(input = "http://services/CovidSoapWebService/findAllCovidWeekRequest", output = "http://services/CovidSoapWebService/findAllCovidWeekResponse")
    public List<Covidweek> findAllCovidWeek();

    /**
     * 
     * @param covid
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "insertCovidWeek", targetNamespace = "http://services/", className = "services.InsertCovidWeek")
    @Action(input = "http://services/CovidSoapWebService/insertCovidWeek")
    public void insertCovidWeek(
        @WebParam(name = "covid", targetNamespace = "")
        Covidweek covid);

}
