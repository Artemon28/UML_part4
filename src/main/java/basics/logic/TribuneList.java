package basics.logic;

import java.util.Map;

public class TribuneList {

    private final Map<Integer, Tribune> tribuneList;

    public TribuneList(Map<Integer, Tribune> tribuneList){
        this.tribuneList = tribuneList;
    }

    public void showTribuneInfo() {
        for (Map.Entry mapTribune : tribuneList.entrySet()) {
            Tribune tribune = (Tribune) mapTribune.getValue();
            if (tribune.isFreeSeats()){
                System.out.println(mapTribune.getKey());
                System.out.println(tribune.getDescription());
            }

        }

    }

    public Tribune getTribune(int tribuneNumber) {
        if (tribuneList.get(tribuneNumber).isFreeSeats()){
            return tribuneList.get(tribuneNumber);
        }
        return null;
    }
}
