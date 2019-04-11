package com.example.datalogiinlamningsuppgift2;

//Program som behandlar primtal med Array. Val för användaren och input.

import java.util.Scanner;
import java.util.Arrays;

public class DatalogiInlamningsuppgift2 {
    private static int inputAdd = 0;
    private static int[] nummerArray = new int[1];
    private static int inputAntHighNum = 0;
    public static void main(String[] args) {
        boolean quit = false;
        Scanner scan = new Scanner(System.in);

        startMeny();
        while (!quit) {
            System.out.print("Välj:\n[0 för Add] [1 för Sort] [2 för Search] " +
                    "[3 för HigherPrimtal] [4 för att stänga ner programmet]: ");
            int action = scan.nextInt();

            switch (action) {
                case 0:
                    //Sektionen för Add. Där användaren matar in värden som om de är primtal sparas och skrivs ut.

                    do {
                        Scanner input = new Scanner(System.in);
                        System.out.print("A: Skriv det tal du vill lägga till: ");
                        String inputEtt = input.nextLine();

                        try {
                            inputAdd = Integer.parseInt(inputEtt);
                            System.out.println("A: " + inputAdd + " is a valid number.");
                        } catch (Exception e) {
                            System.out.println("A: Something went wrong.");
                        }
                        if (inputEtt.matches("[0-9]+")) {
                            break;
                        }

                    } while (true);

                    //Metod som kontrollerar om input är ett primtal. Retunerar true/false.
                    checkIfPrime(inputAdd);
                    System.out.println("B: Prime number: " + checkIfPrime(inputAdd));

                    //Metod som jämför om input redan finns, om nej lägger till input som är primtal i array.
                    checkIfPrimeExistElseAdd(inputAdd);

                    //Metod som adderar samtliga värden i Arrayen. Om summan är ett primtal adderas det till Arrayen.
                    System.out.println("D: Totalvärde av Arrayen: ");
                    addAllNumberInArray(nummerArray);

                    break;
                case 1:
                    //Sektion för Sort. Där datan sparad i 0 visas, sorteras och visas sorterad
                    System.out.println("E: \nOsorterad Array:");
                    System.out.println(Arrays.toString(nummerArray));
                    sortNummerArray(nummerArray);
                    System.out.println("E: \nSorterad Array");
                    printArray(nummerArray);

                    break;
                case 2:
                    //Sektion för Search. Där användaren kan mata in ett tal för att se om det redan finns sparat.
                    searchForNumber(nummerArray);
                    break;
                case 3:
                    //Sektion för använadaren att hitta fler primtal med högre värde än det högsta i Arrayen.
                    //Användaren får mata in hur många till denne önskar hitta.
                    findAndAddHigherPrime(nummerArray, inputAntHighNum);
                    break;
                case 4:
                    // Avslutar programmet.
                    System.out.println("Programmet sängs ner...");
                    quit = true;
                    break;
            }
        }

    }

    /** Program start.
     *
     */
    private static void startMeny() {
        System.out.print("Välkommen till programmet.");
    }

    /** B
     * Metod som kontrollerar om input är ett primtal.
     *
     * @return boolean
     * */
    private static boolean checkIfPrime(int inputAdd) {

        for(int i = 2; 2 * i < inputAdd; i++) {
            if(inputAdd % i == 0)
                return false;
        }
        return true;
    }

    /** C B
     * Metod som testar om inputnumret redan finns i Arrayen, om inte sparas det till Arrayen.
     * */
    private static void checkIfPrimeExistElseAdd(int inputAdd) {
        if(checkIfPrime(inputAdd)) {
            for (int i = 0; i < nummerArray.length; i++) {
                if (inputAdd == nummerArray[i]) {
                    System.out.println("C: The given input already exists");
                    break;
                }
                if (i == nummerArray.length-1) {
                    nummerArray = addElement(nummerArray); //Adderar en plats till i arrayen som det nya värdet får.
                    nummerArray[nummerArray.length - 1] = inputAdd;
                    System.out.println("B: Array: " + Arrays.toString(nummerArray));
                    break;
                }
            }

        }
    }

    /** C
     * Metod som kopierar Arrayen och lägger till en plats.
     *
     * @param nummerArray
     * @return nummerArray
     */
    private static int[] addElement(int[] nummerArray) {
        nummerArray = Arrays.copyOf(nummerArray, nummerArray.length + 1);
        return nummerArray;
    }

    /** D
     * Metod som tar alla numer i arrayen och lägger ihop dem.
     * Om totalvärdet är ett primtal läggs det till i Arrayen
     *
     * @param nummerArray2
     */
    private static void addAllNumberInArray(int[] nummerArray2) {
        int numberInArray;
        int total = 0;
        for (int i = 0; i < nummerArray2.length; i++) {
            numberInArray = nummerArray2[i];
            total += numberInArray;
        }
        System.out.println(total);
        checkIfPrimeExistElseAdd(total);

        // Skriv så arrayen räknar ihop igen efter totalen är införd i arrayen (om totalen är primtal)
        //addAllNumberInArray(nummerArray);
    }

    /** E
     * Sorteringsalgoritm för att sortera arrayen. Selection sort.
     *
     * @param nummerArray
     */
    private static void sortNummerArray(int[] nummerArray) {

        int n = nummerArray.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (nummerArray[j] < nummerArray[min_idx])
                    min_idx = j;
            }
            int temp = nummerArray[min_idx];
            nummerArray[min_idx] = nummerArray[i];
            nummerArray[i] = temp;
        }
    }

    /** E
     * Prints the array (Sorted Array.).
     *
     * @param arr
     */
    private static void printArray(int arr[]) {
         int n = arr.length;
         for (int i = 0; i < n; ++i) {
             System.out.print(arr[i] + " ");
             System.out.println();
         }
     }

    /** F
     * Metod som söker efter värde. Finns det skrivs det ut annars att det inte hittades.
     *
     * @param nummerArray
     */
    private static void searchForNumber(int[] nummerArray) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Skriv in numret du vill söka efter: ");
        int numberSearch = scan.nextInt();
        if(contains(numberSearch)) {
            System.out.println("F: " + numberSearch + " was found.");
        }
        else {
            System.out.println("F: " + numberSearch + " was not found.");
        }
    }

    /**F
     * Metod som testar om sökvärdet finns i Arrayen
     *
     * @param item
     * @return boolean
     */
    private static boolean contains(int item) {
        for (int n : nummerArray) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    /** G
     * Metod som frågar efter hur många primtal efter största värdet i Arrayen användaren vill ha.
     *
     * @param nummerArray
     * @param inputAntHighNum
     */
    private static void findAndAddHigherPrime(int[] nummerArray, int inputAntHighNum) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Input number of prime number you want: ");
        inputAntHighNum = scan.nextInt();

        //finds the highest value
        int max = nummerArray[0];

        for (int counter = 1; counter < nummerArray.length; counter++) {
            if (nummerArray[counter] > max) {
                max = nummerArray[counter];
            }
        }
        for (int i = 0; i < inputAntHighNum; i++) {
        }
    }
}
