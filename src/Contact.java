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

import java.util.Comparator;

public class Contact {
    private String contactName;
    private String contactPhone;
    private String contactAddress;
    private String contactEmail;

    public Contact(String name, String phone, String email, String address) {
        this.contactName = name;
        this.contactPhone = phone;
        this.contactEmail = email;
        this.contactAddress = address;
    }


    public String getContactName() {
        return this.contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public String getContactAddress() {
        return this.contactAddress;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactName(String input) {
        this.contactName = input;
    }
    public void setContactPhone(String input) {
        this.contactPhone = input;
    }
    public void setContactAddress(String input) {
        this.contactAddress = input;
    }
    public void setContactEmail(String input) {
        this.contactEmail = input;
    }

    public static Comparator<Contact>sortByName = new Comparator<Contact>() {
        @Override
        public int compare(Contact contactOne, Contact contactTwo) {
            return contactOne.getContactName().compareTo(contactTwo.getContactName());
        }
    };

    public static Comparator<Contact>sortByPhone = new Comparator<Contact>() {
        @Override
        public int compare(Contact contactOne, Contact contactTwo) {
            return contactOne.getContactPhone().compareTo(contactTwo.getContactPhone());
        }
    };

    public static Comparator<Contact>sortByEmail = new Comparator<Contact>() {
        @Override
        public int compare(Contact contactOne, Contact contactTwo) {
            return contactOne.getContactEmail().compareTo(contactTwo.getContactEmail());
        }
    };

    public static Comparator<Contact>sortByAddress = new Comparator<Contact>() {
        @Override
        public int compare(Contact contactOne, Contact contactTwo) {
            return contactOne.getContactAddress().compareTo(contactTwo.getContactAddress());
        }
    };
}
