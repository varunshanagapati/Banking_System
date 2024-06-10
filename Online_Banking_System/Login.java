package Online_Banking_System;

import java.sql.*;
import java.util.*;
public class Login {
    Scanner s=new Scanner(System.in);
    String account_number;
    Login(String account_number){
        this.account_number=account_number;
    }
    boolean check_password(String a,String b){
        if(a.compareTo(b)==0){
            return true;
        }else{
            return false;
        }
    }

    void check_Account(){
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/banking_system","root","root");    
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select account_number,passkey,first_name,balance from account_details where account_number='"+account_number+"'limit 1;");  
            rs.next();
            String fname=rs.getString(3);
            double balance=rs.getDouble(4);
            if(rs.getString(1)!=""){
                String temp=rs.getString(2);
                int j=3;    
                while(j-->0){
                    System.out.print("\nEnter your Password: ");
                    String password=s.next();
                    if(check_password(temp,password)){
                        System.out.println("\n-----Welcome back to Online Banking System-----\n");
                        Operations op=new Operations(account_number,fname,balance);
                        op.oper();
                    }else{
                        System.out.println("\n-----Password Wrong. you got "+j+"chances-----\n");
                    }
                }
            }else{
                System.out.println("\n-----You do not have an account, Create an Account for Online Banking-----\n");
            }
            con.close();  
        }catch(Exception e){ System.out.println("\n-----You do not have an account, Create an Account for Online Banking-----\n");} 
    }
 
}



