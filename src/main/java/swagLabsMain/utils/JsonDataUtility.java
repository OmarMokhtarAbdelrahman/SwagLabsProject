package swagLabsMain.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataUtility {
    
    /**
     * Reads JSON data from a file and converts it to a List of HashMaps
     * @param jsonFilePath The path to the JSON file
     * @return List of HashMaps containing the JSON data
     * @throws IOException If there's an error reading the file
     */
    public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // Read the JSON file content
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        // Parse the JSON content into a List of HashMaps
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
    }
} 