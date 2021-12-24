package basics.logic;

import java.util.Map;

public class TicketList {

    private final Map<Integer, Ticket> ticketList;

    public TicketList(Map<Integer, Ticket> ticketList){
        this.ticketList = ticketList;
    }

    public int addTicket(Ticket ticket) {
        int id = generateNewID();
        ticket.setID(id);
        ticketList.put(id, ticket);
        return id;
    }

    public int generateNewID() {
        for (int i = 0; i <= ticketList.size(); i++){
            if (ticketList.get(i) == null){
                return i;
            }
        }
        return 0;
    }

    public void showInformation(int ticketID){
        ticketList.get(ticketID).showInfo();
    }
}
