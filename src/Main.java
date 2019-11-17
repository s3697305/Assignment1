/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 1
  Author: Nguyen Quoc Hoang
  ID: s3697305
  Created  date: 15/11/2019
  Last modified: 17/11/2019
  Acknowledgement: If you use any resources, acknowledge here. Failure to do so will be considered as plagiarism.
*/

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        ArrayList<Contact> contactsList = new ArrayList<Contact>();
        programLoop(contactsList);
    }

    /**
     * Print CLI
     **/
    private static void printCLI() {
        System.out.println("----------------------------");
        System.out.println("1. Load contacts from file");
        System.out.println("2. View all contacts");
        System.out.println("3. Add new contact");
        System.out.println("4. Edit a contact");
        System.out.println("5. Delete a contact");
        System.out.println("6. Search contact list");
        System.out.println("7. Sort contact list");
        System.out.println("8. Save contacts to file");
        System.out.println("9. Quit");
    }

    /**
     * Program loop
     **/
    private static void programLoop(ArrayList<Contact> contactsList) {
        printCLI();
        System.out.print("Select a function (1-9): ");
        Scanner readInput = new Scanner(System.in);
        String userChoice = readInput.nextLine();

        while (!userChoice.equals("9")) {
            if (!userChoice.matches("[1-9]")) {
                System.out.println("Invalid Input. Please re-enter your choice.");
            } else {
                inputChoice(contactsList, userChoice);
            }
            printCLI();
            System.out.print("Select a function (1-9): ");
            userChoice = readInput.nextLine();
        }
        System.out.print("Do you want to save your file before quitting?[Y/N]: ");
        String quitChoice = readInput.nextLine();
        quitChoice = quitChoice.toLowerCase();
        if (quitChoice.equals("y")) {
            saveContactsToFile(contactsList);
            System.out.println("Your file has been saved. Thank you! :)");
            System.exit(0);
        } else if (quitChoice.equals("n")) {
            System.out.println("No changes were saved. Thank you! :)");
            System.exit(0);
        } else
            System.out.println("Invalid input. Program finished.");
    }


    /**
     * Take input function
     **/
    private static void inputChoice(ArrayList<Contact> contactsList, String userChoice) {

        int intChoice = Integer.parseInt(userChoice);

        switch (intChoice) {
            case 1:
                loadFile(contactsList);
                System.out.println();
                break;
            case 2:
                viewAllContacts(contactsList);
                System.out.println();
                break;
            case 3:
                addNewContact(contactsList);
                System.out.println();
                break;
            case 4:
                editContact(contactsList);
                System.out.println();
                break;
            case 5:
                deleteContact(contactsList);
                System.out.println();
                break;
            case 6:
                searchContact(contactsList);
                System.out.println();
                break;
            case 7:
                sortContact(contactsList);
                System.out.println();
                break;
            case 8:
                saveContactsToFile(contactsList);
                System.out.println();
                break;
            default:
                break;

        }
    }

    /**
     * Load contact from file function
     **/
    private static void loadFile(ArrayList<Contact> contactsList) {
        try {
            FileInputStream file = new FileInputStream("contacts.txt");
            Scanner readFile = new Scanner(file);

            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] contactString = line.split("; ");
                Contact contact = new Contact(contactString[0], contactString[1], contactString[2], contactString[3]);
                contactsList.add(contact);
            }
            readFile.close();
            System.out.println("File loaded successfully.");
        } catch (Exception error) {
            System.out.println("Error message: " + error);
        }
    }

    /**
     * View all contacts function
     **/
    private static void viewAllContacts(ArrayList<Contact> contactsList) {
        for (int i = 0; i < contactsList.size(); ++i) {
            System.out.print(i + 1);
            String contactValue = " || " + contactsList.get(i).getContactName() + " || "
                    + contactsList.get(i).getContactPhone() + " || "
                    + contactsList.get(i).getContactEmail() + " || "
                    + contactsList.get(i).getContactAddress();
            System.out.println(contactValue);
        }
        if (contactsList.size() == 0)
            System.out.println("Contact list is empty.");
    }

    /**
     * Add a new contact function
     **/
    private static void addNewContact(ArrayList<Contact> contactsList) {
        Scanner input = new Scanner(System.in);
        System.out.println("Creating a new contact. Please enter the required information");
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Phone number: ");
        String phone = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Address: ");
        String address = input.nextLine();

        Contact newContact = new Contact(name, phone, email, address);

        contactsList.add(newContact);

        System.out.println("A new contact has been created successfully.");
    }

    /**
     * Delete contact function
     **/
    private static void editContact(ArrayList<Contact> contactsList) {
        Scanner inputContact = new Scanner(System.in);
        Scanner inputField = new Scanner(System.in);
        Scanner inputValue = new Scanner(System.in);
        if (contactsList.size() > 0) {
            viewAllContacts(contactsList);
            System.out.print("Enter the contact you want to edit: ");
            int index = inputContact.nextInt();
            Contact editContact = contactsList.get(index - 1);
            System.out.print("Enter the field you want to edit (name, phone, email, address): ");
            String editField = inputField.nextLine();
            if (editField.equals("name")) {
                System.out.print("New value: ");
                String editValue = inputValue.nextLine();
                editContact.setContactName(editValue);
            } else if (editField.equals("phone")) {
                System.out.print("New value: ");
                String editValue = inputValue.nextLine();
                editContact.setContactPhone(editValue);
            } else if (editField.equals("email")) {
                System.out.print("New value: ");
                String editValue = inputValue.nextLine();
                editContact.setContactEmail(editValue);
            } else if (editField.equals("address")) {
                System.out.print("New value: ");
                String editValue = inputValue.nextLine();
                editContact.setContactAddress(editValue);
            } else {
                System.out.println("Contact list is empty.");
            }
            System.out.println("This field has been successfully updated.");
        }
    }

    /**
     * Delete contact function
     **/
    private static void deleteContact(ArrayList<Contact> contactsList) {
        Scanner input = new Scanner(System.in);
        Scanner deleteChoice = new Scanner(System.in);
        if (contactsList.size() > 0) {
            viewAllContacts(contactsList);
            System.out.print("Enter the contact you want to delete: ");
            int index = input.nextInt();
            System.out.println("Deleting contact" + index + ", this process cannot be undone.");
            System.out.print("Are you sure you want to delete contact " + index + "? [Y/N]: ");
            String choice = deleteChoice.nextLine();
            choice = choice.toLowerCase();
            if (choice.equals("y")) {
                contactsList.remove(index - 1);
                System.out.println("Contact has been removed successfully");
            } else if (choice.equals("n")) {
                programLoop(contactsList);
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Contact list is empty.");
        }
    }

    /**
     * Sort Contact List function
     **/
    private static void sortContact(ArrayList<Contact> contactsList) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the field you want contact list to be sorted (name, phone, email, address): ");
        String sortChoice = input.nextLine();
        if (sortChoice.equals("name")) {
            Collections.sort(contactsList, Contact.sortByName);
        } else if (sortChoice.equals("phone")) {
            Collections.sort(contactsList, Contact.sortByPhone);
        } else if (sortChoice.equals("email")) {
            Collections.sort(contactsList, Contact.sortByEmail);
        } else if (sortChoice.equals("address")) {
            Collections.sort(contactsList, Contact.sortByAddress);
        } else {
            System.out.println("Invalid input");
        }
        viewAllContacts(contactsList);
        programLoop(contactsList);
    }

    /**
     * Sort Contact List function
     **/
    private static void searchContact(ArrayList<Contact> contactsList) {
        Scanner searchFieldInput = new Scanner(System.in);
        Scanner searchKeyInput = new Scanner(System.in);
        System.out.print("Enter the searching field (name, phone, email, address): ");
        String searchField = searchFieldInput.nextLine();
        System.out.print("Enter the searching keywords: ");
        String searchKey = searchKeyInput.nextLine();
        ArrayList<Contact> searchResult = new ArrayList<Contact>();

        if (searchField.equals("name")) {
            for (int i = 0; i < contactsList.size(); ++i) {
                if (contactsList.get(i).getContactName().toLowerCase().contains(searchKey.toLowerCase())) {
                    searchResult.add(contactsList.get(i));
                }
            }
        } else if (searchField.equals("phone")) {
            for (int i = 0; i < contactsList.size(); ++i) {
                if (contactsList.get(i).getContactPhone().contains(searchKey)) {
                    searchResult.add(contactsList.get(i));
                }
            }
        } else if (searchField.equals("email")) {
            for (int i = 0; i < contactsList.size(); ++i) {
                if (contactsList.get(i).getContactEmail().contains(searchKey)) {
                    searchResult.add(contactsList.get(i));
                }
            }
        } else if (searchField.equals("address")) {
            for (int i = 0; i < contactsList.size(); ++i) {
                if (contactsList.get(i).getContactAddress().toLowerCase().contains(searchKey.toLowerCase())) {
                    searchResult.add(contactsList.get(i));
                }
            }
        } else {
            System.out.println("Invalid input");
        }
        System.out.println("Search Result");
        viewAllContacts(searchResult);
        programLoop(contactsList);
    }

    private static void saveContactsToFile(ArrayList<Contact> contactsList) {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("contacts.txt"));
        } catch (Exception error) {}

        System.out.println("Writing contacts to file.....");
        for (int i = 0; i < contactsList.size(); ++i) {
            String writeLine = contactsList.get(i).getContactName() + "; "
                    + contactsList.get(i).getContactPhone() + "; "
                    + contactsList.get(i).getContactEmail() + "; "
                    + contactsList.get(i).getContactAddress();
            outputStream.println(writeLine);
        }
        outputStream.close();
        System.out.println("Finish writing contacts to file");
    }
}