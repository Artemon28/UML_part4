import JSON.Initialization;
import JSON.WriteReadTicket;
import basics.logic.*;

import java.util.Scanner;

public class main {

    private static final String tribunesFileName = "tribunes.json";
    private static final String ticketsFileName = "tickets.json";

    private static void buyHotelRoom(){

    }

    private static void StartBuingTicket(){
        TribuneList tribuneList;
        TicketList ticketList;
        Ticket ticket;


        tribuneList = new Initialization().parseTribunes(tribunesFileName);
        ticketList = new WriteReadTicket().readTickets(ticketsFileName);
        while (true){
            System.out.println("Choose the tribune");
            ticket = new Ticket();
            tribuneList.showTribuneInfo();
            Scanner in1 = new Scanner(System.in);
            int tribuneNumber = in1.nextInt();
            Tribune tribune = tribuneList.getTribune(tribuneNumber);
            if (tribune == null){
                System.out.println("This tribune don't exist or it's fully filled");
                continue;
            }

            ticket.setTribune(tribuneNumber);

            System.out.println("Now choose sector on the tribune");
            tribune.showAvailableSectors();
            int sectorNumber = in1.nextInt();
            tribune.showSector(sectorNumber);

            System.out.println("And row and a seat please.");
            int rowNumber= in1.nextInt();
            int seatNumber = in1.nextInt();
            ticket.setSeat(sectorNumber, rowNumber, seatNumber);

            System.out.println("Do you want to add hotel room to your ticket? 1 - yes 2 - no");
            int actionNum2 = in1.nextInt();
            if (actionNum2 == 1){
                buyHotelRoom();
            }

            System.out.println("Let's check your order");
            System.out.println("Your tribune is ");
            System.out.println(String.valueOf(ticket.getTribune()));
            System.out.println("Your sector is ");
            System.out.println(String.valueOf(ticket.getSector()));
            System.out.println("Your row is ");
            System.out.println(String.valueOf(ticket.getRow()));
            System.out.println("Your seat is ");
            System.out.println(String.valueOf(ticket.getSeat()));

            System.out.println("is it right?");

            System.out.println("1 - yes, start payment");
            System.out.println("2 - no, i want to change my seat");
            System.out.println("0 - delete the order and stop");

            int actionNum = in1.nextInt();

            if (actionNum == 0){
                System.exit(0);
            } else if (actionNum == 1) {
                System.out.println("enter your email address to which your ticket will be sent");
                String email = in1.next();
                if (Payment.payment(ticket)){
                    new Initialization().ReserveTicket(tribunesFileName, tribune, sectorNumber, rowNumber, seatNumber);
                    tribune.reserveSeat(sectorNumber, rowNumber, seatNumber);

                    int ticketID = ticketList.addTicket(ticket);
                    ticket.setID(ticketID);
                    new WriteReadTicket().writeTicket(ticketsFileName, ticket);

                    System.out.println("Congratulation! Your Number of the ticket is: " + ticketID);
                    Payment.sendTicket(ticket, email);
                    System.exit(0);
                }
            }
        }
    }

    private static void ShowInformationAboutTicket(){
        TicketList ticketList = new WriteReadTicket().readTickets(ticketsFileName);
        System.out.println("Enter the number of your ticket, please");
        Scanner in1 = new Scanner(System.in);
        int ticketID = in1.nextInt();
        ticketList.showInformation(ticketID);
        System.exit(0);
    }

    public static void main(String args[]) {
        System.out.println("Welcome on our system");
        System.out.println("What you want?");
        System.out.println("1 - buy ticket");
        System.out.println("2 - view information about my ticket");
        System.out.println("0 - stop using system");

        Scanner in = new Scanner(System.in);

        while (true){

            int actionNumber = in.nextInt();
            if (actionNumber == 0){
                System.exit(0);
                in.close();
            }
            else if (actionNumber == 1){
                StartBuingTicket();
            }
            else if (actionNumber == 2){
                ShowInformationAboutTicket();
            }
            else{
                System.out.println("I don't know this command, please write correct number");
            }
        }
    }
}
