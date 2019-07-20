package com.xsolve.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONHelper {

    public static Object[][] readJSONFile(int testNumber, String filePath) {
        Object[][] data = null;
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            int len = jsonObject.size();
            data = new Object[len][testNumber];
            for (int i = 0; i < len; i++) {
                JSONObject structure = (JSONObject) jsonObject.get("test" + i);
                for (int j = 0; j < testNumber; j++) {
                    data[i][j] = structure.get(Integer.toString(j));
                }
            }
            //System.out.println(Arrays.toString(data[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
