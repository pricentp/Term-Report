
package services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for insertCovidWeek complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="insertCovidWeek">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="covid" type="{http://services/}covidweek" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertCovidWeek", propOrder = {
    "covid"
})
public class InsertCovidWeek {

    protected Covidweek covid;

    /**
     * Gets the value of the covid property.
     * 
     * @return
     *     possible object is
     *     {@link Covidweek }
     *     
     */
    public Covidweek getCovid() {
        return covid;
    }

    /**
     * Sets the value of the covid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Covidweek }
     *     
     */
    public void setCovid(Covidweek value) {
        this.covid = value;
    }

}
