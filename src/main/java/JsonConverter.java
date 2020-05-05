import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.*;
import java.util.List;

public class JsonConverter {
    ObjectMapper objectMapper = new ObjectMapper();

    <T> String convertObjectToString(T obj) {
        Writer res = new StringWriter();
        try {
            objectMapper.writeValue(res, obj);
            return res.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    <T> T convertStringToObject(String str, Class<T> tClass) {
        StringReader reader = new StringReader(str);
        try {
            return objectMapper.readValue(reader, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    <T> void convertObjectToJsonFile(T obj, String filename) {
        try {
            objectMapper.writeValue(new File(filename), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    <T> List<T> convertJSONFileToListOfOjects(String fileName, Class<T> tClass) {
        try {
            CollectionType typeInsideFile = objectMapper.getTypeFactory().constructCollectionType(List.class, tClass);
            return objectMapper.readValue(new File(fileName), typeInsideFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    <T> T convertJSONFileToOject(String fileName, Class<T> tClass) {
        try {
            return objectMapper.readValue(new File(fileName), tClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
