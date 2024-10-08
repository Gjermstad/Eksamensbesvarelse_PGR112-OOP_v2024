package history_database;

import java.util.Scanner;

/*
    Klasse for å håndtere menysystemet og brukerinput gjennom terminalen
 */

public class RelicsProgram
{
    //# Fields
    private final DataHandler data;
    private final static Scanner input = new Scanner(System.in);

    //# Constructor
    public RelicsProgram (DataHandler data) {
        this.data = data;
    }

    //# Methods
    public void startProgram() {
        System.out.println("...Program is booting up...");

        data.parseFile();
        data.loadDataFromDatabaseAtStart();
        data.addNewDataToDatabase();
        data.loadDataFromDatabaseAtStart();

        System.out.println(STR."...\{data.getAmountOfPeopleInDatabase()} people loaded from database...");
        System.out.println(STR."...\{data.getAmountOfMuseumsInDatabase()} museums loaded from database...");
        System.out.println(STR."...\{data.getAmountOfItemsInDatabase()} items loaded from database...");

        System.out.println("Program finished loading, starting now...");

        showMenu();
    }

    private void showMenu() {
        printDivider();
        System.out.println("*** HOVEDMENY ***");
        System.out.println("1. Se informasjon om alle gjenstander funnet.");
        System.out.println("2. Se informasjon om alle gjenstander over en viss alder.");
        System.out.println("3. Se informasjon om antall gjenstander registrert.");
        System.out.println("4. Avslutt programmet.");
        System.out.print("Skriv inn ditt menyvalg her: ");

        int userInput = checkInputIfValidNumber(4);

        switch (userInput) {
            case 1 -> {
                printDivider();
                menu1_printInfoAboutAllItems();
            }
            case 2 -> {
                printDivider();
                menu2_printInfoBasedOnAge();
            }
            case 3 -> {
                printDivider();
                menu3_printInfoBasedOnNumbers();
            }
            case 4 -> {
                printDivider();
                System.out.println("Programmet avsluttes... Velkommen tilbake!");
                System.exit(0);
            }
            default -> showMenu();
        }
    }

    private void menu1_printInfoAboutAllItems() {
        System.out.println("Oversikt over alt vi har... Det er mye det...\n");

        printAllCoins();
        printAllJewelry();
        printAllWeapons();

        printArrowUpText();
        System.out.println(STR." Info om \{data.getAmountOfItemsInDatabase()} gjenstander printet ut.");
        showMenu();
    }
    private void menu2_printInfoBasedOnAge() {
        System.out.println("*** GJENSTANDER OVER EN VISS ALDER ***");
        System.out.println("Denne menyen er viser alle gjenstander eldre enn et bestemt årstall.");
        System.out.println("Hvilket årstall velger du?: ");

        int userInput = checkInputIfValidNumber(2024);
        System.out.println("");

        data.printItemsOlderThanX(userInput);

        printArrowUpText();
        System.out.println();
        showMenu();
    }
    private void menu3_printInfoBasedOnNumbers() {
        printNumbersAboutItems();

        printArrowUpText();
        System.out.println();
        showMenu();
    }

    private void printNumbersAboutItems() {
        data.printNumbersAboutItems();
    }

    private void printAllCoins() {
        data.printAllCoins();
    }
    private void printAllJewelry() { data.printAllJewelry(); }
    private void printAllWeapons() { data.printAllWeapons(); }

    // methods that do small odd jobs related to the menu system
    private void printDivider() {
        System.out.println("\n----------\n");
    }
    private void printArrowUpText() {
        System.out.print("---> Se resultatet ditt over ↑");
    }
    /*
       Denne koden er basert på getNumberFromTerminalInput() fra foreleser Marcus Alexander Dahl i prosjektet Terminal
       Link: https://github.com/kristiania/PGR112v24/blob/main/code/solutions/database/terminal/src/Terminal.java
     */
    private int checkInputIfValidNumber(int maxMenuNumber) {
        try {
            int userInput = Integer.parseInt(input.nextLine());
            if (userInput <= maxMenuNumber) {
                return userInput;
            } else {
                System.out.print(STR."Menyen har kun menyvalg mellom 1-\{maxMenuNumber}. Prøv igjen: ");

                return checkInputIfValidNumber(maxMenuNumber);
            }
        } catch (NumberFormatException e) {
            System.out.print(STR."Menyen støtter kun tall mellom 1-\{maxMenuNumber}. Prøv igjen: ");

            return checkInputIfValidNumber(maxMenuNumber);
        }
    }
}
