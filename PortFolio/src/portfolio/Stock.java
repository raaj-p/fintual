/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;
import java.util.Date;
import java.util.HashMap;
/**
 *
 * @author raaj
 */
public class Stock {
    HashMap<Date,Double> priceMap;
    public Stock(HashMap<Date, Double> priceMap){
        this.priceMap = priceMap;
    }
    
    public void setPriceForDate(Date date, double price){
        priceMap.put(date, price);
    }
    
    public double getPriceForDate(Date date){
        if(priceMap.containsKey(date))
            return priceMap.get(date);
        return 0.0;
    }
    
    
}
