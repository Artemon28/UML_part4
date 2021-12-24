package basics.logic;

import java.util.ArrayList;

public class Sector {

    ArrayList<ArrayList<Long>> seatMatrix;
    private final int id;
    private final int price;

    public Sector(ArrayList<ArrayList<Long>> seatMatrix, int id, int price){
        this.seatMatrix = seatMatrix;
        this.id = id;
        this.price = price;
    }

    public boolean isSectorFreeSeats(){
        for (int i = 0; i < seatMatrix.size(); i++){
            for (int j = 0; j < seatMatrix.get(i).size(); j++){
                if (seatMatrix.get(i).get(j) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void show() {
        for (int i = 0; i <= seatMatrix.size(); i++) {
            for (int j = 0; j <= seatMatrix.get(0).size(); j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("x ");
                    } else
                        System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else
                    System.out.print(seatMatrix.get(i - 1).get(j - 1) + " ");
            }
            System.out.println("");
        }
    }

    public void Reserve(int rowNumber, int seatNumber){
        seatMatrix.get(rowNumber - 1).set(seatNumber - 1, 0L);
    }

    public long getID(){
        return (long) id;
    }

    public long getPrice(){
        return (long) price;
    }

}
