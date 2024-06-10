package Online_Banking_System;

import java.sql.*;
import java.util.*;

import com.mysql.cj.xdevapi.SelectStatement;

public class Operations {
    Scanner s=new Scanner(System.in);
    String account_number,fname;
    double balance;
    Operations(String account_number,String fname,double balance){
        this.account_number=account_number;
        this.fname=fname;
        this.balance=balance;
    }

    void debitAmount(double debit){
        if(balance-debit>=0){
            balance-=debit;
            try{  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/banking_system","root","root");    
                Statement stmt=con.createStatement();  
                String sql = "INSERT INTO transaction_details(transaction_date,account_number,debit_amount,credit_amount,balance) VALUES (curdate(), '"+account_number+"','"+debit+"',0,'"+balance+"')";
                stmt.execute(sql);
                String s2 = "update account_details set balance='"+balance+"' where account_number='"+account_number+"'";
                stmt.execute(s2);
                System.out.println("\n-----Amount debited Successfully-----\n");
            }catch(Exception e){ System.out.println(e);}  
        }else{
            System.out.println("\n-----Insufficient Funds-----\n");
        }

    }

    void depositAmount(double deposit){
        balance+=deposit;
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/banking_system","root","root");    
            Statement stmt=con.createStatement();  
            String sql = "INSERT INTO transaction_details(transaction_date,account_number,debit_amount,credit_amount,balance) VALUES (curdate(), '"+account_number+"',0,'"+deposit+"','"+balance+"')";
            stmt.execute(sql);
            String s2 = "update account_details set balance='"+balance+"' where account_number='"+account_number+"'";
            stmt.execute(s2);
            System.out.println("\n-----Amount Deposited Successfully-----\n");
        }catch(Exception e){ System.out.println(e);}  
    }

    void balance_enquiry(){
        System.out.println("Your balance amount : "+balance);
    }

    void transactions(){
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/banking_system","root","root");    
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from transaction_details where account_number='"+account_number+"' order by transaction_date");  
            if(!rs.next()){
                System.out.println("\n-----No Transactions Made-----\n");
            }else{
                System.out.println("|Date|     |Account Number|   |Debit Amount|  |Credit Amount| |Balance Amount|");
                while(rs.next())  
                System.out.println(rs.getString(1)+"      "+rs.getString(2)+"          "+rs.getDouble(3)+"              "+rs.getDouble(4)+"           "+rs.getDouble(5));   
            }
            con.close();  
            }catch(Exception e){ System.out.println(e);}  
    }

    void oper(){
        System.out.println("\n----- Account Number: "+account_number+"\n----- Name: "+fname+" -----\n");
        while(true){
            System.out.print("-----------\n1.Withdraw\n2.Deposit\n3.Balance Enquiry\n4.Transactions\n5.Exit\nSelect the Operation(1/2/3/4/5): ");
            int option=s.nextInt();
            switch (option) {
                case 1:System.out.print("Enter Amount: ");
                double debit=s.nextDouble();
                debitAmount(debit);               
                break;
                case 2:System.out.print("Enter Amount: ");
                double deposit=s.nextDouble();
                depositAmount(deposit);
                break;
                case 3:balance_enquiry();
                break;
                case 4:transactions();
                break;
                case 5:
                System.out.println("\n-----Thank you for the transaction :) -----\n");
                System.exit(0);
                default:
                System.out.println("\n-----Invalid Input-----\n");
                    break;
            }
        }
    }


}
