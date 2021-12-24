package JSON;

import basics.logic.Ticket;
import basics.logic.TicketList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WriteReadTicket {

    public TicketList readTickets(String fileName) {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(fileName)) {

            JSONObject tickets = (JSONObject) parser.parse(reader);
            JSONArray ticketsArray = (JSONArray) tickets.get("tickets");
            Map<Integer, Ticket> ticketMap = new HashMap<>();
            for (Object ticket: ticketsArray){
                JSONObject ticketJSON = (JSONObject) ticket;
                long ticketID = (long) ticketJSON.get("id");
                long tribuneNumber = (long) ticketJSON.get("tribune");
                long sectorNumber = (long) ticketJSON.get("sector");
                long rowNumber = (long) ticketJSON.get("row");
                long seatNumber = (long) ticketJSON.get("seat");

                ticketMap.put((int) ticketID, new Ticket(tribuneNumber, sectorNumber, rowNumber, seatNumber));
            }
            return new TicketList(ticketMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeTicket(String fileName, Ticket ticket) {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(fileName)) {
            JSONObject tr = (JSONObject) parser.parse(reader);
            JSONArray tickets = (JSONArray) tr.get("tickets");

            JSONObject newTicket = new JSONObject();
            newTicket.put("id", ticket.getID());
            newTicket.put("tribune", ticket.getTribune());
            newTicket.put("sector", ticket.getSector());
            newTicket.put("row", ticket.getRow());
            newTicket.put("seat", ticket.getSeat());

            tickets.add(newTicket);

            try(FileWriter writer = new FileWriter(fileName)) {
                writer.write(tr.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
