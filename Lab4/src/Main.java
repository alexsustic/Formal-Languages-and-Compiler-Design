import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        displayMenu();
        Scanner userCommand = new Scanner(System.in);
        FiniteAutomata fa = new FiniteAutomata();
        fa.initialiseParameters();
        try {
            int ok = 0;
            while (ok == 0) {
                System.out.println("Choose one action: ");
                String command = userCommand.nextLine();
                switch (command) {
                    case "1":
                        fa.printStates();
                        break;
                    case "2":
                        fa.printAlphabet();
                        break;
                    case "3":
                        fa.printInitialState();
                        break;
                    case "4":
                        fa.printTransitions();
                        break;
                    case "5":
                        fa.printFinalStates();
                        break;
                    case "6":
                        checkDFA(userCommand, fa);
                        break;
                    case "7":
                        ok = 1;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void checkDFA(Scanner userCommand, FiniteAutomata fa) {
        System.out.println("Enter sequence: ");
        String sequence = userCommand.nextLine();
        if (fa.checkIfFa(sequence)) {
            System.out.println("It's Fa!\n");
        } else {
            System.out.println("It's not Fa!\n");
        }
    }

    public static void displayMenu() {

        System.out.println("1.Display states");
        System.out.println("2.Display alphabet");
        System.out.println("3.Display initial state");
        System.out.println("4.Display transitions");
        System.out.println("5.Display final states");
        System.out.println("6.Check DFA");
        System.out.println("7.Exit\n");
    }
}
