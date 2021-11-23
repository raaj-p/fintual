/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
/**
 *
 * @author raaj
 *  NOTE: PARA ESTE CLASE Y LO DEL STOCK, HE PUESTO UN CHECK BIEN BASICO EN EL CASO DE QUE NO HAYA
 *  DATOS DE UNA STOCK PARA UNA FECHA EN PARTICULAR. HE ASUMIDO QUE EL PRECIO SERA 0.
 */
public class Portfolio {
    ArrayList<Stock> portFolio;
    public Portfolio(ArrayList<Stock> portFolio){
        this.portFolio = portFolio;
    }
    
    public double getProfit(Date fromDate, Date toDate){
        double profit = 0.0;
        if(fromDate.after(toDate))
            return profit;
        for(Stock currentStock:portFolio){
            double toPrice = currentStock.getPriceForDate(toDate);
            double fromPrice = currentStock.getPriceForDate(fromDate);
            profit = profit + toPrice - fromPrice;
        }
        return profit;
    }
    
    /*
      Function returns , for a range of 2 dates, the profit accumudated for each year.
      Output is a hashmap where the key is the year and the value is the profit
     If data doesnt exist for a stock on a particular date, the price is assumed to be 0. 
    */
    public HashMap<Integer,Double> getAnnualizedProfit(Date fromDate, Date toDate){
        // NOTE: THE YEAR NEEDS TO BE EXTRACTED LIKE THIS DUE TO METHODS FROM DATE CLASS BEING DEPRECATED
        HashMap<Integer,Double> annualProfitMap = new HashMap<>();
        if(fromDate.after(toDate))
            return annualProfitMap;
        Calendar fromCalendar = Calendar.getInstance();
        Calendar toCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);
        toCalendar.setTime(toDate);
        int fromYear = fromCalendar.get(Calendar.YEAR);
        int toYear = toCalendar.get(Calendar.YEAR);
        if(fromYear == toYear){
            annualProfitMap.put(toYear, getProfit(fromDate,toDate));
        }
        else{
            Calendar fromCalendarDec = Calendar.getInstance();
            fromCalendarDec.set(fromYear,Calendar.DECEMBER,31);
            Date fromDateDec = fromCalendarDec.getTime();
            
            Calendar toCalendarJan = Calendar.getInstance();
            toCalendarJan.set(toYear, Calendar.JANUARY, 1);
            Date toDateJan = toCalendarJan.getTime();
            
            annualProfitMap.put(toYear, getProfit(toDateJan,toDate));
            annualProfitMap.put(fromYear, getProfit(fromDate,fromDateDec));
            for(int year=fromYear+1;year<toYear;year++){
                Calendar fromCalendarMiddle = Calendar.getInstance();
                Calendar toCalendarMiddle = Calendar.getInstance();
                fromCalendarMiddle.set(year,Calendar.JANUARY, 1);
                toCalendarMiddle.set(year,Calendar.DECEMBER,31);
                double fromMiddlePrice = getProfit(fromCalendarMiddle.getTime(),toCalendarMiddle.getTime());
                annualProfitMap.put(year, fromMiddlePrice);
            }
        }
        return annualProfitMap;
    }
    
}
