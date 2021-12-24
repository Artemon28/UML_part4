package JSON;

import basics.logic.Tribune;
import basics.logic.TribuneList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Initialization {
    public TribuneList parseTribunes(String fileName){
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(fileName)) {

            JSONObject tribunes = (JSONObject) parser.parse(reader);
            JSONArray tribuneArray = (JSONArray) tribunes.get("tribunes");
            Map<Integer, Tribune> tribuneMap = new HashMap<>();
            for (Object itTribune: tribuneArray){
                JSONObject tribune = (JSONObject) itTribune;
                long tribuneID = (long) tribune.get("id");
                String tribuneFileName = (String) tribune.get("fileName");
                tribuneMap.put((int) tribuneID, new InitializationTribune().parse(tribuneFileName));
            }
            return new TribuneList(tribuneMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ReserveTicket(String fileNam, Tribune tribune, int sector, int row, int seat){
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(fileNam)) {
            JSONObject tribunes = (JSONObject) parser.parse(reader);
            JSONArray tribuneArray = (JSONArray) tribunes.get("tribunes");
            for (Object itTribune: tribuneArray){
                JSONObject tribuneJSON= (JSONObject) itTribune;
                long tribuneID = (long) tribuneJSON.get("id");
                String tribuneFileName = (String) tribuneJSON.get("fileName");
                if (tribuneID == tribune.getNumber()) {
                    new InitializationTribune().ReserveSeat(tribuneFileName, sector, row, seat);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
