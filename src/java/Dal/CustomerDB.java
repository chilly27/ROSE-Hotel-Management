/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Customer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt; 

/**
 *
 * @author Admin
 */
public class CustomerDB extends DBContext {

    public Customer loginGoogle(String googleID) {
        String sql = "SELECT * FROM [Customer] WHERE [GoogleID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, googleID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8).charAt(0), rs.getString(9), rs.getString(10), rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer registerGoogle(String name, String pictureUrl, String googleID, String email) {
        String sql = "INSERT INTO [dbo].[Customer]\n"
                    + "([Email]\n"
                    + ",[Username]\n"
                    + ",[Avatar]\n"
                    + ",[Role]\n"
                    + ",[GoogleID])\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, name);
            st.setString(3, pictureUrl);
            st.setInt(4, 0);
            st.setString(5, googleID);
            st.executeUpdate();
            return getCustomerByGoogleID(googleID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomerByGoogleID(String googleID) {
        String sql = "SELECT * FROM [Customer] WHERE [GoogleID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, googleID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8).charAt(0), rs.getString(9), rs.getString(10), rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkAccountGoogle(String googleUserId) {
        String sql = "SELECT * FROM [Customer] WHERE [GoogleID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, googleUserId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAvatar(Customer customer) {
        String sql = "UPDATE [dbo].[Customer]\n"
                    + "SET [Avatar] = ?\n"
                    + "WHERE [CustomerID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customer.getAvatar());
            st.setInt(2, customer.getCustomerID());
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String email, String password) {
        String sql = "UPDATE [dbo].[Customer]\n"
                    + "SET [Password] = ?\n"
                    + "WHERE [Email] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Update profile
    public Customer changeUserInfo(String username, String phone, Date dob, String address, int customerID) {
        String sql = "UPDATE [dbo].[Customer]\n"
                    + "SET [Username] = ?\n"
                    + ",[CusPhone] = ?\n"
                    + ",[Dob] = ?\n"
                    + ",[Address] = ?\n"
                    + "WHERE [CustomerID] = ?";
        try {
            // Khai báo một PreparedStatement
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, phone);
            st.setDate(3, dob);
            st.setString(4, address);
            st.setInt(5, customerID);
            st.executeUpdate();
            return getCustomerByID(customerID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Register new customer
    public void register(String email, String username, String password, String phone, Date dob, char gender) {
        String sqlQuery = "INSERT INTO [dbo].[Customer]\n" +
                  "([Email]\n" +
                  ",[Username]\n" +
                  ",[Password]\n" +
                  ",[CusPhone]\n" +
                  ",[Dob]\n" +
                  ",[Role]\n" +
                  ",[Gender])\n" +
                  "VALUES\n" +
                  "(?,?,?,?,?,?,?)";

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            // Prepare the statement
            PreparedStatement st = connection.prepareStatement(sqlQuery);
            st.setString(1, email);
            st.setString(2, username);
            st.setString(3, hashedPassword); // Store the hashed password
            st.setString(4, phone);
            st.setDate(5, dob);
            st.setInt(6, 0); // Assuming 0 is the default role value
            st.setString(7, String.valueOf(gender)); // Set gender as a string

            // Log before executing the update
            System.out.println("Executing insert: " + st);
            st.executeUpdate();

            System.out.println("User inserted: " + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get customer by ID
    public Customer getCustomerByID(int customerID) {
        String sql = "SELECT * FROM [Customer] WHERE [CustomerID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8).charAt(0), rs.getString(9), rs.getString(10), rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Check if email exists
    public Customer checkEmailExist(String email) {
        String sql = "SELECT [CustomerID]\n" +
"      ,[Email]\n" +
"      ,[Username]\n" +
"      ,[Password]\n" +
"      ,[CusPhone]\n" +
"      ,[Dob]\n" +
"      ,[Role]\n" +
"      ,[Gender]\n" +
"  FROM [dbo].[Customer] WHERE [Email] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                char gender = rs.getString(8).charAt(0);
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Customer checkUserNameExist(String username) {
        String sql = "SELECT [CustomerID]\n" +
"      ,[Email]\n" +
"      ,[Username]\n" +
"      ,[Password]\n" +
"      ,[CusPhone]\n" +
"      ,[Dob]\n" +
"      ,[Role]\n" +
"      ,[Gender]\n" +
"  FROM [dbo].[Customer] WHERE [Username] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                char gender = rs.getString(8).charAt(0);
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //login
        public Customer login(String email, String password) {
    String sqlQuery = "SELECT * " +
                      "FROM [dbo].[Customer] WHERE [Email] = ?";
    
    try {
        PreparedStatement st = connection.prepareStatement(sqlQuery);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            String storedPasswordHash = rs.getString("Password");
            if (BCrypt.checkpw(password, storedPasswordHash)) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8).charAt(0), rs.getString(9), rs.getString(10), rs.getString(11));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
         //get customer by email
        public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM [Customer] WHERE [Email] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
             char gender = rs.getString(8).charAt(0);
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    

    public static void main(String[] args) {
        // Create an instance of CustomerDB
        CustomerDB customerDB = new CustomerDB();

        // Sample data for testing
        String email = "testemail@example.com";
        String username = "testuser";
        String password = "password123";
        String phone = "123456789";
        char gender = 'M';  // Gender represented by a single character
        // Use java.sql.Date to represent birthDate
        Date birthDate = Date.valueOf("2000-01-01"); // YYYY-MM-DD format

        // Call the register method to test
        customerDB.register(email, username, password, phone, birthDate, gender);
        
        // Log success message after testing
        System.out.println("Customer registration test completed.");
    }
}