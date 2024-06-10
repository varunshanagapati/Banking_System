package Online_Banking_System;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        while(true){
        System.out.println("\n-----Welcome to Banking System-----");
        System.out.print("1.Sign up\n2.Log in\n3.Exit\n----------\nChoose Operation: ");
        int option=s.nextInt();
        String accountNumber,pass;
        switch (option) {
            case 1:
            System.out.print("Enter Account Number: ");
            accountNumber=s.next();
            System.out.print("Enter Your First Name: ");
            String fname=s.next();
            System.out.print("Enter Your Last Name: ");
            String lname=s.next();
            System.out.print("Enter new Password: ");
            pass=s.next();
            System.out.print("Enter the depositing Amount: ");
            double depositAmount=s.nextDouble();
            CreateAccount ac=new CreateAccount(fname,lname,accountNumber,depositAmount,pass);
            ac.setDetails();
                break;
            case 2:
            System.out.print("Enter your Account Number: ");
            accountNumber=s.next();
            Login lg=new Login(accountNumber);
            lg.check_Account();
            break;
            case 3:
            System.exit(0);
            default:
            System.out.println("\n-----Invalid Input-----\n");
                break;
        }
    }
    }
    
}
