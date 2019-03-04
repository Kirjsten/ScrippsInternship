

/**
 * TremorCount: counts the number of tremors in a set amount of time (so far only a day)
 * 
 * @author Kirjsten Blodgett
 * @version 1.0
 */
import java.util.*;
import java.text.DecimalFormat;
import javax.swing.*;
public class TremorCount
{
    // instance variables - replace the example below with your own
    private ArrayList<Tremor> tremors = new ArrayList<Tremor>();
    private ArrayList<ArrayList> tremPerDay = new ArrayList<ArrayList>();
    private int f;
    private double maxHR=0, minHR=999, maxVec=0, minVec = 9999999, maxLength=0, minLength=999;
    private ArrayList<ArrayList> tremorPrint = new ArrayList<ArrayList>();
    /**
     * Constructor for objects of class TremorCount
     */
    public TremorCount(ArrayList trems)
    {
        tremors = trems;
    }
    
    /**
     * Constructor for objects of class TremorCount
     */
    public TremorCount()
    {
    }
    
    /**
     * method to split date into ints
     */
    private void dateSplit()
    {
        for(int i = 0; tremors.size()>i; i++)
        {
            Tremor trem = (Tremor)tremors.get(i);
            trem.splitDate();
            tremors.set(i, trem);
        }
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     */
    public void tremPerDay()
    {
        ArrayList<Tremor> temp = new ArrayList<Tremor>();
        dateSplit();
        int count = 0;
        while(count++ <= 31)
        {
            for(int i =0; tremors.size()> i; i++)
            {
                Tremor trem = (Tremor)tremors.get(i);
                if(trem.day()==count)
                {
                    temp.add(trem);
                }
            }
            temp = new ArrayList<Tremor>();
            tremPerDay.add(temp);
        }
        printTremsPerDay();
    }
    
    /**
     * prints out how many tremors per day and averages for HR, vecmag, and length;
     */
    private void printTremsPerDay()
    {
        System.out.println("");
        System.out.println("Tremors Per Day: Averages");
        System.out.printf("%-15s %-15s %-10s %-10s %-10s %-25s %-25s %-25s %-12s %-12s %-12s %n","# of Tremors", "Date", "Ave HR", "Max HR", "Min HR","Ave Vector Magnitude", "Max Vector Magnitude","Min Vector Magnitude", "Ave Length","Max Length","Min Length" );
        double total= 0.00, average=0.00, totalHR=0.00, totalVec=0.00, totalLength=0.00;
        int tremTotalCount=0;
        for(int i = 0; tremPerDay.size()>i; i++)
        {
            ArrayList<Tremor> temp = tremPerDay.get(i);
            
            double tempHR = 0.00, tempVec = 0.00, tempLength =0.00;
            for(int x =0; temp.size()>x; x++)
            {
                Tremor tremTemp = (Tremor)temp.get(x);
                tempHR += tremTemp.maxHR();
                tempVec += tremTemp.maxVec();
                tempLength += tremTemp.length();
                
                if(maxHR < tremTemp.maxHR()){maxHR = tremTemp.maxHR();}
                if(maxVec < tremTemp.maxVec()){maxVec = tremTemp.maxVec();}
                if(maxLength < tremTemp.length()){maxLength = tremTemp.length();}
                if(minLength > tremTemp.length()){minLength = tremTemp.length();}
                if(minHR > tremTemp.maxHR()){minHR = tremTemp.maxHR();}
                if(minVec>tremTemp.maxVec()){minVec=tremTemp.maxVec();}
            }
            
            if(tempHR !=0){tempHR = tempHR/temp.size();} 
            if(tempVec!=0){tempVec = tempVec/temp.size();} 
            if(tempLength!=0){tempLength = tempLength/temp.size();}
            Tremor tempTrem = null;
            if(temp.size()>0)
            {tempTrem = temp.get(0);}
            if(tempTrem != null)
            {   String date = tempTrem.date();
                total += temp.size();
                totalHR += tempHR;
                totalVec += tempVec;
                totalLength += tempLength;
                ArrayList<String> tremDay = new ArrayList<String>();
                DecimalFormat  df = new DecimalFormat("0.00");
                //               ("%-15s %-15s %-10s %-10s %-25s %-25s %-12s %-12s %-12s %n","# of Tremors", "Date", "Ave HR", "Max HR", "Ave Vector Magnitude", "Max Vector Magnitude", "Ave Length","Max Length","Min Length" )
                System.out.printf("%-15s %-15s %-10s %-10s %-10s %-25s %-25s %-25s %-12s %-12s %-12s %n",temp.size(), date, df.format(tempHR), df.format(maxHR),df.format(minHR), df.format(tempVec), df.format(maxVec),df.format(minVec), df.format(tempLength), df.format(maxLength), df.format(minLength));
                maxHR=0; minHR=999; maxVec=0; minVec=99999999; maxLength=0; minLength=999;
                tremTotalCount++;
                String size = Integer.toString(temp.size());
                String printHR = Double.toString(tempHR);
                String printMaxHR = Double.toString(maxHR);
                String printMinHR = Double.toString(minHR);
                String printVec = Double.toString(tempVec);
                String printMaxVec = Double.toString(maxVec);
                String printMinVec = Double.toString(minVec);
                String printLength = Double.toString(tempLength);
                String printMaxLength = Double.toString(maxLength);
                String printMinLength = Double.toString(minLength);
                tremDay.add(size);tremDay.add(date);tremDay.add(printHR);tremDay.add(printMaxHR);tremDay.add(printMinHR);tremDay.add(printVec);tremDay.add(printMaxVec);tremDay.add(printMinVec);tremDay.add(printLength);tremDay.add(printMaxLength);tremDay.add(printMinLength);
                tremorPrint.add(tremDay);
            }   
            tempHR = 0; tempVec= 0; tempLength = 0;
            
        }
        System.out.println("File Averages:");
        if(total!=0){System.out.println("   Tremor: "+ (total/tremTotalCount));}
        if(totalHR!=0){System.out.println("   Heartrate: " + (totalHR/tremTotalCount));}
        if(totalVec!=0){System.out.println("   Vector Magnitude:" + (totalVec/tremTotalCount));}
        if(totalLength!=0){System.out.println("   Length: " + (totalLength/tremTotalCount));}
    }
    

}