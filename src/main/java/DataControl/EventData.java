package DataControl;

import Dataset.DatasetIndex;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventData {
    private int seat1, seat2, seat3, seat1Available, seat2Available, seat3Available;
    private String eventID, eventName, startDateTime, endDateTime, location;
    double price1, price2, price3;
    private Stack<String[]> Data = new Stack<>();

    public EventData()
    {
        readAll();
    }

    public EventData(String eventID, String eventName, String startDateTime,
                     String endDateTime, String location,
                     int seat1, int seat2, int seat3,
                     int seat1Available, int seat2Available, int seat3Available,
                     double price1, double price2, double price3)
    {
        this.eventID = eventID;
        this.eventName = eventName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.seat1 = seat1;
        this.seat2 = seat2;
        this.seat3 = seat3;
        this.seat1Available = seat1Available;
        this.seat2Available = seat2Available;
        this.seat3Available = seat3Available;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
    }

    public List<Object> getAllValues()
    {
        List<Object> data = new ArrayList<>();
        data.add(eventID);
        data.add(eventName);
        data.add(startDateTime);
        data.add(endDateTime);
        data.add(location);
        data.add(seat1);
        data.add(seat2);
        data.add(seat3);
        data.add(seat1Available);
        data.add(seat2Available);
        data.add(seat3Available);
        data.add(price1);
        data.add(price2);
        data.add(price3);

        return data;
    }

    public void readAll()
    {
        String fetchLine[], line;
        try(BufferedReader in = new BufferedReader(new FileReader(DatasetIndex.eventFile));) {
            while((line = in.readLine()) !=null) {
                fetchLine = line.split("%");
                Data.push(fetchLine);
            }
        }
        catch (FileNotFoundException ex) {
//            File newFile = new File(DatasetIndex.eventFile);
//            try {
//                newFile.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "Event Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stack<String[]> getEventData()
    {
        return Data;
    }

    public void writeAllEvent()
    {
        File data = new File(DatasetIndex.eventFile);
        if(data.exists())
        {
            List<Object> wrt = getAllValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(DatasetIndex.eventFile, true));)
            {
                out.println();
                for(Object str : wrt)
                {
                    out.write(str + "%");
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(new JFrame(), "Event Created!", "Event", JOptionPane.INFORMATION_MESSAGE);

        } else{
            List<Object> wrt = getAllValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(DatasetIndex.eventFile));)
            {
                for(Object str : wrt)
                {
                    out.write(str + "%");
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(new JFrame(), "Event created!", "Event", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void updateEvent()
    {
        List<Object> wrt = getAllValues();
        try(PrintWriter out = new PrintWriter(new FileWriter(DatasetIndex.eventFile, true));)
        {
            readAll();
            try(PrintWriter clean = new PrintWriter(new FileWriter(DatasetIndex.eventFile,false));)
            {
                clean.flush();
                clean.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < Data.size(); i++)
            {
                if(Data.get(i)[0].equals(wrt.get(0)) && Data.get(i)[1].equals("null") ||
                        Data.get(i)[1].equals(wrt.get(1)) && Data.get(i)[0].equals("null"))
                {
                    for(int j = 0; j < Data.get(i).length; j++)
                    {
                        Data.get(i)[j] = wrt.get(j).toString();
                    }
                }

                for(int k = 0; k < Data.get(i).length; k++)
                {
                    out.write(Data.get(i)[k] + "%");
                }

                if(i < Data.size()-1)
                {
                    out.println();
                }
            }
            JOptionPane.showMessageDialog(new JFrame(), "Event have been updated", "Event", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateEvent(Stack<String[]> newData) {
        try(PrintWriter out = new PrintWriter(new FileWriter(DatasetIndex.eventFile,true));) {
            try (PrintWriter clean = new PrintWriter(new FileWriter(DatasetIndex.eventFile, false));) {
                clean.flush();
                clean.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            for(int i=0; i<newData.size(); i++) {
                for(int j = 0; j < newData.get(i).length; j++)
                {
                    out.write(newData.get(i)[j] + "%");
                }

                if(i<newData.size()-1){
                    out.println();
                }
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "Event Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
