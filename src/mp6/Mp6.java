/*
Andrew Tong
Mr. Ritter
May 19, 2019
MP6

This program reads in up to 100 strings from the file into an array. Then it will display a menu to allow the user to choose out of four options.
1) Print the strings in reverse order.
2) Print the strings in entered order.
3) Allow the user to enter a string and return the index through a linear search or -1 if found. If the string is found, it will be in all uppercase, if not, an error message will be displayed.
*/

package mp6;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Mp6  
{   

    public static void main(String[] args) throws FileNotFoundException
    {
        File inFile=new File("mp6.txt");
        Scanner out=new Scanner(inFile);
        Scanner in= new Scanner(System.in);
        System.out.println("You are now entering a menu where you enter an integer for your choice. The menu will repeat unless you quit.");
        System.out.println("Please input up to 100 strings in mp6.txt, with each string on a separate line to be into an array.");//because I specified in instructions, each string will be on a separate line in textfile.
        String[] strings=new String[100];//create array of size 100

        int currentSize=0;//initialize the currentSize of the array
        while(out.hasNextLine()&&currentSize<strings.length)//currentSize has to be less than the max length of the array
        {
            strings[currentSize]=out.nextLine();//reads in from the txt file scanner
            currentSize++;
        }
        
        System.out.println("You will choose an option on your menu in a second, but first, what is your name?");
        String user=in.nextLine();
        System.out.println("Hi, "+user+"!");//user experience
        
        
        int choice;
        do
        {
            System.out.println("Choice 1 is to print the strings in reverse order.");
            System.out.println("Choice 2 is to print the strings in entered order.");
            System.out.println("Choice 3 is to ask the user to enter a string and then return the index if the string is found with the string in all uppercase. If not found, it will return -1 with an error message.");
            System.out.println("Choice 4 is to quit.");
            System.out.println("What choice do you want? (enter integer choice)");
            
            choice=in.nextInt();
            
            switch (choice)
            {
                case 1:
                    System.out.println("This will print the strings in reverse order of your input.");
                    
                    reverseOrder(strings, currentSize);//calls method
                    
                    break;
                case 2:
                    System.out.println("This will print the strings in entered order.");
                    
                    enteredOrder(strings, currentSize);
                    break;           
                case 3:
                    System.out.println("Please enter a string and I will return the index if the string is found with the string in all uppercase. If not found, it will return -1 with an error message");
                    System.out.println("Enter a string to find in the array.");
                    in.nextLine();//junk separating nextInt and nextLine
                    String key=in.nextLine();
                    
                    int position=linearSearch(strings, currentSize, key);
                    
                    if (position==-1)//from the method to differentiate if the string was found or not
                    {
                        System.out.println("Error, the string was not found in the array!");
                    }
                    else
                    {
                        strings[position] = strings[position].toUpperCase();//assign the string to permanently change the array
                        System.out.println("String "+key+" was found at position "+position+" and is now converted to "+strings[position]+" in the array.");  
                    }
                    
                    break;
                case 4:
                    System.out.println(user+", thank you for using this program!");
                    break;
                default:
                   System.out.println("Wrong input.");//if user enters input other than integers 1-4 for the menu choice
                   break;
            }
        }
        while (choice!=4);
        out.close();//Need to close the scanner to stop the input of extra values from the mp6.txt
    }
    
    public static void reverseOrder(String[] strings, int currentSize)
    {       
        for (int i=(currentSize-1); i>=0; i--)//currenSize-1 bcause index begins at 0, not 1
        {
            System.out.println(strings[i]+"\n");//these don't have to change the strings in the array, just printing it
            //having extra line between strings is for viewing pleasure/less confusing.
        }
    }
    
    public static void enteredOrder(String[] strings, int currentSize)
    {
        for (int i=0; i< currentSize; i++)
        {
            System.out.println(strings[i]+"\n");//these don't have to change the strings in the array, just printing it
            //having extra line between strings is for viewing pleasure/less confusing.
        }
    }
    
    public static int linearSearch(String[] strings, int currentSize, String key)
    {
        int pos=0;
        boolean found=false;
        while(pos<currentSize&&!found)
        {
            if (strings[pos].equals(key))
            {
                found=true;//if there is a string that matches key
            }
            
            else
            {
                pos++;//then moves to the next string in the array to check
            }
        }
        
        if (found)
        {
            return pos;
        }
        
        else
        {
            return -1;//return -1 as per instructions so that I can use a conditional to display an error message
        }
    }
}
