package com.example;

import com.example.dao.CustomerDAO;
import com.example.model.Customer;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        CustomerDAO dao = new CustomerDAO();
        try {
            dao.loadAll();
            int choice;
            while(true){
                System.out.println("\n=== Customer Management System ===");
                System.out.println("1. Add Customer");
                System.out.println("2. View All Customers");
                System.out.println("3. Update Customer");
                System.out.println("4. Delete Customer");
                System.out.println("5. View Customer by ID");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                  if(choice==6){
                      System.out.println("Exit");
                      break;
                  }
                switch (choice) {
                    case 1:
                        System.out.print("Enter Customer ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter Customer Name: ");
                        String name = sc.next();
                        sc.nextLine();
                        System.out.print("Enter Customer Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Customer Phone: ");
                        String phone = sc.nextLine();
                        dao.add(new Customer(id, name, email, phone));
                        break;

                        case 2:
                            dao.viewAll();
                            break;

                        case 3:
                            System.out.print("Enter Customer ID to update: ");
                            id = sc.nextInt();
                            System.out.print("Enter Customer Name: ");
                            String newName = sc.next();
                            sc.nextLine();
                            System.out.print("Enter Customer Email: ");
                            String newEmail = sc.nextLine();
                            System.out.print("Enter Customer Phone: ");
                            String newPhone = sc.nextLine();
                            dao.update(new Customer(id, newName, newEmail, newPhone));
                            break;

                            case 4:
                                System.out.print("Enter Customer ID to delete: ");
                                id=sc.nextInt();
                                dao.delete(id);
                                break;

                             case 5:
                                 System.out.println("By ID");
                                 id = sc.nextInt();
                                 Customer c=dao.findById(id);
                                 if(c!=null){
                                     System.out.println(c.toString());
                                 }
                                 else {
                                     System.out.println("Customer not found");
                                 }
                                 break;
                                 default:
                                 System.out.println("Wrong choice");

                }
            }

        } catch (SQLException e) {
            System.out.println("SQLException"+e.getMessage());
        }
    }
}