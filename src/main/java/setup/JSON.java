package setup;

import app.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSON {
    private ObjectMapper mapper;

    public JSON() {}


    public void createDefaultJson(String userEmail){
        mapper = new ObjectMapper();

        Dashboard dashboard = new Dashboard();

        /* SET DEFAULT LISTS */
        dashboard.storeNewList("Personal");
        dashboard.storeNewList("Work");
        dashboard.storeNewList("Grocery List");

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Accounts/" + userEmail + "/data.json"), dashboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
