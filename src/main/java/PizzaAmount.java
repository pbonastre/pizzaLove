import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

public class PizzaAmount {

    private static final String FILENAME = "C:\\workSpaceIJ\\input\\inputChallenge1.txt";
    private static final int MAX_CASES = 100;
    private static final int MAX_PERSONS = 10000;
    private static final int MAX_SLIDES = 100;
    private static final double PIZZA_SLIDES = 8;
    public static int CASES_AMOUNT = 0;
    public static ArrayList<Double> pizzaSlides = new ArrayList<>();
    public static BufferedReader reader = null;


    public static void main(String[] args) {
        try {
            File file = new File(FILENAME);
            StringBuffer contents = new StringBuffer();

            reader = new BufferedReader(new FileReader(file));
            ObtainAndSaveInput();
            showOutput();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ObtainAndSaveInput() {
        int peopleAmount = 0;
        int pizzaSlides = 0;

        obtainCaseAmount();

        for (int i = 0; i < CASES_AMOUNT; i++) {
            peopleAmount = obtainPeople();
            pizzaSlides = obtainPizzaSlides(peopleAmount);
            saveSlides(pizzaSlides);
        }
    }

    private static void obtainCaseAmount() {
        System.out.println("Insert Input: ");
        try {
            String contentLine = reader.readLine();
            if (contentLine != null) {
                CASES_AMOUNT = parseInt(contentLine);
            } else {
                System.out.println("Please insert a valid Case number.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Please insert a number.");
        } catch (IOException e) {
            System.out.println("Error reading input. ");
        }
    }

    private static int obtainPeople() {
        int people = 0;

        try {
            String sCurrentLine = reader.readLine();
            if (sCurrentLine != null) {
                people = parseInt(sCurrentLine);
            } else {
                System.out.println("Please insert a valid People amount.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
        return people;
    }

    protected static int obtainPizzaSlides(int peopleAmount) {

        int slices = 0;
        StringTokenizer token = null;
        String contentLine = null;
        try {
            contentLine = reader.readLine();
        } catch (IOException e) {
            System.out.println("You need to enter at least one Case with persons and Pizza slides per each person.");
        }
        if (contentLine == null) {
            System.out.println("You must enter a valid Slide pizzas according to the people you've set.");
        } else {
            token = new StringTokenizer(contentLine, " ");
        }

        if (token.countTokens() > peopleAmount) {
            System.out.println("The number of slides must be the same as people introduced");
            System.exit(1);
        }
        while (token.hasMoreElements()) {
            slices = slices + parseInt(token.nextToken());
        }
        return slices;
    }

    private static void saveSlides(double amountPizzaSlides) {
        pizzaSlides.add(amountPizzaSlides);
    }

    private static void showOutput() {
         for(int i = 0; i  < pizzaSlides.size(); i++ ) {
            String pizzas = obtainPizzaAmount(pizzaSlides.get(i));
            System.out.print("Case #" + (i+1) + ": " + pizzas + "\n");
        }
    }

    private static String obtainPizzaAmount(double pizzaSlides) {
        DecimalFormat df = new DecimalFormat("##0");
        return (df.format(Math.ceil(pizzaSlides / PIZZA_SLIDES)));
    }
}
