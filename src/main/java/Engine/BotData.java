package Engine;

import com.google.gson.*;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BotData {
    public static final String PATH = "src/test/resources/TestData/";
    public static String getJsonData(String jsonFilename, String field) {

        try {
            FileReader reader = new FileReader(PATH + jsonFilename + ".json");
            JsonElement json = JsonParser.parseReader(reader);
            return json.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getURL(String fileName, String key) throws IOException {
        Properties property = new Properties();
        property.load(new FileInputStream(PATH + fileName + ".properties"));
        return property.getProperty(key);
    }
}
