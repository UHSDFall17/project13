package app;

import Utilities.*;

import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Tasks
{
    private String description;
    private String notification; //date due for task
    private String timeStamp; //time created for task
    private boolean isRepeated;
    private boolean isCompleted;
    private Stack<String> subtasks;
    private String note;

    Tasks()    {
        subtasks = new Stack<String>();
        isRepeated = false;
        isCompleted = false;
    }

    public void printTask()
    {
        Stream stream =  new Stream();
        stream.writeToConsole("Task: " + getDescription());
        stream.writeToConsole("Notification time: (Y-M-D-Hr:Min:Sec) " + getNotificationDate());
        stream.writeToConsole("Note: " + getNote());
        printSubtask();
    }

    /*Command Handling*/
    protected boolean taskHandler()
    {
        Commands commands = new Commands();
        commands.addCommand(1, "Edit task description");
        commands.addCommand(2, "Edit note");
        commands.addCommand(3, "Edit notification");
        commands.addCommand(4, "Edit a subtask");
        commands.addCommand(5, "Delete a subtask");
        commands.addCommand(6, "Help");
        commands.addCommand(7, "Back To Dashboard");

        String availableCommands = commands.toString();

        boolean cont = true;
        int command;
        int commandReturn = 0;

        Stream stream = new Stream();
        stream.writeToConsole("(Task \""+ getDescription() +"\") "+ availableCommands);

        do{
            stream.writeToConsole("\n(Task \""+ getDescription() +"\") "); //DISPLAY PAGE NAME
            stream.writeToConsole("Press " + Integer.toString(commands.size()-1) + " to Display Available Commands.\nEnter your command: ");

            try
            {
                command = stream.readIntFromConsole();

                if(command > commands.size() || command == 0)
                    throw new Exception();

                commandReturn = commandCenter(command, availableCommands);
                if(commandReturn == 1)
                    cont = false;
            }
            catch (Exception e)
            {
                stream.writeToConsole("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands.\n");
            }

        }while(cont);

        return false;
    }
    public int commandCenter(int command, String availableCommands)
    {
        Stream stream = new Stream();
        switch(command)
        {
            case 1: editDescription(); break;
            case 2: editNote(); break;
            case 3: editNotification(); break;
            case 4: editSubtask(); break;
            case 5: deleteSubtask(); break;
            case 6: stream.writeToConsole(availableCommands); break;
            case 7: return 1;
        }
        return 0;
    }
    /*Task creation helper*/
    protected void addDescription()
    {
        Stream stream = new Stream();
        do
        {
            stream.writeToConsole("\nEnter the task description: \n");
            description = stream.readLineFromConsole();
            if (description.equals(null) || description.equals(""))
                stream.writeToConsole("Not an appropriate description.\nPlease try again!\n\n\n");
        }while(description == null || description == "");
    }
    protected void addDate()
    {
        Stream stream = new Stream();
        int year, month, day;

        do
        {
            stream.writeToConsole("\nYear number: \n");
            year = stream.readIntFromConsole();
        }while(!isValidYear(year));

        do
        {
            stream.writeToConsole("Month number: \n");
            month = stream.readIntFromConsole();
        }while(!isValidMonth(month));

        do
        {
            stream.writeToConsole("Day number: \n");
            day = stream.readIntFromConsole();
        }while(!isValidDay(year,month,day));

        stream.writeToConsole("Do you wish to enter a time for completion? (Y/N)\n");
        String ans = stream.readLineFromConsole().toUpperCase();
        if(ans.equals("Y"))
        {
            int hour, min;
            do
            {
                stream.writeToConsole("Enter hour (0-23): ");
                hour = stream.readIntFromConsole();
                stream.writeToConsole("Enter minute (0-59): ");
                min = stream.readIntFromConsole();
            }while(!isValidTime(hour,min));

            setDate(year, month-1, day, hour, min);
        }
        else
        {
            setDate(year, month-1, day);
        }
    }
    protected void addNote()
    {
        Stream stream = new Stream();
        do
        {
            stream.writeToConsole("\nEnter the note for the task: \n");
            note = stream.readLineFromConsole();
            if (description.equals(null) || description.equals(""))
                stream.writeToConsole("Not an appropriate note.\nPlease try again!\n\n\n");
        }while(description == null || description == "");
    }
    protected void addSubtask()
    {
        Stream stream = new Stream();
        stream.writeToConsole("\nEnter the subtask description: \n");
        String st = stream.readLineFromConsole();
        if (st == null || st.equals(""))
            stream.writeToConsole("Subtask has no description.\n");
        else
        {
            subtasks.push(st);
        }
    }
    protected void printSubtask()
    {
        Stream stream = new Stream();
        stream.writeToConsole("Subtasks:\n");
        if(subtasks == null)
            return;
        else
        {
            Stack<String> stCopy = new Stack();
            stCopy.addAll(subtasks);
            for(int i = 0; i < subtasks.size(); i++)
            {
                String temp = stCopy.pop();
                stream.writeToConsole("         "+ (i+1) + temp + "\n"); //To whom it may concern: You can use "/t" if you want the String indented. -Stacy
            }
        }
    }
    protected void flipRepeated() //flip false to true or true to false
    {
        if(isRepeated == false)
            isRepeated = true;
        else
            isRepeated = false;
    }

    protected void deleteSubtask()
    {
        Stream stream = new Stream();
        if(subtasks.isEmpty())
            stream.writeToConsole("\nThere are no subtasks!\n\n");
        else
        {
            printSubtask();
            int index;
            do
            {
                stream.writeToConsole("Enter the number of the subtask you wish to delete: ");
                index = stream.readIntFromConsole();
                if(index > 0 && index < subtasks.size())
                {
                    subtasks.remove(index-1);
                }
                else
                    stream.writeToConsole("Invalid index!");
            } while(!(index > 0 && index < subtasks.size()));
        }
    }

    private void setDate(int y, int m, int d, int hr, int min) //date has assigned hh:mm
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:SS");
        Calendar taskCal = Calendar.getInstance();
        taskCal.set(y, m, d, hr, min);
        Date taskDate = taskCal.getTime();
        notification = sdf.format(taskDate);
    }
    private void setDate(int y, int m, int d) //date is a reminder for a day w/o hh:mm
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:SS");
        Calendar taskCal = Calendar.getInstance();
        taskCal.set(y, m, d);
        Date taskDate = taskCal.getTime();
        notification = sdf.format(taskDate);
    }

    protected void setTimestamp() //creation date for sorting purposes
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:SS");
        timeStamp = sdf.format(currentTimeStamp());
    }
    private Timestamp currentTimeStamp()
    {
        Date current = new Date();
        return new Timestamp(current.getTime());
    }

    protected boolean markCompleted()
    {
        if(!isCompleted)
            return isCompleted = true;
        else
        {
            Stream stream =  new Stream();
            stream.writeToConsole("\nTask has already been mark completed.\n");
            stream.writeToConsole("Would you like to mark it as incomplete? (Y/N)\n");
            //Console input
            String temp = (stream.readLineFromConsole().toUpperCase());
            if(temp.equals(""))
            {
                stream.writeToConsole("Please enter Y or N.\n");
                markCompleted();
            }
            else if(temp.equals("Y"))
            {
                return isCompleted = false;
            }
            else if(temp.equals("N"))
            {
                return isCompleted;
            }
        }
        return isCompleted;
    }
    /*Task Editing Helper*/
    private void editDescription()
    {
        Stream stream = new Stream();
        stream.writeToConsole("\nCurrent description: " + description);
        String newDescription;
        do
        {
            stream.writeToConsole("\nEnter the new description: ");
            newDescription = stream.readLineFromConsole();
            if (newDescription == null || newDescription.isEmpty())
                stream.writeToConsole("Invalid argument! Please enter a valid description.\n");
            else
                description = newDescription;
        } while(newDescription == null || newDescription.isEmpty());
    }
    private void editNote()
    {
        Stream stream = new Stream();
        stream.writeToConsole("\nCurrent note: " + note);
        String newNote;
        do
        {
            stream.writeToConsole("\nEnter the new note: ");
            newNote = stream.readLineFromConsole();
            if (newNote == null || newNote.isEmpty())
                stream.writeToConsole("Invalid argument! Please enter a valid note.\n");
            else
                note = newNote;
        } while(newNote == null || newNote.isEmpty());
    }
    private void editNotification()
    {
        Stream stream = new Stream();
        stream.writeToConsole("\nCurrent notification time: " + notification);
        addDate();
    }
    private void editSubtask()
    {
        Stream stream = new Stream();
        printSubtask();
        int index;
        do
        {
            stream.writeToConsole("\nEnter the number of the subtask you wish to edit: ");
            index = stream.readIntFromConsole();
            if(index > 0 && index < subtasks.size())
            {
                String newSubtask = "";
               do
               {
                   stream.writeToConsole("Enter a new subtask to replace: ");
                   newSubtask = stream.readLineFromConsole();
                   if (newSubtask == null || newSubtask.isEmpty())
                       stream.writeToConsole("Please enter a valid argument.");
                   else
                   {
                       subtasks.remove(index);
                       subtasks.push(newSubtask);
                   }
               } while(newSubtask == null || newSubtask.isEmpty());

            }
            else
                stream.writeToConsole("Invalid index!");
        } while(!(index > 0 && index < subtasks.size()));
    }
    /*Getters*/
    public String getDescription()
    {
        return description;
    }
    public Boolean getIsRepeated(){ return isRepeated; }
    public Boolean getIsCompleted(){ return isCompleted; }
    public String getNote(){ return note; }
    public String getNotificationDate()
    {
        return notification;
    }
    public String getTimestamp()
    {
        return timeStamp;
    }
    public Stack<String> getSubtasks() { return subtasks; }

    /*Setters*/
    protected void setDescription(String description){ this.description = description; }
    protected void setNotification(String notification) { this.notification = notification; }
    protected void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }
    protected void setRepeated(Boolean isRepeated){ this.isRepeated = isRepeated; }
    protected void setCompleted(Boolean isCompleted){ this.isCompleted = isCompleted; }
    protected void setSubtasks(Stack<String> subtasks){ this.subtasks = subtasks; }
    protected void setNote(String note){ this.note = note; }

    /*Date Validation*/
    private boolean isValidYear(int y)
    {
        if(y >= 1990)
            return true;
        else
        {
            Stream stream =  new Stream();
            stream.writeToConsole("\nPlease enter a valid year integer.\n\n");
            return false;
        }

    }
    private boolean isValidMonth(int m)
    {
        if (m >= 1 && m <= 12)
            return true;
        else
        {
            Stream stream =  new Stream();
            stream.writeToConsole("\nPlease enter a valid month integer.\n\n");
            return false;
        }
    }
    private boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return (y % 4 == 0);
    }
    private boolean isValidDay(int y, int m, int d)
    {
        int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false; //between 1 and days[month]
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }
    private boolean isValidTime(int h, int m)
    {
        if(!(h >= 0 && h <= 23))
        {
            Stream stream =  new Stream();
            stream.writeToConsole("\nHour is not within range (0-23).\n");
            return false;
        }
        if(!(m >= 0 && m <=59))
        {
            Stream stream =  new Stream();
            stream.writeToConsole("\nMinutes are noth within range (0-59).\n");
            return false;
        }
        return true;
    }
}
