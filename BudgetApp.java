package budget;

import java.util.Scanner;

public class BudgetApp {
    private double balance = 0;
    private double totalSum = 0;
    private Scanner in;
    private StringBuilder purchaseList;
    private boolean programIsRunning = true;

    public BudgetApp(Scanner in, StringBuilder strB) {
        this.in = in;
        this.purchaseList = strB;
    }

    public void menu() {
        while (programIsRunning) {
            System.out.println("1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "0) Exit");

            String answer = in.nextLine();
            System.out.println();

            if (answer.equals("1")) {
                this.addIncome();
            } else if (answer.equals("2")) {
                this.addPurchase();
            } else if (answer.equals("3")) {
                this.showListOfPurchase();
            } else if (answer.equals("4")) {
                this.showBalance();
            } else if (answer.equals("0")) {
                System.out.println("Bye!");
                programIsRunning = false;
            } else {
                System.out.println("Enter your answer 0-4");
            }
        }
    }


    private void addIncome() {
        System.out.println("Enter income");
        double addition = Double.parseDouble(in.nextLine());
        balance += addition;
        System.out.println("Income was added!");
        System.out.println();
    }

    private void showBalance() {
        System.out.println("Balance: $" + balance);
        System.out.println();
    }

    private void addPurchase() {
        System.out.println("Enter purchase name:");
        String purchase = in.nextLine();
        System.out.println("Enter its price:");
        String cost = in.nextLine();
        purchaseList.append(purchase + " $" + cost + "\n");
        totalSum += Double.parseDouble(cost);
        balance -= Double.parseDouble(cost);
        System.out.println("Purchase was added!");
        System.out.println();
    }

    private void showListOfPurchase() {
        if (purchaseList.length() == 0) {
            System.out.println("Purchase list is empty");
            System.out.println();
        }
        else {
            System.out.print(purchaseList);
            System.out.println("Total sum: $" + totalSum);
            System.out.println();
        }
    }

}
