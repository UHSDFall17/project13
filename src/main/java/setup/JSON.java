package setup;

import app.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.node.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
    ObjectMapper mapper;

    public JSON() {}

//    public void practiceJNode(){
//        JsonNodeFactory nodeFactory = new JsonNodeFactory();
//        ObjectNode node = nodeFactory.objectNode();
//    }


    public void createDefaultJson(String userEmail){
        mapper = new ObjectMapper();

        Dashboard dashboard = new Dashboard();


        //Object o = dashboard.getLists().toArray();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Accounts/" + userEmail + "/data.json"), dashboard);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public void addTasktoJson(String userEmail, ArrayList<Tasks> taskList){
//        mapper = new ObjectMapper();
//
////        Dashboard dashboard = new Dashboard(userEmail);
////        //String listName = list.getListName();
////        List list = new List("Personal");
//
//        /*Tasks tasks = new Tasks();
//        //list.createTask(tasks, "Call mom");
//        //tasks.getListName();
//        tasks.getDescription();
//        //tasks.getSubTasks();
//        tasks.getNotification();
//        tasks.getTimestamp();
//        //tasks.getPrioritized();
//        //tasks.getNotes();*/
//
//        try {
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Accounts/" + userEmail + "/data.json"), taskList);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
