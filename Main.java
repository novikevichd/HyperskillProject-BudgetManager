package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner will be read all your data from console
        Scanner in = new Scanner(System.in);

        // Creating budget manager and calling method for showing its menu.
        BudgetApp budgetApp = new BudgetApp(in);
        budgetApp.showMenu();

    }
}
