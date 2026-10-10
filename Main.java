
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        payment p  = new upi("user 1"); //polymorphism payment abstract class accessed by using p upi() becoz it is another ovveride classs
        p.pay(200);
        p.display();
        payment p1 = new CardPayment("user 2");
        p1.pay(300);
        p1.display();
        
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nPayment Method:");
            System.out.println("1. Card");
            System.out.println("2. UPI");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                System.out.println("Thank you!");
                break;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter customer name: ");
                    String cardName = sc.nextLine();

                    System.out.print("Enter amount: ");
                    double cardAmount = sc.nextDouble();
                    sc.nextLine();

                    CardPayment c = new CardPayment(cardName);
                    c.display();
                    c.processpayment(cardAmount);
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String upiName = sc.nextLine();

                    System.out.print("Enter amount: ");
                    double upiAmount = sc.nextDouble();
                    sc.nextLine();

                    upi u = new upi(upiName);
                    u.display();
                    u.processpayment(upiAmount);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
