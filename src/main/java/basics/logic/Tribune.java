package basics.logic;


import java.util.Map;

public class Tribune {

    private Map<Integer, Sector> sectorList;
    private final int id;
    private final String name;
    private final String description;

    public Tribune(Map<Integer, Sector> sectorList, String name, int id, String description){
        this.sectorList = sectorList;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public boolean isFreeSeats(){
        for (Map.Entry mapSector : sectorList.entrySet()) {
            Sector sector = (Sector) mapSector.getValue();
            if (sector.isSectorFreeSeats()){
                return true;
            }
        }
        return false;
    }

    public String getDescription(){
        return description;
    }

    public void showSector(int sectorNumber){
        sectorList.get(sectorNumber).show();
    }

    public void reserveSeat(int sectorNumber, int rowNumber, int seatNumber){
        sectorList.get(sectorNumber).Reserve(rowNumber, seatNumber);
    }

    public void showAvailableSectors(){
        for (Map.Entry mapSector : sectorList.entrySet()) {
            Sector sector = (Sector) mapSector.getValue();
            if (sector.isSectorFreeSeats()){
                System.out.println(mapSector.getKey() + " sector cost: " + sector.getPrice());
            }
        }
        System.out.println("");
    }

    public int getNumber() {
        return id;
    }
}
