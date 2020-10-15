package budget;

import java.io.*;
import java.util.*;

public class BudgetAppV2 {
    final String pathToFile = "purchases.txt";
    File fileToSaveAndLoadPurchases = new File(pathToFile);
    private Double balance = 0.0;

    private Double foodSum = 0.0;
    private Double clothesSum = 0.0;
    private Double entertainmentSum = 0.0;
    private Double otherSum = 0.0;
    private Double totalSum = 0.0;

    private Scanner in;
    private Boolean programIsRunning = true;

    HashMap<Category, ArrayList<Purchase>> categoryPurchaseMap = new HashMap<>();

    BudgetAppV2(Scanner in) {
        this.in = in;
    }


    public void showMenu() {
        while (programIsRunning) {
            System.out.println(
                    "Choose your action:\n" +
                            "1) Add income\n" +
                            "2) Add purchase\n" +
                            "3) Show list of purchases\n" +
                            "4) Balance\n" +
                            "5) Save\n" +
                            "6) Load\n" +
                            "7) Analyze (Sort)\n" +
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
            } else if (answer.equals("5")) {
                this.saveAllPurchaseToFile();
            } else if(answer.equals("6")) {
                this.loadPurchaseFromFile();
            } else if (answer.equals("7")) {
                this.analyze();
            } else if (answer.equals("0")) {
                System.out.println("Bye!");
                programIsRunning = false;
            } else {
                System.out.println("Enter your answer 0-7");
            }

        }

    }


    private void addIncome() {
        System.out.println("Enter income");
        Double addition = Double.parseDouble(in.nextLine());
        balance += addition;
        System.out.println("Income was added!");
        System.out.println();
    }


    private void addPurchase() {
        System.out.println("Choose the type of purchases\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) Back"
                );
        String answer = in.nextLine();
        System.out.println();



        if (!answer.equals("1") && !answer.equals("2") &&
            !answer.equals("3") && !answer.equals("4"))  {
            System.out.println("Wrong number of answer!");
            System.out.println();
            return;
        }

        Category categoryOfPurchase = Category.returnNewCategory(answer);

        System.out.println("Enter purchase name:");
        String nameOfPurchase = in.nextLine();
        System.out.println("Enter its price:");
        Double priceOfPurchase = Double.parseDouble(in.nextLine());
        String priceInString = String.format("%.2f", priceOfPurchase);
        balance -= priceOfPurchase;
        totalSum += priceOfPurchase;

        if (answer.equals("1")) {
            foodSum += priceOfPurchase;
        } else if (answer.equals("2")) {
            clothesSum += priceOfPurchase;
        } else if (answer.equals("3")) {
            entertainmentSum += priceOfPurchase;
        } else if (answer.equals("4")) {
            otherSum += priceOfPurchase;
        }

        Purchase thisPurchase = new Purchase(categoryOfPurchase, nameOfPurchase, priceInString);

        if (!categoryPurchaseMap.containsKey(categoryOfPurchase)) {
            ArrayList<Purchase> purchases = new ArrayList<>();
            purchases.add(thisPurchase);
            categoryPurchaseMap.put(categoryOfPurchase, purchases);
        } else {
            categoryPurchaseMap.get(categoryOfPurchase).add(thisPurchase);
        }

        System.out.println("Purchase was added!");
        System.out.println();
        this.addPurchase();



    }


    private void showListOfPurchase() {
        System.out.println();
        if (categoryPurchaseMap.isEmpty()) {
            System.out.println("Purchase list is empty!");
            System.out.println();
            return;
        }

        System.out.println("Choose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");


        String answer = in.nextLine();
        if (answer.equals("1")) {
            if(!categoryPurchaseMap.containsKey(Category.FOOD)) {
                System.out.println("Purchase list is empty!");
                this.showListOfPurchase();
            }
            System.out.println("\nFood:");
            for(Purchase purchase: categoryPurchaseMap.get(Category.FOOD)) {
                System.out.println(purchase);
            }
            System.out.println("Total sum: $" + foodSum);
            this.showListOfPurchase();
        } else if (answer.equals("2")) {
            if(!categoryPurchaseMap.containsKey(Category.CLOTHES)) {
                System.out.println("Purchase list is empty!");
                this.showListOfPurchase();
            }
            System.out.println("\nClothes:");
            for(Purchase purchase: categoryPurchaseMap.get(Category.CLOTHES)) {
                System.out.println(purchase);
            }
            System.out.println("Total sum: $" + clothesSum);
            this.showListOfPurchase();
        } else if (answer.equals("3")) {
            if(!categoryPurchaseMap.containsKey(Category.ENTERTAINMENT)) {
                System.out.println("Purchase list is empty!");
                this.showListOfPurchase();
            }
            System.out.println("\nEntertainment:");
            for (Purchase purchase: categoryPurchaseMap.get(Category.ENTERTAINMENT)) {
                System.out.println(purchase);
            }
            System.out.println("Total sum: $" + entertainmentSum);
            this.showListOfPurchase();
        } else if (answer.equals("4")) {
            if(!categoryPurchaseMap.containsKey(Category.OTHER)) {
                System.out.println("Purchase list is empty!");
                this.showListOfPurchase();
            }
            System.out.println("\nOther");
            for(Purchase purchase: categoryPurchaseMap.get(Category.OTHER)) {
                System.out.println(purchase);
            }
            System.out.println("Total sum: $" + otherSum);
            this.showListOfPurchase();
        } else if (answer.equals("5")) {
            System.out.println("\nAll");
            for (ArrayList<Purchase> listOfAllPurchases: categoryPurchaseMap.values()) {
                for(Purchase purchase: listOfAllPurchases) {
                    System.out.println(purchase);
                }
            }
            System.out.println("Total sum: $" + totalSum);
            this.showListOfPurchase();
        } else if (answer.equals("6")) {
            System.out.println();
            return;
        }


    }



    private void showBalance() {
        System.out.println("Balance: $" + balance);
        System.out.println();

    }



    private void saveAllPurchaseToFile() {
        try (FileWriter writer = new FileWriter(fileToSaveAndLoadPurchases)) {
            writer.write(String.valueOf(balance) + "\n");
            writer.write(String.valueOf(totalSum) + "\n");
            writer.write(String.valueOf(foodSum) + "\n");
            writer.write(String.valueOf(clothesSum) + "\n");
            writer.write(String.valueOf(entertainmentSum) + "\n");
            writer.write(String.valueOf(otherSum) + "\n");

            for (Category category: categoryPurchaseMap.keySet()) {
                for (Purchase purchase: categoryPurchaseMap.get(category)) {
                    writer.write(category.getId() + "\n");
                    writer.write(purchase.getName() + "\n");
                    writer.write(String.valueOf(purchase.getPrice()) + "\n" );
                }
            }
            System.out.println("Purchases were saved!");
            System.out.println();

        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }

    }



    private void loadPurchaseFromFile() {
        try(Scanner scanner = new Scanner(fileToSaveAndLoadPurchases)) {
            balance = Double.parseDouble(scanner.nextLine());
            totalSum = Double.parseDouble(scanner.nextLine());
            foodSum = Double.parseDouble(scanner.nextLine());
            clothesSum = Double.parseDouble(scanner.nextLine());
            entertainmentSum = Double.parseDouble(scanner.nextLine());
            otherSum = Double.parseDouble(scanner.nextLine());

            while (scanner.hasNext()) {
                String categoryId = scanner.nextLine();
                Category categoryOfPurchase = Category.returnNewCategory(categoryId);

                String purchaseName = scanner.nextLine();

                String costInString = scanner.nextLine();
                Double purchaseCost = Double.parseDouble(costInString);


                Purchase thisPurchase = new Purchase(categoryOfPurchase, purchaseName, costInString);



                if (!categoryPurchaseMap.containsKey(categoryOfPurchase)) {
                    ArrayList<Purchase> purchases = new ArrayList<>();
                    purchases.add(thisPurchase);
                    categoryPurchaseMap.put(categoryOfPurchase, purchases);
                } else {
                    categoryPurchaseMap.get(categoryOfPurchase).add(thisPurchase);
                }

            }
            System.out.println("Purchases were loaded!");
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileToSaveAndLoadPurchases.getAbsolutePath());
        }
    }

    private void analyze() {
        System.out.println(
                "How do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
        String answer = in.nextLine();

        if (answer.equals("1")) {
            if (categoryPurchaseMap.isEmpty()) {
                System.out.println();
                System.out.println("Purchase list is empty!");
                System.out.println();
                this.analyze();
            } else {
                System.out.println();
                this.sortAllPurachse();
            }
        } else if (answer.equals("2")) {
            System.out.println();
            this.sortByType();
        } else if (answer.equals("3")) {
            System.out.println();
            this.sortByCertainType();
        } else if (answer.equals("4")) {
            System.out.println();
            this.showMenu();
        }

    }

    private void sortAllPurachse() {
        ArrayList<Purchase> allPurachses = new ArrayList<>();
        for (Category mapKey: categoryPurchaseMap.keySet()) {
            allPurachses.addAll(categoryPurchaseMap.get(mapKey));
        }

        allPurachses.sort(new PurchaseDescendingComparator());
        for(Purchase purchase: allPurachses) {
            System.out.println(purchase);
        }

        System.out.println();
        this.analyze();
    }

    private void sortByType() {
        System.out.println("Types");
        ArrayList<TypeToSum> listOfTypes = new ArrayList<>();
        listOfTypes.add(new TypeToSum("Food", foodSum));
        listOfTypes.add(new TypeToSum("Clothes", clothesSum));
        listOfTypes.add(new TypeToSum("Entertainment", entertainmentSum));
        listOfTypes.add(new TypeToSum("Other", otherSum));

        Collections.sort(listOfTypes);
        for(TypeToSum typeToSum: listOfTypes) {
            System.out.println(typeToSum);
        }

        System.out.println("Total sum: $" +totalSum);
        System.out.println();
        this.analyze();
    }

    private void sortByCertainType() {
        System.out.println(
                "Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");

        String answer = in.nextLine();
        if (!answer.equals("1") && !answer.equals("2") &&
            !answer.equals("3") && !answer.equals("4")) {
            System.out.println("Worng number");
            this.analyze();
        }
        System.out.println();

        Category category = Category.returnNewCategory(answer);
        if (!categoryPurchaseMap.containsKey(category)) {
            System.out.println();
            System.out.println("Purchase list is empty!");
            System.out.println();
            this.analyze();
        } else {

            System.out.println(category);
            ArrayList<Purchase> listOfPurchase = new ArrayList<>();
            listOfPurchase.addAll(categoryPurchaseMap.get(category));
            listOfPurchase.sort(new PurchaseDescendingComparator());

            for (Purchase purchase : listOfPurchase) {
                System.out.println(purchase);
            }
            System.out.println();


            this.analyze();
        }

    }




















}
