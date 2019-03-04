/**
 * Read in the CSV file from device, prints out file in nice labeled format, and creates CSV file with only HR, time, and vector magnitude.
 * 
 * @author Kirjsten Blodgett 
 * @version 1.0
 * email: catsanddogskb@hotmail.com
 */
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.Math;
public class ReadIn   
{
    private Scanner reader;
    private Scanner keyboard = new Scanner(System.in);
    private PrintWriter writer = new PrintWriter( new FileWriter("tremorData.csv"));
    private List<DataPerMin>  dataPerMinArray = new ArrayList<DataPerMin>();
    
   /**
     * Constructor for objects of class ReadIn
     */
    public ReadIn() throws IOException
    {
        String input;
            try{
                    System.out.println("Enter file location and name");
                    System.out.println("OR drag file in tremor program folder and enter (NAME).(FILETYPE)");
                    input = keyboard.nextLine();
                    reader = new Scanner(new File(input));
                }catch(IOException e)
                {
                    System.out.println("File was not found, Ending Program");
                    String delay = keyboard.next();
                    System.exit(0);
                }
    }

    /**
     * reads in and makes DataPerMin object
     */
    public void read()
    {
        int format = 0;
        String ans;
        /**
        do
        {   System.out.println("Is the file in Mins or Secs? (1 - Mins, 2 - Secs)");
            format = keyboard.nextInt();
        }while(format != 1 && format != 2);
        */
        System.out.println("Would you like to see the raw data from the file?");
        ans = keyboard.next();
        System.out.println("File Time format? (1 - mins(With Vec Mag)     2 - secs(No Vec Mag)      3 - mins(No Vec Mag)     4 - secs(With Vec Mag))");
        format=keyboard.nextInt();
        System.out.println("Loading Please Wait. Restart program if it takes longer than a minutre.\nIf BlueJ keeps frezing then open using cmd (Run batch file titled run)");
        /**for(int i=0; 13>i;i++)
        {
            if(reader.next().equalsIgnoreCase("date"))
            {
                format = 1;
            }else format = 2;
        }
        */
        if(format == 1)
        {    if(ans.equalsIgnoreCase("yes"))
            {   printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");
            }            
            readInMinutes(ans, format);
        }
        if(format == 2)
        {
             if(ans.equalsIgnoreCase("yes"))
            {   printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");
            }
            readInSeconds(ans, format);
        }
        if(format==3)
        {
             if(ans.equalsIgnoreCase("yes"))
            {   printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");
            }            
            altReadInMinutes(ans);
        }
        if(format==4)
        {
            if(ans.equalsIgnoreCase("yes"))
            {   printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");
            }
            altReadInSeconds(ans);
        }
        if(format == 0)
        {
            System.out.println("File set-up format was not initialized. Terminating");
            System.exit(0);
        }
        
        
    }
    
    /**
     * reads in seconds and sets per minute data (NOTE: data is changed to work like minute data)
     */
    public void altReadInMinutes(String ans)
    {
        for(int i = 0; i < 3; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        int HR, sec = 00, min = 00, hour = 00, month = 00, day =00, year =00;
        double vec;
        String dateStr, timeStr;
        //reverse back before using again (NOTE TO PROGRAMMER!!!!!!!) (time and date str to be reversed)
        reader.next();reader.next();dateStr = reader.next();timeStr = reader.next();reader.nextLine(); //reader.nextLine();
        reader.next();reader.next();timeStr = reader.next();
        String[] dateArray = dateStr.split("[/]+");
        month = Integer.parseInt(dateArray[0]);
        day = Integer.parseInt(dateArray[1]);
        year = Integer.parseInt(dateArray[2]);
        String[] timeArray = timeStr.split("[:]+");
        hour = Integer.parseInt(timeArray[0]);
        min = Integer.parseInt(timeArray[1]);
        sec = Integer.parseInt(timeArray[2]);

        for(int i = 0; i <= 4; i++)
        {
            reader.nextLine();
        }
        while(reader.hasNextLine())
        {
            ++min;
            if(min == 60){++hour; min=00;}
            if(hour == 24){++day; hour=00;}
            if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){if(day==31){++month;day=01;}}
            if(month==2){if(day==28){++month;day=01;}}
            if(month==4||month==6||month==9||month==11){if(day==30){++month;day=01;}}
            if(month==12){++year;month=01;}
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            date = (Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year));
            time = (Integer.toString(hour)+":"+Integer.toString(min)+":"+Integer.toString(sec));
            axis1 = textArray[0];
            axis2 = textArray[1];
            axis3 = textArray[2];
            steps = textArray[3];
            HR = Integer.parseInt(textArray[4]);
            lux = textArray[5];
            iOff = textArray[6];
            iStand = textArray[7];
            iSit= textArray[8];
            iLay = textArray[9];
            
            int y = Integer.parseInt(axis1), x = Integer.parseInt(axis2), z = Integer.parseInt(axis3);
            vec = Math.sqrt((x*x)+(y*y)+(z*z));
            
            if(HR !=0 && vec !=0 && !iOff.equalsIgnoreCase("1"))
            {   writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
                DataPerMin dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, 1);
                dataPerMinArray.add(dataPerMin);
            
                if(ans.equalsIgnoreCase("yes"))
                {print(dataPerMin);}
            }
        }
        reader.close();
        writer.close();
        
    }
    
    /**
     * reads in minutes and sets per minute data
     */
    public void readInMinutes(String ans, int format)
    {
        for(int i = 0; i < 13; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        int HR;
        double vec;
        while(reader.hasNextLine())
        {
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            
            
            date = textArray[0];
            time = textArray[1];
            axis1 = textArray[2];
            axis2 = textArray[3];
            axis3 = textArray[4];
            steps = textArray[5];
            HR = Integer.parseInt(textArray[6]);
            lux = textArray[7];
            iOff = textArray[8];
            iStand = textArray[9];
            if(textArray.length>10)
            {iSit =  textArray[10];}else iSit= "N/A";
            if(textArray.length>11)
            {iLay = textArray[11];}else iLay = "N/A";
            if(textArray.length>12)
            {vec = Double.parseDouble(textArray[12]);} else vec=0;

                writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
                DataPerMin dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, format);
                dataPerMinArray.add(dataPerMin);
            
            if(ans.equalsIgnoreCase("yes"))
            {print(dataPerMin);}
        }
        reader.close();
        writer.close();
        
    }
    
    /**
     * reads in minutes and sets per minute data
     */
    public void altReadInSeconds(String ans)
    {
        for(int i = 0; i < 13; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        int HR;
        double vec;
        while(reader.hasNextLine())
        {
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            
            date = textArray[0];
            time = textArray[1];
            axis1 = textArray[2];
            axis2 = textArray[3];
            axis3 = textArray[4];
            steps = textArray[5];
            HR = Integer.parseInt(textArray[6]);
            lux = textArray[7];
            iOff = textArray[8];
            iStand = textArray[9];
            if(textArray.length>10)
            {iSit =  textArray[10];}else iSit= "N/A";
            if(textArray.length>11)
            {iLay = textArray[11];}else iLay = "N/A";
            
            int y = Integer.parseInt(axis1)*60, x = Integer.parseInt(axis2)*60, z = Integer.parseInt(axis3)*60;
            vec = Math.sqrt((x*x)+(y*y)+(z*z));
            
            DataPerMin dataPerMin = null;
            if(HR !=0 && vec !=0 && !iOff.equalsIgnoreCase("1"))
            {   writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
                dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, 2);
                dataPerMinArray.add(dataPerMin);
            }
            if(ans.equalsIgnoreCase("yes"))
            {print(dataPerMin);}
        }
        reader.close();
        writer.close();
        
    }
    
    /**
     * reads in seconds and sets per minute data (NOTE: data is changed to work like minute data)
     */
    public void readInSeconds(String ans, int format)
    {
        for(int i = 0; i < 3; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        int HR, sec = 00, min = 00, hour = 00, month = 00, day =00, year =00;
        double vec;
        String dateStr, timeStr;
        //reverse back before using again (NOTE TO PROGRAMMER!!!!!!!) (time and date str to be reversed)
        reader.next();reader.next();dateStr = reader.next();timeStr = reader.next();reader.nextLine(); //reader.nextLine();
        reader.next();reader.next();timeStr = reader.next();
        String[] dateArray = dateStr.split("[/]+");
        month = Integer.parseInt(dateArray[0]);
        day = Integer.parseInt(dateArray[1]);
        year = Integer.parseInt(dateArray[2]);
        String[] timeArray = timeStr.split("[:]+");
        hour = Integer.parseInt(timeArray[0]);
        min = Integer.parseInt(timeArray[1]);
        sec = Integer.parseInt(timeArray[2]);

        for(int i = 0; i <= 4; i++)
        {
            reader.nextLine();
        }
        while(reader.hasNextLine())
        {
            ++sec;
            if(sec == 60){++min; sec=00;}
            if(min == 60){++hour; min=00;}
            if(hour == 24){++day; hour=00;}
            if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){if(day==31){++month;day=01;}}
            if(month==2){if(day==28){++month;day=01;}}
            if(month==4||month==6||month==9||month==11){if(day==30){++month;day=01;}}
            if(month==12){++year;month=01;}
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            date = (Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year));
            time = (Integer.toString(hour)+":"+Integer.toString(min)+":"+Integer.toString(sec));
            axis1 = Integer.toString(Integer.parseInt(textArray[0])*60);
            axis2 = Integer.toString(Integer.parseInt(textArray[1])*60);
            axis3 = Integer.toString(Integer.parseInt(textArray[2])*60);
            steps = textArray[3];
            HR = Integer.parseInt(textArray[4]);
            lux = textArray[5];
            iOff = textArray[6];
            iStand = textArray[7];
            iSit= textArray[8];
            iLay = textArray[9];
            
            int y = Integer.parseInt(axis1), x = Integer.parseInt(axis2), z = Integer.parseInt(axis3);
            vec = Math.sqrt((x*x)+(y*y)+(z*z));
            
            if(HR !=0 && vec !=0 && !iOff.equalsIgnoreCase("1"))
            {   writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
                DataPerMin dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, format);
                dataPerMinArray.add(dataPerMin);
                if(ans.equalsIgnoreCase("yes"))
                {print(dataPerMin);}
            }
        }
        reader.close();
        writer.close();
        
    }
    
    /**
     * prints out object created in read()
     * 
     * @param o
     */
    public void print(DataPerMin o)
    {
        
        //if(o!=null)
        System.out.printf("%-10s %-10s %-5s %-5s %-5s %-5s %-5s %-5s %-12s %-12s %-12s %-12s %-8s %n", o.date(), o.time(), o.axis1(), o.axis2(), o.axis3(), o.steps(), o.HR(), o.lux(), o.iOff(), o.iStand(), o.iSit(), o.iLay(), o.vec());
       
    }
    
    /**
     * 
     */
    public void printHeader()
    {
        System.out.printf("%-10s %-10s %-5s %-5s %-5s %-5s %-5s %-5s %-12s %-12s %-12s %-12s %-8s %n","Date", "Time", "Axis1", "Axis2", "Axis3", "Steps", "HR", "Lux", "Inc-Off", "Inc-Stand", "Inc-Sit", "Inclin-Lying", "Vector");
    }
    
    /**
     * Returns the filtered array to the driver (filtered to take out data that are outliers or not applicable)
     */
    public List dataPerMinArray()
    {
        return dataPerMinArray;
    }
}