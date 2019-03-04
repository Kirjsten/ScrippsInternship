/**
 * Sort: Sorts out tremor data by either heartrate, vector magnitude, or date
 * Please note that data already comes pre-sorted in date/time order.
 * 
 * @author Kirjsten Blodgett 
 * @version 1.0
 */
import java.util.*;
public class Sort
{
    private Scanner keyboard = new Scanner(System.in);
    private ArrayList<Tremor> tremors = new ArrayList<Tremor>();
    
    /**
     * Constructor for objects of class Sort
     */
    public Sort(ArrayList tremorList)
    {
        tremors = tremorList;
    }
    
    /**
     * allows user to choose what sort method within sort class
     */
    public void choice()
    {
        int choice;
        
        do
        {   System.out.println("");
            System.out.println("Sort by:\n1.Date/Time \n2.Heartrate \n3.Vector Magnitude (Amount of Movement) \n4.Length \n5.End");
            choice = keyboard.nextInt();
            switch(choice)
            {
                case 1: dateTime(); break; //data comes sorted in date and time order. no need to resort.
                case 2: heartRate(); break;
                case 3: vectorMag(); break;
                case 4: length(); break;
                case 5: break;
                default:
                System.out.println("None of the choices were selected. \nPlease select from the choices above.");
            }
        }while(choice != 5);
    }
    
    /**
     * Sorts date/time, most likey will not be used since the data already comes sorted by date.
     */
    private void dateTime()
    {
        Collections.sort(tremors, Tremor.TremorDateComparator);
        System.out.println("Date/Time Sort");
        System.out.printf("%-15s %-20s %-10s %-20s %-10s %n","*Date*","Time","HR","Vector Magnitude","Length");
        print();
    }
    
    /**
     * sorts out tremors in order of highest heartrate to lowest heartrate.
     */
    private void heartRate()
    {
        Collections.sort(tremors, Tremor.TremorHRComparator);
        System.out.println("Heartrate Sort");
        System.out.printf("%-15s %-20s %-10s %-20s %-10s %n","Date","Time","*HR*","Vector Magnitude","Length");
        print();
    }
    
    
    /**
     * sorts out tremors in order of highest vector magnitude to lowest vector magnitude.
     */
    private void vectorMag()
    {
       Collections.sort(tremors, Tremor.TremorVecComparator);
       System.out.println("Vector Magnitude Sort");
       System.out.printf("%-15s %-20s %-10s %-20s %-10s %n","Date","Time","HR","*Vector Magnitude*","Length");
       print();
    }
    
    /**
     * sorts out trmeors in order of highest length to lowest length
     */
    private void length()
    {
        Collections.sort(tremors, Tremor.TremorLengthComparator);
        System.out.println("Length Sort");
        System.out.printf("%-15s %-20s %-10s %-20s %-10s %n","Date","Time","HR","Vector Magnitude","*Length*");
        print();
    }
    
    /**
     * prints out the sorted array lists
     */
    private void print()
    {
        for(int i=0; tremors.size()>i; i++)
        {
            Tremor trem = tremors.get(i);
             System.out.printf("%-15s %-20s %-10s %-20s %-10s %n",trem.date(),(trem.startTime() + "-" + trem.endTime()),trem.maxHR(),trem.maxVec(),trem.length());
        }
    }
}