/**
 * Driver: Make a program to record and differentiate between tremors and everyday life using data from a movement device. 
 * 
 * @author Kirjsten Blodgett
 * @version 1.0
 * email: catsanddogskb@hotmail.com
 */
import java.io.*;
import java.util.*;
public class Driver
{

    public static void main(String[] args) throws IOException
    {
        List<DataPerMin>  dataPerMinArray = new ArrayList<DataPerMin>();
        ReadIn readIn = new ReadIn();
        
        readIn.read();
        dataPerMinArray = readIn.dataPerMinArray();
        TremorEvaluation tremor = new TremorEvaluation(dataPerMinArray);
        tremor.mins();
        LowHigh lowHigh = new LowHigh(tremor.tremList());
        lowHigh.lowest();
        lowHigh.highest();
        tremor.VecHRMean();
        TremorCount tremCount = new TremorCount(tremor.tremList());
        tremCount.tremPerDay();
        
        lowHigh.print();//change to return
        tremor.meanPrint();//change to return
        //lowhigh and tremor prints get append onto textarea
        Sort sort = new Sort(tremor.tremList());
        sort.choice();
    }
    

    
}