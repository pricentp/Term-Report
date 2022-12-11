/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covidsoapclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.datatype.DatatypeConfigurationException;
import services.Covidweek;

/**
 *
 * @author price
 */
public class CovidSoapClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException, DatatypeConfigurationException {
        readJson();
        List<Covidweek> co = findAllCovidWeek();
        printAllCovidList(co);
        
    }

    private static java.util.List<services.Covidweek> findAllCovidWeek() {
        services.CovidSoapWebService_Service service = new services.CovidSoapWebService_Service();
        services.CovidSoapWebService port = service.getCovidSoapWebServicePort();
        return port.findAllCovidWeek();
    }

    private static void insertCovidWeek(services.Covidweek covid) {
        services.CovidSoapWebService_Service service = new services.CovidSoapWebService_Service();
        services.CovidSoapWebService port = service.getCovidSoapWebServicePort();
        port.insertCovidWeek(covid);
    }
    public static void printAllCovidList(List<Covidweek> aList){
        for( Covidweek a: aList){
            System.out.println("ปีที่แถลง : " + a.getCovidyear());
            System.out.println("สัปดาห์ที่แถลง : " + a.getWeeknum());
            System.out.println("จำนวนผู้ป่วยรายใหม่ : " + a.getNewCase());
            System.out.println("จำนวนผู้ป่วยสะสม : " + a.getTotalCase());
            System.out.println("จำนวนผู้ป่วยรายใหม่ในไทย : " + a.getNewCaseExcludeabroad());
            System.out.println("จำนวนผู้ป่วยสะสมในไทย : " + a.getTotalCaseExcludeabroad());
            System.out.println("จํานวนผู้ป่วยรักษาหายรายใหม่ : " + a.getNewRecovered());
            System.out.println("จํานวนผู้ป่วยรักษาหายสะสม : " + a.getTotalRecovered());
            System.out.println("จํานวนผู้ป่วยตายรายใหม่ : " + a.getNewDeath());
            System.out.println("จํานวนผู้ป่วยตายสะสม : " + a.getTotalDeath());
            System.out.println("จํานวนผู้ป่วยที่มาจากต่างประเทศ : " + a.getCaseForeign());
            System.out.println("จํานวนผู้ป่วยที่อยู่ในเรือนจํา : " + a.getCasePrison());
            System.out.println("จํานวนผู้ป่วยที่เข้ามารับการตรวจ ณ จุดบริการ : " + a.getCaseWalkin());
            System.out.println("จํานวนผู้ป่วยวันก่อนหน้าของวันปัจจุบัน : " + a.getCaseNewPrev());
            System.out.println("จํานวนการเพิ่มขึ้น/ลดลง ของผู้ป่วย : " + a.getCaseNewDiff());
            System.out.println("จํานวนผู้เสียชีวิตวันก่อนหน้าของวันปัจจุบัน : " + a.getDeathNewPrev());
            System.out.println("จํานวนการเพิ่มขึ้น/ลดลง ของผู้เสียชีวิต : " + a.getDeathNewDiff());
            System.out.println("วันที่ปรับปรุง service ล่าสุด : " + a.getUpdateDate());
            System.out.println();
        }
    }
    
    
    public static void readJson() throws IOException, ParseException, DatatypeConfigurationException{
        
        URL obj = new URL("https://covid19.ddc.moph.go.th/api/Cases/today-cases-all");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        //System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            String json = response.toString();
            //System.out.println(json);
            
            JsonReader jsonr = Json.createReader(new StringReader(json));
            JsonArray jsona = jsonr.readArray();
            jsonr.close();
            
            
            System.out.println(jsona);
            for(int i = 0 ; i < jsona.size() ; i++){
                JsonObject firstname = jsona.getJsonObject(i);
            
                int year = firstname.getInt("year");

                int weeknum = firstname.getInt("weeknum");

                int new_case = firstname.getInt("new_case");

                int total_case = firstname.getInt("total_case");

                int new_case_excludeabroad = firstname.getInt("new_case_excludeabroad");

                int total_case_excludeabroad = firstname.getInt("total_case_excludeabroad");

                int new_recovered = firstname.getInt("new_recovered");

                int total_recovered = firstname.getInt("total_recovered");

                int new_death = firstname.getInt("new_death");

                int total_death = firstname.getInt("total_death");

                int case_foreign  = firstname.getInt("case_foreign");

                int case_prison = firstname.getInt("case_prison");

                int case_walkin = firstname.getInt("case_walkin");

                int case_new_prev = firstname.getInt("case_new_prev");

                int case_new_diff = firstname.getInt("case_new_diff");

                int death_new_prev = firstname.getInt("death_new_prev");

                int death_new_diff = firstname.getInt("death_new_diff");

                String update_date = firstname.getString("update_date");
                SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = DateFor.parse(update_date);
                
                addCovidWeek(year,weeknum,new_case,total_case,new_case_excludeabroad,total_case_excludeabroad,new_recovered,total_recovered,new_death,total_death,case_foreign,case_prison,case_walkin,case_new_prev,case_new_diff,death_new_prev,death_new_diff,date);
        
            }
            
        } 
        else {
            System.out.println("GET request did not work.");
        }
    }
    
    public static void addCovidWeek(int year,int weeknum,int new_case,int total_case,int new_case_excludeabroad,int total_case_excludeabroad,int new_recovered,int total_recovered,int new_death,int total_death,int case_foreign,int case_prison,int case_walkin,int case_new_prev,int case_new_diff,int death_new_prev,int death_new_diff,Date date) throws DatatypeConfigurationException{
        Covidweek c = new Covidweek(year,weeknum,new_case,total_case,new_case_excludeabroad,total_case_excludeabroad,new_recovered,total_recovered,new_death,total_death,case_foreign,case_prison,case_walkin,case_new_prev,case_new_diff,death_new_prev,death_new_diff, date);
        insertCovidWeek(c);
    }
}
