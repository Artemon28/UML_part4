package JSON;

import basics.logic.Sector;
import basics.logic.Tribune;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class InitializationTribune {

    public Tribune parse(String fileName){
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader(fileName)) {
            JSONObject tr = (JSONObject) parser.parse(reader);
            String name = (String) tr.get("name");
            long tribuneId = (long) tr.get("TribuneId");
            String description = (String) tr.get("description");
            JSONArray sectors = (JSONArray) tr.get("sectors");
            Map<Integer, Sector> sectorList = new HashMap<>();
            for (Object itSector: sectors){
                JSONObject sector = (JSONObject) itSector;
                long sectorID = (long) sector.get("sectorId");
                long price = (long) sector.get("price");
                JSONArray rows = (JSONArray) sector.get("rows");
                ArrayList<ArrayList<Long>> seatMatrix = new ArrayList<>();
                for (Object itRow: rows){
                    JSONObject row = (JSONObject) itRow;
                    long rowID = (long) row.get("rowNumber");
                    JSONArray seats = (JSONArray) row.get("seats");
                    ArrayList<Long> rowList = new ArrayList<>();
                    for (Object itSeat: seats){
                        rowList.add((Long) itSeat);
                    }
                    seatMatrix.add(rowList);
                }
                Sector sectorUnit = new Sector(seatMatrix, (int) sectorID, (int) price);
                sectorList.put((int) sectorID, sectorUnit);
            }
            return new Tribune(sectorList, name, (int) tribuneId, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public void ReserveSeat(String tribuneFileName, int sector, int row, int seat){
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(tribuneFileName)) {
            JSONObject tr = (JSONObject) parser.parse(reader);
            JSONArray sectors = (JSONArray) tr.get("sectors");
            for (Object itSector: sectors){
                JSONObject sectorJSON = (JSONObject) itSector;
                long sectorID = (long) sectorJSON.get("sectorId");
                if (sectorID == sector){
                    JSONArray rows = (JSONArray) sectorJSON.get("rows");
                    for (Object itRow: rows){
                        JSONObject rowJSON = (JSONObject) itRow;
                        long rowID = (long) rowJSON.get("rowNumber");
                        if (rowID == row){
                            JSONArray seats = (JSONArray) rowJSON.get("seats");
                            seats.set(seat - 1, (long) 0);
                        }
                    }
                }
            }
            try(FileWriter writer = new FileWriter(tribuneFileName)) {
                writer.write(tr.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
