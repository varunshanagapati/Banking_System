package Online_Banking_System;

import java.sql.*;
import java.util.*;

public class CreateAccount {
    String fname;
    String lname;
    String accountNumber;
    double depositAmount;
    String password;
    Scanner s=new Scanner(System.in);
    CreateAccount(String fname,String lname,String accountNumber,double depositAmount,String pass){
        this.fname=fname;
        this.lname=lname;
        this.accountNumber=accountNumber;
        this.depositAmount=depositAmount;
        this.password=pass;
    }
    void setDetails(){
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/banking_system","root","root");    
            Statement stmt=con.createStatement();  
            
            ResultSet rs=stmt.executeQuery("select account_number from account_details where account_number='"+accountNumber+"'limit 1;");  
            if(!rs.next()){                  //rs returns false if it is empty
                String sql = "INSERT INTO account_details(account_number,first_name,last_name,passkey,balance) VALUES ('"+accountNumber+"', '"+fname+"','"+lname+"','"+password+"','"+depositAmount+"')";
                stmt.execute(sql);
                Operations op=new Operations(accountNumber, fname, depositAmount);
                op.oper();
            }else{
                System.out.println("\n-----Account already Exists. Please Log in for Online Banking-----\n");
            }

            con.close();  
        }catch(Exception e){ System.out.println(e);}  
    }

    
}