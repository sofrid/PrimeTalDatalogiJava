package com.example.datalogiinlamningsuppgift2;

/**
 * Program with an Array that handels user input.
 * Saves prime number in the Array.
 * Adds together for total value.
 * Search for existing numbers.
 * Search and adding new higher prime numbers.
 *
 * @author Sofia Ridderstad
 * @date 2019-02-15
 */

import java.util.Arrays;
import java.util.Scanner;

public class UtokadFunktionalitetTest {
    private static int inputAdd = 0;
    private static int[] nummerArray = new int[1];
    private static int total = 0;
    private static int inputAntHighNum = 0;
    public static void main(String[] args) {
        boolean quit = false;
        Scanner scan = new Scanner(System.in);

        startMeny();
        while (!quit) {
            System.out.print("Välj:\n[0 for Add] [1 for Sort] [2 for Search] " +
                    "[3 for searching Higher Prime numbers] [4 to end program]: ");
            int action = scan.nextInt();
            scan.nextLine();

            switch (action) {
                case 0:
                    //Sectionen for Add.
                    // The user can add a number and if it's a prime number it get added to an Array


                    do {
                        Scanner input = new Scanner(System.in);
                        System.out.print("A: Write the number you want to add: ");
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

                    //Method that check if input is a prime number. Return true or false.
                    checkIfPrime(inputAdd);
                    System.out.println("B: Prime number: " + checkIfPrime(inputAdd));

                    //Method that compare if input already exist in Array. If not the input get added to Array.
                    checkIfPrimeExistElseAdd(inputAdd);

                    //Method that add all numbers in Array to a total value.
                    //If total value is a prime number it get added to the Array.
                    System.out.println("D: Total value of Array: ");
                    addAllNumberInArray(nummerArray);
                    System.out.println(total);
                    System.out.println("Total value after a Prime total number is added.");
                    addTotalPrimeToArray(nummerArray);
                    break;
                case 1:
                    //Section for Sort. The Array shows onsorted, sorts and shows sorted.
                    System.out.println("E: \nUnsorted Array:");
                    System.out.println(Arrays.toString(nummerArray));
                    sortNummerArray(nummerArray);
                    System.out.println("E: \nSorted Array");
                    printArray(nummerArray);
                    break;
                case 2:
                    //Section for Search. User can search for number to se if its in the Array
                    searchForNumber(nummerArray);
                    break;
                case 3:
                    //Section to find more (higher) prime numbers.
                    findAndAddHigherPrime(nummerArray, inputAntHighNum);
                    break;
                case 4:
                    // Ends program.
                    System.out.println("Programmet sängs ner...");
                    quit = true;
                    break;
            }
        }
    }

    /** Program start.
     */
    private static void startMeny() {
        System.out.print("Welcome!");
    }

    /** B
     * Method that check if input number is a prime number.
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
     * Method that test if input already exist in Array.
     * If not adds input to the Array.
     *
     * @param inputAdd;
     * */
    private static void checkIfPrimeExistElseAdd(int inputAdd) {
        if(checkIfPrime(inputAdd)) {
            for (int i = 0; i < nummerArray.length; i++) {
                if (inputAdd == nummerArray[i]) {
                    System.out.println("C: The given input already exists");
                    break;
                }
                if (i == nummerArray.length-1) {
                    nummerArray = addElement(nummerArray);
                    nummerArray[nummerArray.length - 1] = inputAdd;
                    System.out.println("B: Array: " + Arrays.toString(nummerArray));
                    break;
                }
            }
        }
    }

    /** C
     * Method that copies the Array and increments by 1.
     *
     * @param nummerArray;
     * @return nummerArray
     */
    private static int[] addElement(int[] nummerArray) {
        nummerArray = Arrays.copyOf(nummerArray, nummerArray.length + 1);
        return nummerArray;
    }

    /** D
     * Method that adds all numbers to a total value.
     * If the total value is a prime number it get added to the Array.
     *
     * @param nummerArray2;
     */
    private static void addAllNumberInArray(int[] nummerArray2) {
        int numberInArray;
        total = 0;
        for (int i = 0; i < nummerArray2.length; i++) {
            numberInArray = nummerArray2[i];
            total += numberInArray;
        }
        checkIfPrimeExistElseAdd(total); // Total value gets checked and if prime added to array.
    }

    /** D
     * Method that add the total value of the Array to the Array if the total value is a prime number.
     *
     * @param nummerArray;
     */
    private static void addTotalPrimeToArray (int[] nummerArray) {
        for (int i = 0; i < nummerArray.length; i++) {
            if (checkIfPrime(total)) {
                addAllNumberInArray(nummerArray);
                System.out.println(total);
            }
        }
    }

    /** E
     * Sort algorithem for the Array. Selection sort.
     *
     * @param nummerArray;
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
     * Prints the sorted array.
     *
     * @param arr;
     */
    private static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
            System.out.println();
        }
    }

    /** F
     * Method that search for the number user have searched for.
     * If found prints out was found.
     * Else prints out was not found,
     * and the existing number closest to the searched number.
     *
     * @param nummerArray;
     */
    private static void searchForNumber(int[] nummerArray) {
        Scanner scanSearch = new Scanner(System.in);
        System.out.print("Input number you want to search for: ");
        int numberSearch = scanSearch.nextInt();
        if(contains(numberSearch)) {
            System.out.println("F: " + numberSearch + " was found.");
        }
        else {
            System.out.println("F: " + numberSearch + " was not found.");
            System.out.println("Closest number is: " + closest(numberSearch, nummerArray));
        }
    }

    /**F
     * Method that test if the searched value exists in Array
     *
     * @param item;
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

    /**F
     * Method that finds the closest number to the one searched for if number dosen't exist in Array.
     *
     * @param num;
     * @param nummerArray2;
     * @return curr
     */
    private static int closest (int num, int[] nummerArray2) {
        int curr = nummerArray2[0];
        var diff = Math.abs (num - curr);
        for (var val = 0; val < nummerArray2.length; val++) {
            var newdiff = Math.abs (num - nummerArray2[val]);
            if (newdiff < diff) {
                diff = newdiff;
                curr = nummerArray2[val];
            }
        }
        return curr;
    }

    /** G
     * Method that ask how many prime numbers you want to find.
     * Begins on one number higher than the highest prime number in Array
     *
     * @param num;
     * @param arr;
     */
    private static void findAndAddHigherPrime(int[] arr, int num) {
        Scanner higherPrime = new Scanner(System.in);
        int max;

        System.out.print("Input number of prime number you want: ");
        inputAntHighNum = higherPrime.nextInt();

        for (int i =0; i < inputAntHighNum; i++) {
            //Finds the highest value
            Arrays.sort(nummerArray);
            max = nummerArray[nummerArray.length-1];

            // Inner loop for finding new higher prime number
            for (int j= max + 1; j > max && max < Integer.MAX_VALUE ; j++) {
                checkIfPrime(j);
                if (checkIfPrime(j)) {
                    System.out.println(j);
                    max = j;
                    checkIfPrimeExistElseAdd(max);
                    break;
                }
            }
        }
    }
}
