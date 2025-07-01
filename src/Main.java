import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        //1.Write a program to find all the longest word in a given dictionary.
        String[] my_string_list = {"cat", "dog", "red", "is", "am"};
        int longest = findLongest(my_string_list);
        printLongest(my_string_list, longest);

        //2. Write a program that displays the number of occurrences of an element in the array
        int[] my_ints_list = {1, 1, 1, 3, 3, 5};
        printArray(my_ints_list);
        System.out.print("Enter element to check ocurrences: ");
        int elemnet = scn.nextInt();
        int ocurrence = countOccurrences(elemnet, my_ints_list);
        System.out.printf("%d occurs %d times\n", elemnet, ocurrence);

        //3.Write a program to find the k largest elements in a given array. Elements in the array can be in
        //any order.
        int[] original_arr = {1, 4, 17, 7, 25, 3, 100};
        printArray(original_arr);
        System.out.print("Enter n to find n largest in this array: ");
        int k = scn.nextInt();
        for (int i = 0; i < k && i< original_arr.length; i++) {
            findLargest(original_arr);
        }
        System.out.println();

        //4. Create a method to reverse an array of integers. Implement the method without creating a new
        //array.
        int[] original_array = {5, 4, 3, 2, 1};
        System.out.println("Original: ");
        printArray(original_array);
        System.out.println("Reversed: ");
        reversArray(original_array);

        //5. Write a menu driven Java program with following option:
        System.out.print("Enter size of array: ");
        int size = scn.nextInt();
        int[] user_array = new int[size];
        int index = 0;
        boolean is_run = true;
        while (is_run) {
            System.out.println("""
                chose number:
                1. Accept elements of an array
                2. Display elements of an array
                3. Search the element within array
                4. Sort the array
                5. To Stop
                """);
            int chose = scn.nextInt();
            int user_element;
            switch (chose) {
                case 1:
                    System.out.print("Enter element you want to add: ");
                    user_element = scn.nextInt();
                    addElemnt(user_array, user_element, index);
                    index++;
                    break;
                case 2:
                    printArray(user_array);
                    break;
                case 3:
                    System.out.print("Enter element to find: ");
                    user_element = scn.nextInt();
                    int found_index = searchNumber(user_array, user_element);
                    if (found_index == -1) {
                        System.out.println("Not found");
                    } else {
                        System.out.printf("found %d in index %d", user_element, found_index);
                    }
                    break;
                case 4:
                    sortArray(user_array);
                    printArray(user_array);
                    break;
                case 5:
                    is_run = false;
                    break;
                default:
                    System.out.println("you must chose from 1 - 5");
            }
        }

        //6. Create a method that generates a random number within a given range. Allow the user to
        //specify the range and call the method to display random numbers.
        System.out.print("Enter minimum: ");
        int minimum = scn.nextInt();
        System.out.print("Enter maximum: ");
        int maximum = scn.nextInt();
        System.out.print("Enter the number of random numbers to generate: ");
        int generate = scn.nextInt();
        generateNumbers(minimum, maximum, generate);
        System.out.println();

        //7. Write a program that checks the strength of a password. Create a method that evaluates a
        //password based on criteria like length, inclusion of special characters, and
        //uppercase/lowercase letters.
        //We have three methods: checkLength, checkSpecialCharacters, and
        //checkUpperCaseLowerCase, each of which assigns a score based on specific criteria.
        //-The totalScore is calculated by adding the scores from these methods.
        //-Classify the password as strong (8 or more), moderately strong (5 or more), or weak
        //based on the totalScore. -
        //The criteria for scoring:
        //Length: 0-5 characters (0 points), 6-7 characters (2 points), 8 or more characters
        //(3 points).
        //Special characters: Absence (0 points), Presence (2 points).
        //Uppercase and lowercase letters: Absence of both (0 points), presence of both (3
        //points).
        System.out.print("Enter password: ");
        String password = scn.next();
        int score = 0;
        score = checkLength(password, score);
        score = checkSpecialCharacters(password, score);
        score = checkUpperCaseLowerCase(password, score);
        if (score >= 8) {
            System.out.println("password is strong");
        } else if (score >= 5) {
            System.out.println("password is moderately strong");
        } else {
            System.out.println("password is weak");
        }

        //8. Create a method that generates the Fibonacci sequence up to a specified number of terms.
        //Hint: The Fibonacci sequence is a mathematical sequence of numbers that starts with 0 and 1,
        //and each subsequent number in the sequence is the sum of the two preceding ones.
        System.out.print("Enter the number of Fibonacci terms to generate: ");
        int febo = scn.nextInt();
        febonachi(febo);

    }

    private static void generateNumbers(int minimum, int maximum, int generate) {
        Random rand = new Random();
        for (int i = 0; i < generate; i++) {
            System.out.print(rand.nextInt((maximum-minimum+1))+minimum+" ");
        }

    }

    public static void febonachi(int terms){
        int first = 0;
        int second = 1;
        System.out.printf("Fibonacci sequence with %d terms: ",terms);
        for (int i = 0; i < terms; i++) {
            if(i == 0){
                System.out.print(0+" ");
            } else if (i == 1) {
                System.out.print(1+" ");
            }else {
                System.out.print(first+second+" ");
                int tmp = first;
                first = second;
                second = tmp+second;
            }
        }
    }

    public static int checkLength(String password, int score){
        if (password.length()>=8){
            return score+=3;
        }else if (password.length()>=6){
            return score+=2;
        }else{
            return score;
        }
    }

    public static int checkSpecialCharacters(String password, int score){
        if (password.matches(".*[^a-zA-Z0-9].*")){
            score+=2;
        }
        return score;
    }
    
    public static int checkUpperCaseLowerCase(String password, int score){
        int upper_case = 0;
        int lower_case = 0;
        for (int i = 0; i < password.length(); i++) {
            if(password.charAt(i) == password.toLowerCase().charAt(i)){
                lower_case++;
            } else if (password.charAt(i) == password.toUpperCase().charAt(i)) {
                upper_case++;
            }
            if (upper_case > 0 && lower_case > 0){
                return score+=3;
            }
        }
        return score;
    }


    public static void printArray(int[] arr){
        for (int a: arr){
            System.out.print(a+" ");
        }
        System.out.println();
    }

    public static void printLongest(String[] arr, int longest){
        for (String n : arr){
            if (n.length() == longest){
                System.out.print(n+", ");
            }
        }
        System.out.println();
    }

    public static int countOccurrences(int n, int[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (n == arr[i]){
                count++;
            }
        }
        return count;
    }



    public static void reversArray(int[] arr){
        for (int i = arr.length-1; i >= 0; i--) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }


    public static int[] addElemnt(int[] arr, int element, int index){
        if (index >= arr.length){
            System.out.println("You reached max element in array");
        }else {
            arr[index] = element;
        }
        return arr;
    }

    public static int searchNumber(int[] arr, int element){
        for (int i = 0; i < arr.length ; i++){
            if (arr[i] == element){
                return i;
            }
        }
        return -1;
    }

    public static void findLargest(int[] arr){
        int max = arr[0];
        for (int n : arr) {
            if (n >= max) {
                max = n;
            }
        }
        int index = searchNumber(arr, max);
        arr[index] = 0;
        System.out.print(max +" ");

    }

    public static void sortArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] >= arr[j]){
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }

    public static int findLongest(String[] arr){
        int longest = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() >= longest) {
                longest = arr[i].length();
            }
        }
        return longest;
    }

}