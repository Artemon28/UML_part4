package basics.logic;

//здесь должна быть прикручена система оплаты
public class Payment {
    public static boolean payment(Ticket ticket){
        System.out.println("оплата прошла успешно :-)");
        return true;
    }

    public static void sendTicket(Ticket ticket, String email){
    }
}
