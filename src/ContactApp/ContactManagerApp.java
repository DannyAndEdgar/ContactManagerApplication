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
        }else if(response == 2){
            addContact();
        }else if(response == 3){
            getContact();
        }else if(response == 4){
            deleteContact();
        }else if(response == 5){
            editContact();

        }
        ContactStarter();
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
        System.out.printf("                Name -   Phone Number%n               ---------------%n");
        for(int i = 0; i < contactList.size(); i++){
            System.out.printf("%s %n", contactList.get(i));
        }
    }

    public static void addContact() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter contact first and lastname:");
//        String nothing = input.nextLine();
        String contactName = input.nextLine();
        String formattedName = fixedStringLength(contactName, 20);

        System.out.println("Please enter the number to add: ");
        String contactNum = input.nextLine();
        String formattedNum = fixedStringLength(contactNum, 15);


        String contact = formattedName + "  - " + formattedNum;

        boolean nameChanged = false;

        String namelowercase = contactName.toLowerCase(Locale.ROOT);
        int numLoops = contactList.size();
        for(int i = 0; i < numLoops; i++){
            String contactlowercase = contactList.get(i).toLowerCase(Locale.ROOT);
            if(contactlowercase.contains(namelowercase)){
//                System.out.println(contactList);
//                System.out.println(contactList.get(i));
                System.out.println("This name already exist. Would you like to override it?");
                String confirmation = input.nextLine();
                if(confirmation.equalsIgnoreCase("Y")){
                    contactList.set(i, contact);
                    nameChanged = true;
//                    System.out.println(contactList);
//                    break;
                }else if(confirmation.equalsIgnoreCase("N")){
                    System.out.println("Ok, please enter a new name for the contact");
                    String newName = input.nextLine();
                    String formatNewName = fixedStringLength(newName, 20);
                    String newContactName = formatNewName + "-" + formattedNum;
                    contactList.add(newContactName);
                }
            }else if(i == (numLoops - 1) && !nameChanged){
                System.out.println(!contactlowercase.contains(namelowercase));
                contactList.add(contact);

            }
        }

//        System.out.println("contact = " + contact);

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


    public static void editContact() throws IOException{



        Scanner input = new Scanner(System.in);
        System.out.println("Which contact would you like to edit? ");
        String response = input.nextLine();
        String responselc = response.toLowerCase(Locale.ROOT);

        for(int i = 0; i < contactList.size(); i++){
            String contactlowercase = contactList.get(i).toLowerCase(Locale.ROOT);
            String[] contactsplit = contactList.get(i).split(" - ");
            if(contactlowercase.contains(responselc)){
                System.out.println("Would you like to change the name or the number? 1 for number and 2 for name");
                String choice = input.nextLine();
                if(choice.equalsIgnoreCase("1")){
                    System.out.println("Please enter the new number: ");
                    String newNum = input.nextLine();
                    String formattedname = fixedStringLength(contactsplit[0],20);
                    String formattednum = fixedStringLength(newNum,15);
                    String editedContact = formattedname + " - " + formattednum;
                    contactList.set(i, editedContact);
                }else if(choice.equalsIgnoreCase("2")){
                    System.out.println("What's the new name");
                    String newName = input.nextLine();
                    String formatnewname = fixedStringLength(newName,20);
                    String formatnewdigit = fixedStringLength(contactsplit[1],15);
                    String editedName = formatnewname +"  - " + formatnewdigit;
                    contactList.set(i, editedName);
                }
            }
        }
        Files.write(dataDirectoryAndFile, contactList);
    }

    public static String fixedStringLength(String string, int length) {
        return String.format("%1$"+length + "s", string);
    }

    public static void main(String[] args) throws IOException{
        ContactApp();
    }
}
