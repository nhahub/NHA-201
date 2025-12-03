package Engine;

import com.google.gson.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BotData {
    public static final String TEST_DATA_PATH = "src/test/resources/TestData/LoginData";

    // Generic method to read any array from JSON
    public static <Type> List<Type> getArrayFromJson(String jsonFilename, String arrayName, Class<Type> DataType) {
        try (FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json")) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray(arrayName);

            List<Type> list = new ArrayList<>();
            Gson gson = new Gson();
            for (JsonElement element : jsonArray) {
                list.add(gson.fromJson(element, DataType));
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
