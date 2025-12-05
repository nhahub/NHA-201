package Engine;

import com.google.gson.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BotData {
    public static final String TEST_DATA_PATH = "src/test/resources/TestData/";
    public static String getJsonData(String jsonFilename, String field) {

        try {
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
