package setup;

import app.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
    ObjectMapper mapper;

    public JSON() {}

    public void createDefaultJson(String userEmail){
        mapper = new ObjectMapper();

        Dashboard dashboard = new Dashboard();

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
