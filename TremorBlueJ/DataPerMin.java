
/**
 * All the methods/actions for movement device
 * 
 * @author Kirjsten Blodgett 
 * @version 1.0
 * email: catsanddogskb@hotmail.com
 */
public class DataPerMin
{
    // instance variables - replace the example below with your own
    private String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay; 
    private int HR, format;
    private double vec;

    /**
     * Constructor for objects of class Device when reading in all data
     */
    public DataPerMin(String dateC, String timeC, String axis1C, String axis2C, String axis3C, String stepsC, int heartrate, String luxC, String inclinometerOff, String inclinometerStanding, String inclinometerSitting, String inclinometerLying, double vector,int formatminsec)
    {
        date = dateC;
        time = timeC;
        axis1 = axis1C;
        axis2 = axis2C;
        axis3 = axis3C;
        steps = stepsC;
        HR = heartrate;
        lux = luxC;
        iOff = inclinometerOff;
        iStand = inclinometerStanding;
        iSit = inclinometerSitting; 
        iLay = inclinometerLying;
        vec = vector;     
        format = formatminsec;
    }
    
     /**
     * default constructor for DataPerMin when making tremor objects
     */
    public DataPerMin(int heartrate, String endTime, double vector)
    {
        HR= heartrate;
        time= endTime;
        vec = vector;
    }
    
    /**
     * default constructor for DataPerMin
     */
    public DataPerMin()
    {
    }

    /**
     * returns date
     * @param  
     * @return date
     */
    public String date()
    {
        return date;
    }
    
    /**
     * returns time
     * @return time
     */
    public String time()
    {
        return time;
    }
        
    /**
     * returns axis1
     * @return axis1
     */
    public String axis1()
    {
        return axis1;
    }
    
    /**
     * returns axis2
     * @return axis2
     */
    public String axis2()
    {
        return axis2;
    }
    
    /**
     * returns axis3
     * @return axis3
     */
    public String axis3()
    {
        return axis3;
    }
    
    /**
     * returns steps
     * @return steps
     */
    public String steps()
    {
        return steps;
    }
        
    /**
     * returns heart rate
     * @return HR
     */
    public int HR()
    {
        return HR;
    }
    
    /**
     * returns lux
     * @return lux
     */    
    public String lux()
    {
        return lux;
    }
    
    /**
     * returns inclinometer off
     * @return iOff
     */
    public String iOff()
    {
        return iOff;
    }
        
    /**
     * returns inclinometer standing
     * return iStand
     */
    public String iStand()
    {
        return iStand;
    }
    
    /**
     * returns inclinometer sitting
     * @return iSit
     */
    public String iSit()
    {
        return iSit;
    }
    
    /**
     * returns inclinometer laying
     * @return iLay
     */
    public String iLay()
    {
        return iLay;
    }
        
    /**
     * returns vector magnitude
     * @return vec
     */
    public double vec()
    {
        return vec;
    }
    
    /**
     * returns the format 
     * 1 = mins
     * 2 = secs
     * @return format
     */
    public int format()
    {
        return format;
    }
    
}