/**
 * LowIntense: finds the lowest intensity tremor and prints out low tremor array.
 * 
 * @author Kirjsten Blodgett 
 * @version 1.0
 * email: catsanddogskb@hotmail.com
 */
import java.io.*;
import java.util.Scanner;
import java.util.*;
public class LowHigh
{
    //private List<DataPerMin>  lowTremor = new ArrayList<DataPerMin>();
    private List<Tremor>  tremList = new ArrayList<Tremor>();
    private double highVec, lowVec;
    private String highHRdate, highHRtime, highVecDate, highVecTime, lowHRdate, lowHRtime, lowVecDate, lowVecTime;
    private int highHR, lowHR;

    /**
     * Constructor for objects of class LowIntense
     */
    public LowHigh(List tremorList)
    {
        tremList = tremorList;
    }

    /**
     * calculates the lowest HR and vec that occured
     */
    public void lowest()
    {
        double vec = 999999999; int HR = 999;
        Tremor low;
        String HRdate = "0", HRtime = "0", vecDate = "0", vecTime = "0";
        for(int i = 0; tremList.size() > i; i++)
        {
            low = (Tremor) tremList.get(i);
            if(low.maxHR() < HR)
            {HR = low.maxHR();
             HRdate = low.date();
             HRtime = low.startTime();
            }
            
            if(low.maxVec() < vec)
            {vec = low.maxVec();
             vecDate = low.date();
             vecTime = low.startTime();
            }
        }
        
        lowHR=HR; lowHRdate=HRdate; lowHRtime=HRtime; lowVec=vec; lowVecDate=vecDate; lowVecTime=vecTime; 
    }
    
    public void highest()
    {
        double vec=0; int HR=0;
        Tremor high;
        String HRdate = "0", HRtime = "0", vecDate = "0", vecTime = "0";
        for(int i = 0; tremList.size() > i; i++)
        {
            high = (Tremor) tremList.get(i);
            if(high.maxHR() > HR)
            {HR = high.maxHR();
             HRdate = high.date();
             HRtime = high.startTime(); 
            }
            if(high.maxVec() > vec)
            {vec = high.maxVec();             
             vecDate = high.date();
             vecTime = high.startTime();
            }
        }
        highHR=HR; highHRdate=HRdate; highHRtime=HRtime; highVec=vec; highVecDate=vecDate; highVecTime=vecTime;

    }
    
    /**
     * Prints the highest and lowest of HR and Vec. Gives date and time of occurance
     */
    public void print()
    {
        System.out.println(" ");
        System.out.println("Overall Statistics");
        System.out.println("Lowest");
        System.out.println("    HR: " + lowHR);
        System.out.println("        Date:" + lowHRdate);
        System.out.println("        Time:" + lowHRtime);
        System.out.println("    Vector Magnitude: " + lowVec);
        System.out.println("        Date:" + lowVecDate);
        System.out.println("        Time:" + lowVecTime);
        System.out.println("Highest");
        System.out.println("    HR: " + highHR);
        System.out.println("        Date:" + highHRdate);
        System.out.println("        Time:" + highHRtime);        
        System.out.println("    Vector Magnitude: " + highVec);
        System.out.println("        Date:" + highVecDate);
        System.out.println("        Time:" + highVecTime);
    }
    
  
}