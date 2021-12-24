package basics.logic;

public class Ticket {

    private int tribuneNumber;
    private int sectorNumber;
    private int rowNumber;
    private int seatNumber;
    private int id;

    public Ticket(long tribuneNumber, long sectorNumber, long rowNumber, long seatNumber) {
        this.tribuneNumber = (int) tribuneNumber;
        this.sectorNumber = (int) sectorNumber;
        this.rowNumber = (int) rowNumber;
        this.seatNumber = (int) seatNumber;
    }

    public Ticket() {

    }

    public void setTribune(int tribuneNumber) {
        this.tribuneNumber = tribuneNumber;
    }

    public void setSeat(int sectorNumber, int rowNumber, int seatNumber) {
        this.sectorNumber = sectorNumber;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public int getID() {
        return id;
    }

    public int getTribune() {
        return tribuneNumber;
    }

    public int getSector() {
        return sectorNumber;
    }

    public int getRow() {
        return rowNumber;
    }

    public int getSeat() {
        return seatNumber;
    }

    public void showInfo(){
        System.out.println("Your tribune is: " + tribuneNumber);
        System.out.println("Your sector is: " + sectorNumber);
        System.out.println("Your row is: " + rowNumber);
        System.out.println("Your seat is: " + seatNumber);
    }
}
