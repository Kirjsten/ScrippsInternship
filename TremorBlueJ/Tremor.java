/**
 * Tremor: holds data about a single tremor
 * 
 * @author Kirjsten Blodgett
 * @version 1.0
 * email: catsanddogskb@hotmail.com
 */
import java.util.Comparator;
public class Tremor
{
    // instance variables - replace the example below with your own
    private int maxHR, month, day, year, format;
    private String date, startTime, endTime, intensity;
    private double maxVec, length;

    /**
     * Constructor for objects of class Tremor
     */
    public Tremor(String passingdate, String timeStart, String timeEnd, int hr, double vec, double count, int formatminsec)
    {
        date = passingdate;
        startTime = timeStart;
        endTime = timeEnd;
        maxHR = hr;
        maxVec = vec;
        length = count;
        format = formatminsec;
    }
    /**
     * Defailt constructor for tremor
     */
    public Tremor()
    {
    }
    
    /**
     * returns the date of tremor
     * @return date 
     */
    public String date()
    {
        return date;
    }

    
    /**
     * returns the start time of tremor
     * @return startTime 
     */
    public String startTime()
    {
        return startTime;
    }
    
    /**
     * returns the end time of tremor
     * @return endTime 
     */
    public String endTime()
    {
        return endTime;
    }

    /**
     * returns the max heart rate of tremor
     * @return maxHR 
     */
    public int maxHR()
    {
        return maxHR;
    }
    
    /**
     * returns the max vector magnitude of tremor
     * @return maxVec 
     */
    public double maxVec()
    {
        return maxVec;
    }
    
    /**
     * returns the length of tremor
     * @return length 
     */
    public double length()
    {
        return length;
    }
    
    /**
     * returns intensity
     * @return intensity
     */
    public String intensity()
    {
        return intensity;
    }
    
    /**
     * sets the intensity
     * @param intense
     */
    public void setIntensity(String intense)
    {
        intensity = intense;
    }
    
    /**
     * splits date up into int 
     */
    public void splitDate()
    {
        String[] textArray = date.split("[/]+");
        month = Integer.parseInt(textArray[0]);
        day = Integer.parseInt(textArray[1]);
        year = Integer.parseInt(textArray[2]);
    }
    
    /**
     * returns month
     * @return month
     */
    public int month()
    {
        return month;
    }
    
    /**
     * returns day
     * @return day
     */
    public int day()
    {
        return day;
    }
    
    /**
     * returns year
     * @return year
     */
    public int year()
    {
        return year;
    }
    
    /**
     * returns format of the file
     *  1 = mins
     *  2 = secs
     *  @return format
     */
    public int format()
    {
        return format;
    }
    
    public static Comparator<Tremor> TremorHRComparator = new Comparator<Tremor>() 
    {
        public int compare(Tremor t1, Tremor t2) {
            int Tremor1 = t1.maxHR();
            int Tremor2 = t2.maxHR();
            return Tremor2-Tremor1;
        }
    };
    
    public static Comparator<Tremor> TremorVecComparator = new Comparator<Tremor>() 
    {
        public int compare(Tremor t1, Tremor t2) {
            int Tremor1 = (int)t1.maxVec();
            int Tremor2 = (int)t2.maxVec();
            return Tremor2-Tremor1;
        }
    };
    
    public static Comparator<Tremor> TremorLengthComparator = new Comparator<Tremor>() 
    {
        public int compare(Tremor t1, Tremor t2) {
            int Tremor1 = (int)t1.length();
            int Tremor2 = (int)t2.length();
            return Tremor2-Tremor1;
        }
    };
    
    public static Comparator<Tremor> TremorDateComparator = new Comparator<Tremor>() 
    {
        public int compare(Tremor t1, Tremor t2) 
        {
            String Tremor1 = t1.date().toUpperCase();
            String Tremor2 = t2.date().toUpperCase();

            //ascending order
            return Tremor1.compareTo(Tremor2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };
}