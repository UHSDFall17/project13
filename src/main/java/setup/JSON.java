package setup;

import app.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSON {
    ObjectMapper mapper;

    public JSON() {}

    public void createDefaultJson(String userEmail){
        mapper = new ObjectMapper();

        Dashboard dashboard = new Dashboard(userEmail);

        /*SET DEFAULT LISTS*/
        dashboard.storeNewList("Personal");
        dashboard.storeNewList("Work");
        dashboard.storeNewList("Grocery List");

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

}
