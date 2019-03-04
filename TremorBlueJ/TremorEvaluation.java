/**
 * class Tremor: This classs is to help determine what a tremor is. these objects will be reviewed again
 * in the other classes labeled lowintense, medintense, and high intense to determine just how intense a 
 * tremor is.
 * 
 * @author Kirjsten Blodgett
 * @version 1.0
 * email: catsanddogskb@hotmail.com
 */
import java.util.*;
import java.text.DecimalFormat;
public class TremorEvaluation
{
    // instance variables - replace the example below with your own
    Scanner keyboard = new Scanner(System.in);
    private List<DataPerMin>  dataPerMinArray = new ArrayList<DataPerMin>();
    private ArrayList<Tremor> tremList= new ArrayList<Tremor>();
    private List<DataPerMin>  trems = new ArrayList<DataPerMin>();
    private ArrayList<ArrayList> tremmors = new ArrayList<ArrayList>();
    private String timeStart, timeEnd;
    private int HR=0;
    private double vec=0, meanVec = 0.00, meanHR=0.00;
    private int inputStart, inputEnd;
    private double vecInput, vecEnd;
    /**
     * Constructor for objects of class Tremor
     */
    public TremorEvaluation(List tremors)
    {
        dataPerMinArray = tremors;
    }

   
    
    
    /**
     * prints mean of HR
     */
    public void VecHRMean()
    {   double hr=0, count=0, mean=0;
        //previous threshold was 100
        for(int i=0; dataPerMinArray.size() > i ; i++ )
        {
            DataPerMin dataPerMin = (DataPerMin) dataPerMinArray.get(i);
            if(dataPerMin.HR() > inputStart)
            {
                hr += dataPerMin.HR();
                count++;
            }
        }
        if(hr != 0)
        {hr = hr/count;}
        meanHR = hr;
        count =0;

        //previous threshold was 3500
        for(int i=0; dataPerMinArray.size() > i ; i++ )
        {
            DataPerMin dataPerMin = (DataPerMin) dataPerMinArray.get(i);
            if(dataPerMin.vec() > vecInput)
            {
                mean += dataPerMin.vec();
                count++;
            }
        }
        if(mean !=0)
        {mean = mean/count;}
        meanVec=mean;
    }
    
    
    /**
     * calculates when a tremor begins and ends. Puts the data that falls during the tremor is put in a seperate array.
     * then that array is the placed into another array
     */
    public void mins()
    {
        //List<DataPerMin> mins = new ArrayList<DataPerMin>();
        int count = 0;
        int tremCount = 0;
        int maxHR=0;
        double maxVec=0;
        System.out.println(" ");
        System.out.println("Tremor Thresholds:");
        ArrayList<DataPerMin>  duringTrems = new ArrayList<DataPerMin>();
        System.out.println("Threshold for heart rate when tremor is starting?");
        inputStart = keyboard.nextInt();
        System.out.println("Threshold for heart rate when tremor is ending?");
        inputEnd = keyboard.nextInt();
        System.out.println("Threshold for the vector magnitude when tremor is starting?");
        vecInput = keyboard.nextDouble();
        //System.out.println("Treshold for the vector magnitude when tremor is ending?");
        //vecEnd = keyboard.nextDouble(); || dataPerMinArray.get(i).vec()>vecEnd
        for(int i =0; dataPerMinArray.size()>i; i++)
        {
            if(dataPerMinArray.get(i).HR()>inputStart || dataPerMinArray.get(i).vec()>vecInput)
            {
                 duringTrems.add(dataPerMinArray.get(i));
                 count++;
            }
            if(dataPerMinArray.get(i).HR()<inputEnd)
            {
                tremmors.add(duringTrems);
                count = 0;
                duringTrems = new ArrayList<DataPerMin>();
                //duringTrems.clear();
            }
        }
        
        //System.out.println("Total Amount: " + tremmors.size());
        evaluate();
    }
    
        /**
     * evaluates duringTrmor to give length, mean, and highest point during tremor
     */
    public void evaluate()
    {
        //print the max heart rate during tremor and the max vector magnitude during tremor
        System.out.println("");
        System.out.println("Tremors");
        String date = null;
        String startTime=null;
        String endTime=null;
        String intensity= null;
        int maxHR=0, format=0;
        double maxVec=0, count=0;
        int startHour, startMin, startSec, tempHour, tempMin, tempSec, startMath = 9999, tempMath= 0;
        
        System.out.printf("%-15s %-20s %-10s %-20s %-10s %n","Date","Time","HR","Vector Magnitude","Length");
        
         for(int i = 0; tremmors.size()>i; i++)
         {
             ArrayList trem = tremmors.get(i);
             if(trem.size()>0)
             {   DataPerMin temp = (DataPerMin)trem.get(0);
                 startTime = temp.time();
                 date = temp.date();
             }
             for(int x = 0; trem.size()>x; x++)
             {
                 DataPerMin tremTemp = (DataPerMin)trem.get(x);
                 //System.out.println(tremTemp.HR());
                 //System.out.println("setting maxHR, maxVec, format, endTime, and incrementing count");
                 if(tremTemp.HR() > maxHR)
                   {maxHR = tremTemp.HR();}
                 if(tremTemp.vec() > maxVec)
                 {maxVec = tremTemp.vec();}
                 endTime = tremTemp.time();
                 format = tremTemp.format();
                 count++;
             }
             
             if(date != null && endTime != null && startTime != null)
             { 
                 if(startTime != null && tremList.size()>=1)
                 {
                     String[] startTimeArray = startTime.split("[:]+");
                     startHour = Integer.parseInt(startTimeArray[0]);
                     startMin = Integer.parseInt(startTimeArray[1]);
                     startSec = Integer.parseInt(startTimeArray[2]);
                     startMath = (startHour*120)+(startMin*60)+startSec;

                     String[] tempTime = ((Tremor)tremList.get(tremList.size()-1)).endTime().split("[:]+");
                     tempHour = Integer.parseInt(tempTime[0]);
                     tempMin = Integer.parseInt(tempTime[1]);
                     tempSec = Integer.parseInt(tempTime[2]);
                     tempMath = (tempHour*120)+(tempMin*60)+tempSec;
                     //System.out.println("splitting into ints");
                 }
                    
                 if((startMath-tempMath) >= 600) 
                 {  
                     if(format == 2) 
                        {   Tremor tremor = new Tremor(date, startTime, endTime, maxHR, maxVec, (count/60.00), format);
                           if(count>30){tremList.add(tremor);}
                        }

                    if(format == 1)
                    {   Tremor tremor = new Tremor(date, startTime, endTime, maxHR, maxVec, count, format);
                        tremList.add(tremor);
                        
                    }
                        //endTime = null;
                        //startTime = null;
                    
                 } else
                 {
                     Tremor tremPrint = (Tremor)tremList.get(tremList.size()-1);
                        if(tremPrint.format() == 2)
                        {   Tremor tempTremor = new Tremor(tremPrint.date(), startTime, endTime, tremPrint.maxHR(), tremPrint.maxVec(), (count/60.00), tremPrint.format());
                            if(count>30){tremList.set(tremList.size()-1, tempTremor);}
                        }
                    
                        if(tremPrint.format() == 1)
                        {    Tremor tempTremor = new Tremor(tremPrint.date(), startTime, endTime, tremPrint.maxHR(), tremPrint.maxVec(), (count), tremPrint.format());
                            tremList.set(tremList.size()-1, tempTremor);
                        }
                        //endTime = null;
                        //startTime = null;
                    
                 }
                 
             }    
                   maxHR=0;
                   maxVec=0;
                   count=0;
                   endTime = null;
                   startTime = null;
          }
          print();
        }
        
           /**
    * prints tremors
    */
    private void print()
    {
        for(int i = 0; tremList.size()> i; i++)
        {
            Tremor temp = tremList.get(i);
            DecimalFormat  df = new DecimalFormat("0.00");
            System.out.printf("%-15s %-20s %-10s %-20s %-10s %n",temp.date(),temp.startTime(),temp.maxHR(),temp.maxVec(),df.format(temp.length()));
        }
    }
    
    /**
     * prints means
     */
    public void meanPrint()
    {
        System.out.println("Mean:");
        System.out.println("    HR: " + meanHR);
        System.out.println("    Vector Magnitude: " + meanVec);
        
    }
    
    /**
     * returns tremList
     * @return tremList
     */
    public ArrayList tremList()
    {
        return tremList;
    }

}
