package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder purchaseList = new StringBuilder();
        BudgetAppV2 budgetApp = new BudgetAppV2(in);
        budgetApp.showMenu();

        // write your code here
    }
}
