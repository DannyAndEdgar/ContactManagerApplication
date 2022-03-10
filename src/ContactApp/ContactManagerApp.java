package ContactApp;


import java.nio.file.Files;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



public class ContactManagerApp {

    public static void ContactStarter() throws IOException {

        String directory = "./src/ContactApp/data"; // here we made the file path and and gave it the name contactholder.text

        Path dataDirectory = Paths.get(directory);

        String fileName = "contactholder.txt";

        Path dataDirectoryAndFile = Paths.get(directory, fileName);

        if(Files.notExists(dataDirectoryAndFile)){
            Files.createDirectories(dataDirectory);

            Files.createFile(dataDirectoryAndFile);
        }
        System.out.println("dataDirectoryAndFile = " + dataDirectoryAndFile);

        System.out.println("Here are your contacts: ");
        System.out.println();


        List<String> contactList = Files.readAllLines(dataDirectoryAndFile);

//        for(int i = 0; i < contactList.size(); i++){
//            System.out.println("contactList.get(i) = " + contactList.get(i));
//        }

        Scanner input = new Scanner(System.in);

        System.out.println("What would you like to do?\n" +
                "\n" +
                "0 - exit\n" +
                "1 - view all contacts\n" +
                "2 - add contact\n" +
                "3 - search a contact by name\n" +
                "4 - delete a contact by name\n" +
                "5 - Delete an existing contact\n" +
                "Enter your choice: ");
        
        int response = input.nextInt();
        if(response == 1){
            for(int i = 0; i < contactList.size(); i++){
                System.out.printf("%s %n", contactList.get(i));
            }
        }else if(response == 2){
            System.out.println("Please enter contact first and lastname:");
            String nothing = input.nextLine();
            String contactName = input.nextLine();
            System.out.println("Please enter the number to add: ");
            String contactNum = input.nextLine();
            String contact = contactName + " " +contactNum;
            System.out.println("contact = " + contact);
            contactList.add(contact);
            Files.write(dataDirectoryAndFile,contactList);
            ContactStarter();
        }else if(response == 3){

            System.out.println("Please enter a name you would like to look for: ");
            String nothing = input.nextLine();
            String nameLook = input.nextLine();
            String namelowercase = nameLook.toLowerCase(Locale.ROOT);

            for(int i = 0; i < contactList.size(); i++){
                String contactlowercase = contactList.get(i).toLowerCase(Locale.ROOT);
                if(contactlowercase.contains(namelowercase)){
                    System.out.println(contactList.get(i));
                }
            }
        }



    }

    public static void main(String[] args) throws IOException{
        ContactStarter();
    }

}
