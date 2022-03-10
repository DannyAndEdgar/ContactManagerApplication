package ContactApp;


import java.nio.file.Files;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class ContactManagerApp {

    private static List<String> contactList;
    private static Path dataDirectoryAndFile;


    public static void ContactApp() throws IOException {
        ContactStarter();
        Files.write(dataDirectoryAndFile, contactList);
        System.out.println("Thank you for using this app!");
    }
    public static void ContactStarter() throws IOException {

//        System.out.println("dataDirectoryAndFile = " + dataDirectoryAndFile);

//        for(int i = 0; i < contactList.size(); i++){
//            System.out.println("contactList.get(i) = " + contactList.get(i));
//        }

        contactList = getContactList();

        Scanner input = new Scanner(System.in);

        int response = getOption();

        if(response == 1){
            viewAllContacts();
            ContactStarter();
        }else if(response == 2){
            addContact();
            ContactStarter();
        }else if(response == 3){
            getContact();
            ContactStarter();
        }else if(response == 4){
            deleteContact();
            ContactStarter();
        }

    }

    public static List<String> getContactList() throws IOException {
        String directory = "./src/ContactApp/data"; // here we made the file path and and gave it the name contactholder.text

        Path dataDirectory = Paths.get(directory);

        String fileName = "contactholder.txt";

        dataDirectoryAndFile = Paths.get(directory, fileName);

        if(Files.notExists(dataDirectoryAndFile)){
            Files.createDirectories(dataDirectory);

            Files.createFile(dataDirectoryAndFile);

            System.out.println();

        }
        return Files.readAllLines(dataDirectoryAndFile);
    }

    public static int getOption(){
        Scanner input = new Scanner(System.in);

        System.out.println("What would you like to do?\n" +
                "\n" +
                "0 - exit\n" +
                "1 - view all contacts\n" +
                "2 - add contact\n" +
                "3 - search a contact by name\n" +
                "4 - delete a contact by name\n" +
                "5 - edit an existing contact\n" +
                "Enter your choice: ");

         return input.nextInt();
    }

    public static void viewAllContacts() throws IOException {
        contactList = getContactList();
        System.out.println("Here are your contacts: ");
        System.out.printf("Name | Phone Number%n---------------%n");
        for(int i = 0; i < contactList.size(); i++){
            System.out.printf("%s %n", contactList.get(i));
        }
    }

    public static void addContact() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter contact first and lastname:");
//        String nothing = input.nextLine();
        String contactName = input.nextLine();
        System.out.println("Please enter the number to add: ");
        String contactNum = input.nextLine();
        String contact = contactName + " | " +contactNum;
        System.out.println("contact = " + contact);
        contactList.add(contact);
        Files.write(dataDirectoryAndFile,contactList);

    }

    public static void getContact(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a name you would like to look for: ");
//        String nothing = input.nextLine();
        String nameLook = input.nextLine();
        String namelowercase = nameLook.toLowerCase(Locale.ROOT);

        for(int i = 0; i < contactList.size(); i++){
            String contactlowercase = contactList.get(i).toLowerCase(Locale.ROOT);
            if(contactlowercase.contains(namelowercase)){
                System.out.println(contactList.get(i));
            }
        }
    }

    public static void deleteContact() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the name of the contact you would like to delete: ");
//        String nothing = input.nextLine();
        String nameToDelete = input.nextLine();
        String nameDeletelowercase = nameToDelete.toLowerCase(Locale.ROOT);
        for(int i = 0; i < contactList.size(); i++) {
            String contactlowercase = contactList.get(i).toLowerCase(Locale.ROOT);
            if (contactlowercase.contains(nameDeletelowercase)) {
                contactList.remove(i);
            }
        }
        Files.write(dataDirectoryAndFile, contactList);
    }

    public static void main(String[] args) throws IOException{
        ContactApp();
    }

}
