/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author darren
 */
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Mimi Opkins with some tweaking from Dave Brown
 */
public class JDBC {
    //  Database credentials
    static String USER = "Sage";
    static String PASS = "1112";
    static String DBNAME = "JDBC";
    //This is the specification for the printout that I'm doing:
    //each % denotes the start of a new field.
    //The - denotes left justification.
    //The number indicates how wide to make the field.
    //The "s" denotes that it's a string.  All of our output in this test are
    //strings, but that won't always be the case.
    static final String displayFormat="%-5s%-15s%-15s%-15s\n";
// JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
//            + "testdb;user=";
/**
 * Takes the input string and outputs "N/A" if the string is empty or null.
 * @param input The string to be mapped.
 * @return  Either the input string or "N/A" as appropriate.
 */
    public static String dispNull (String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
    public static void main(String[] args) {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to
        //remove that from the connection string.
        Scanner in = new Scanner(System.in);
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        Connection conn = null; //initialize the connection
        Statement stmt = null;  //initialize the statement that we're using
        PreparedStatement pstmt = null;
        
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            ResultSet rs = null;
            String input;
            String input2;
            int numRows;
            int check;
            boolean valid;
            
            while(true) {
                //JDBC Menu
                System.out.println("Choose an option from the menu below: ");
                System.out.println("\t1. List all writing groups.");
                System.out.println("\t2. List all the data for a group specified by the user.");
                System.out.println("\t3. List all publishers.");
                System.out.println("\t4. List all the data for a publisher specified by the user.");
                System.out.println("\t5. List all book titles.");
                System.out.println("\t6. List all the data for a single book specified by the user.");
                System.out.println("\t7. Insert a new book.");
                System.out.println("\t8. Insert a new publisher and update all book published by one publisher to be published by the new publisher.");
                System.out.println("\t9. Remove a single book specified by the user.");
                System.out.println("\t10. Quit.");
                
                int options = 0;
                valid = false;
                while(!valid) {
                    if(in.hasNextInt()) {
                        options = in.nextInt();
                        if(options <= 10 && options >= 1) {
                            valid = true;
                        }
                        else {
                            System.out.println("Invalid Range.");
                        }
                        in.nextLine();
                    }
                    else {
                        in.nextLine();
                        System.out.println("Invalid Input.");
                    }
                }
                
                if(options != 10) {
                    switch (options) {
                        case 1:
                            sql = "SELECT * FROM WritingGroups";
                            rs = stmt.executeQuery(sql);
                            //STEP 5: Extract data from result set
                            System.out.printf(displayFormat, "GroupName","HeadWriter","YearFormed","Subject");
                            while (rs.next()) {
                                //Retrieve by column name
                                String name = rs.getString("GroupName");
                                String writer = rs.getString("HeadWriter");
                                String year = rs.getString("YearFormed");
                                String sub = rs.getString("Subject");
                                //Display values
                                System.out.printf(displayFormat,
                                        dispNull(name), dispNull(writer), dispNull(year), dispNull(sub));
                            }
                            break;
                        case 2:
                            System.out.print("Enter a GroupName to search for: ");
                            input = in.nextLine();
                            
                            sql = "SELECT * FROM WritingGroups NATURAL JOIN Books NATURAL JOIN Publishers WHERE GroupName = ?";
                            //System.out.println("What group would you like to select from?");
                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, input);
                            rs = pstmt.executeQuery();
                            while (rs.next()) {
                                String name  = rs.getString("GroupName");
                                String writer = rs.getString("HeadWriter");
                                String year = rs.getString("YearFormed");
                                String sub = rs.getString("Subject");
                                String title = rs.getString("BookTitle");
                                String pName = rs.getString("PublisherName");
                                String pYear = rs.getString("YearPublished");
                                String pages = rs.getString("NumberPages");
                                String address = rs.getString("PublisherAddress");
                                String phone = rs.getString("PublisherPhone");
                                //Display values
                                System.out.print("GroupName: " + name);
                                System.out.print(", HeadWriter: " + writer);
                                System.out.print(", YearFormed: " + year);
                                System.out.print(", Subject: " + sub);
                                System.out.print(", BookTitle: " + title);
                                System.out.print(", PublisherName: " + pName);
                                System.out.print(", YearPublished: " + pYear);
                                System.out.print(", NumberPages: " + pages);
                                System.out.print(", PublisherAddress: " + address);
                                System.out.println(", PublisherPhone: " + phone);
                            }
                            break;
                        case 3:
                            sql = "SELECT * FROM Publishers";
                            rs = stmt.executeQuery(sql);
                            //STEP 5: Extract data from result set
                            System.out.printf(displayFormat, "PublisherName","PublisherAddress","PublisherPhone","PublisherEmail");
                            while (rs.next()) {
                                //Retrieve by column name
                                String name = rs.getString("PublisherName");
                                String address = rs.getString("PublisherAddress");
                                String phone = rs.getString("PublisherPhone");
                                String email = rs.getString("PublisherEmail");
                                //Display values
                                System.out.printf(displayFormat,
                                        dispNull(name), dispNull(address), dispNull(phone), dispNull(email));
                            }
                            break;
                        case 4:
                            System.out.print("Enter a PublisherName to search for: ");
                            input = in.nextLine();
                            
                            sql = "SELECT * FROM Publishers NATURAL JOIN Books NATURAL JOIN WritingGroups WHERE PublisherName = ?";
                            //System.out.println("What group would you like to select from?");
                            pstmt = conn.prepareStatement(sql);
                            
                            pstmt.setString(1, input);
                            rs = pstmt.executeQuery();
                            while (rs.next()) {
                                String name  = rs.getString("GroupName");
                                String writer = rs.getString("HeadWriter");
                                String year = rs.getString("YearFormed");
                                String sub = rs.getString("Subject");
                                String title = rs.getString("BookTitle");
                                String pName = rs.getString("PublisherName");
                                String pYear = rs.getString("YearPublished");
                                String pages = rs.getString("NumberPages");
                                String address = rs.getString("PublisherAddress");
                                String phone = rs.getString("PublisherPhone");
                                //Display values
                                System.out.print("GroupName: " + name);
                                System.out.print(", HeadWriter: " + writer);
                                System.out.print(", YearFormed: " + year);
                                System.out.print(", Subject: " + sub);
                                System.out.print(", BookTitle: " + title);
                                System.out.print(", PublisherName: " + pName);
                                System.out.print(", YearPublished: " + pYear);
                                System.out.print(", NumberPages: " + pages);
                                System.out.print(", PublisherAddress: " + address);
                                System.out.println(", PublisherPhone: " + phone);
                            }
                            break;
                        case 5:
                            sql = "SELECT * FROM Books";
                            rs = stmt.executeQuery(sql);
                            //STEP 5: Extract data from result set
                            System.out.printf(displayFormat, "GroupName","BookTitle","PublisherName","YearPublished","NumberPages");
                            while (rs.next()) {
                                //Retrieve by column name
                                String gName = rs.getString("GroupName");
                                String title = rs.getString("BookTitle");
                                String pName = rs.getString("PublisherName");
                                String year = rs.getString("YearPublished");
                                String pages = rs.getString("NumberPages");
                                //Display values
                                System.out.printf(displayFormat,
                                        dispNull(gName), dispNull(title), dispNull(pName), dispNull(year), dispNull(pages));
                            }
                            break;
                        case 6:
                            System.out.print("Enter a GroupName to search for: ");
                            input = in.nextLine();
                            System.out.print("Enter a BookTitle to search for: ");
                            input2 = in.nextLine();
                            sql = "SELECT * FROM WritingGroups NATURAL JOIN Books NATURAL JOIN Publishers WHERE GroupName = ? AND BookTitle = ?";
                            //System.out.println("What group would you like to select from?");

                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, input);
                            pstmt.setString(2, input2);
                            rs = pstmt.executeQuery();
                            while (rs.next()) {
                                String name  = rs.getString("GroupName");
                                String writer = rs.getString("HeadWriter");
                                String year = rs.getString("YearFormed");
                                String sub = rs.getString("Subject");
                                String title = rs.getString("BookTitle");
                                String pName = rs.getString("PublisherName");
                                String pYear = rs.getString("YearPublished");
                                String pages = rs.getString("NumberPages");
                                String address = rs.getString("PublisherAddress");
                                String phone = rs.getString("PublisherPhone");
                                //Display values
                                System.out.print("GroupName: " + name);
                                System.out.print(", HeadWriter: " + writer);
                                System.out.print(", YearFormed: " + year);
                                System.out.print(", Subject: " + sub);
                                System.out.print(", BookTitle: " + title);
                                System.out.print(", PublisherName: " + pName);
                                System.out.print(", YearPublished: " + pYear);
                                System.out.print(", NumberPages: " + pages);
                                System.out.print(", PublisherAddress: " + address);
                                System.out.println(", PublisherPhone: " + phone);
                            }
                            break;

                            //case7 Insert a new book.");
                            //case 8. Insert a new publisher and update all book published by one publisher to be published by the new publisher.");
                            //case 9. Remove a single book specified by the user.");
                        case 7:
                            try {
                                sql = "Insert into BOOKS(GROUPNAME, BOOKTITLE, PUBLISHERNAME, YEARPUBLISHED, NUMBERPAGES) values (?, ?, ?, ?, ?)";
                                pstmt = conn.prepareStatement(sql);
                                System.out.print("Enter a GroupName to insert: ");
                                input = in.nextLine();
                                pstmt.setString(1, input);
                                System.out.print("Enter a BookTitle to insert: ");
                                input = in.nextLine();
                                pstmt.setString(2, input);
                                System.out.print("Enter a PublisherName to insert: ");
                                input = in.nextLine();
                                pstmt.setString(3, input);
                                valid = false;
                                while(!valid) {
                                    System.out.print("Enter a YearPublished to insert: ");
                                    if(in.hasNextInt()) {
                                        check = in.nextInt();
                                        if(check > 0) {
                                            pstmt.setInt(4, check);
                                            valid = true;
                                        }
                                        else {
                                            System.out.println("Invalid Range.");
                                        }
                                        in.nextLine();
                                    }
                                    else {
                                        in.next();
                                        System.out.println("Invalid Input.");
                                    }
                                }

                                valid = false;
                                while(!valid) {
                                    System.out.print("Enter a NumberPages to insert: ");
                                    if(in.hasNextInt()) {
                                        check = in.nextInt();
                                        if(check > 0) {
                                            pstmt.setInt(5, check);
                                            valid = true;
                                        }
                                        else {
                                            System.out.println("Invalid Range.");
                                        }
                                        in.nextLine();
                                    }
                                    else {
                                        in.next();
                                        System.out.println("Invalid Input.");
                                    }
                                }
                                numRows = pstmt.executeUpdate();
                                System.out.println(numRows + " affected");
                            }
                            catch(SQLException se) {
                                System.out.println("Could not process the insertion because either GroupName or PublisherName does not exist or the insertion already exists");
                            }
                            break;
                        case 8:
                            try
                            {
                                sql = "Insert into PUBLISHERS(PUBLISHERNAME, PUBLISHERADDRESS, PUBLISHERPHONE, PUBLISHEREMAIL) VALUES (?, ?, ?, ?)";
                                pstmt = conn.prepareStatement(sql);
                                System.out.print("Enter a PublisherName to insert: ");
                                input = in.nextLine();
                                String pName = input;
                                pstmt.setString(1, input);
                                System.out.print("Enter a PublisherAddress to insert: ");
                                input = in.nextLine();
                                pstmt.setString(2, input);
                                System.out.print("Enter a PublisherPhone to insert: ");
                                input = in.nextLine();
                                pstmt.setString(3, input);
                                System.out.print("Enter a PublisherEmail to insert: ");
                                input = in.nextLine();
                                pstmt.setString(4, input);
                                numRows = pstmt.executeUpdate();
                                System.out.println(numRows + " affected.");

                                sql = "UPDATE BOOKS SET PUBLISHERNAME = ? WHERE PUBLISHERNAME = ?";
                                pstmt = conn.prepareStatement(sql);
                                pstmt.setString(1, pName);
                                System.out.print("Enter a PublisherName to replace: ");
                                input = in.nextLine();
                                pstmt.setString(2, input);
                                numRows = pstmt.executeUpdate();
                                System.out.println(numRows + " affected.");
                            }
                            catch(SQLException se) {
                                System.out.println("The publsher already exists");
                            }
                            break;
                        case 9:
                            sql = "DELETE FROM Books WHERE BookTitle = ? AND GroupName = ?";
                            pstmt = conn.prepareStatement(sql);
                            System.out.print("Enter a BookTitle to delete: ");
                            input = in.nextLine();
                            pstmt.setString(1, input);
                            System.out.print("Enter a GroupName to delete: ");
                            input = in.nextLine();
                            pstmt.setString(2, input);
                            numRows = pstmt.executeUpdate();
                            System.out.println(numRows + " affected.");

                            break;
                    }
                }
                else {
                    System.out.println("Thank you.");
                    if(rs != null) {
                        rs.close();
                    }
                    stmt.close();
                    conn.close();
                    break;
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample}