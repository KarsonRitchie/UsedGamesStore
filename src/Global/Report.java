/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLManager;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Karson
 * 
 * A class used to make html reports
 */
public class Report {

    //Variables to help us create the reports
    
    //result sets
    ResultSet rs;
    ResultSetMetaData md;
    
    //body objects to format the report
    String baseIntro = "<!doctype html><html><body>";
    String title = "";
    String baseBody = "";
    String baseClose = "</table></body></html>";
    String header = "";
    String style = "";
    
    //pricing information
    int discountID = 0;
    float discounted = 0.00f;
    float tax = 0.00f;
    float totalFinal = 0.00f;
    float subtotal = 0.00f;
    String discountAmount = "";

    public Report(String title, ResultSet rs, int discountID, float discounted, float tax, float totalFinal, float subtotal, String discountAmount) throws Exception {
        this.title = title;
        this.rs = rs;
        md = rs.getMetaData();

        this.discountID = discountID;
        this.discounted = discounted;
        this.tax = tax;
        this.totalFinal = totalFinal;
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;

        setStyle();
        setReceiptHeader();
        fillTable();
        buildReport();
    }

    //make another report creator so we can have more specific reports
    public Report(ResultSet rs, boolean inventory, boolean specific) throws Exception {
        setStyle();
        md = rs.getMetaData();

        if (inventory) {
            setInventoryHeader();
            fillInventoryTable(rs);
        } else {
            setSalesHeader(specific);
            salesReport(rs, specific);
        }
        buildReport();

    }
    
    public Report(String title){
    
        this.title = title;
        setStyle();
    
    }
    
    /**
     * Sets the style of the html report
     */
    void setStyle() {

        style = "\n<style> table{"
                + "border-collapse: collapse;"
                + "border-block-style: groove;"
                + "margin-left: auto;"
                + "margin-right: auto;}"
                + "th{  background-color: lightsteelblue;"
                + "padding: 10px;}"
                + "td { padding: 6px;}"
                + "tr:nth-child(odd){background-color: #4192D9;}"
                + "tr:nth-child(even){background-color: #4192D9;}"
                + ".TableTitle{"
                + "text-align: center;"
                + "font-weight: bold;"
                + "font-size: 20px;"
                + "}"
                + "</style>\n";
    }

    /**
     * if the report is a receipt, this method should be used to set the header
     * @throws Exception 
     */
    void setReceiptHeader() throws Exception {
        header = "<div class = TableTitle>" + "Receipt" + "</div><table><tr>\n";
        header += "<div><th>Customer: " + Variables.currentUser + "</div></th><th></th><th></th><th></th><tr>\n";
        if (Variables.currentLevel.equals("Manager")) {
            header += "<div><th>Manager: " + Variables.currentManager + "</div></th><th></th><th></th><th></th><tr>\n";
        }
        for (int i = 0; i < md.getColumnCount(); i++) {
            header += "<th>" + md.getColumnName(i + 1).toUpperCase() + "</th>";
        }
        header += "</tr>\n";
    }

    /**
     * A method to make reports for overall sales of the store or customer
     * @param specific
     * True if it is a specific customer, False if not
     * @throws Exception 
     */
    void setSalesHeader(boolean specific) throws Exception {
        header = "<div class = TableTitle>" + "Sales Report" + "</div><table><tr>\n";
        if (specific) {
            header += "<div><th>Customer: " + Variables.currentUser + "</div></th><th></th><th></th><th></th><th></th><tr>\n";
        }

        header += "<th>Title</th>";
        header += "<th>Console</th>";
        header += "<th>Total Sold</th>";
        if (!specific) {
            header += "<th>Total Earned</th>";
        } else {
            header += "<th>Total Spent</th>";
        }
        header += "<th>Order Date</th>";
        header += "</tr>\n";
    }
    
    /**
     * if the report is an inventory report, this method should be used to set the header
     * @throws Exception 
     */
    void setInventoryHeader() throws Exception {
        header = "<div class = TableTitle>" + "Inventory Report" + "</div><table><tr>\n";

        header += "<th>Title</th>";
        header += "<th>Price</th>";
        header += "<th>Console</th>";
        header += "<th>Quantity</th>";
        header += "<th>Restock Threshold</th>";
        header += "<th>Needs Restocking?</th>";
        header += "</tr>\n";
    }

    /**
     * Fills the table of the report with the appropiate data if the report is a receipt
     * @throws Exception 
     */
    void fillTable() throws Exception {

        while (rs.next()) {
            baseBody += "<tr>";

            //we will be making the basebody ourselves
            baseBody += "<td>" + rs.getString(1) + "</td>";
            baseBody += "<td>$" + String.format("%.2f", rs.getFloat(2)) + "</td>";
            baseBody += "<td>" + rs.getString(3) + "</td>";
            baseBody += "<td>$" + String.format("%.2f", rs.getFloat(4)) + "</td>";

//            for (int i = 1; i < md.getColumnCount() + 1; i++) {
//                baseBody += "<td>" + rs.getString(i) + "</td>";
//            }
            baseBody += "</tr>\n";
        }

        //and then we add the final parts of the receipt
        baseBody += "<tr>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "</tr>\n";

        baseBody += "<tr>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "</tr>\n";

        baseBody += "<tr>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td> Subtotal:</td>";
        baseBody += "<td>$" + String.format("%.2f", subtotal) + "</td>";
        baseBody += "</tr>\n";

        baseBody += "<tr>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "</tr>\n";

        if (discountID != 0) {

            baseBody += "<tr>";
            baseBody += "<td></td>";
            baseBody += "<td> Discount:</td>";
            baseBody += "<td>" + discountAmount + "</td>";
            baseBody += "<td>-($" + String.format("%.2f", discounted) + ") off cart</td>";
            baseBody += "</tr>\n";

            baseBody += "<tr>";
            baseBody += "<td></td>";
            baseBody += "<td></td>";
            baseBody += "<td> Discounted Subtotal:</td>";
            baseBody += "<td>$" + String.format("%.2f", subtotal - discounted) + "</td>";
            baseBody += "</tr>\n";
        }

        baseBody += "<tr>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td> Tax:</td>";
        baseBody += "<td>$" + String.format("%.2f", tax) + "</td>";
        baseBody += "</tr>\n";

        baseBody += "<tr>";
        baseBody += "<td></td>";
        baseBody += "<td></td>";
        baseBody += "<td> Final Total:</td>";
        baseBody += "<td>$" + String.format("%.2f", totalFinal) + "</td>";
        baseBody += "</tr>\n";

    }

    /**
     * 
     * Uses the SQL result set to display the inventory in the report correctly
     * 
     * @param inventory
     * A result set of the inventory
     * @throws SQLException 
     */
    void fillInventoryTable(ResultSet inventory) throws SQLException {

        while (inventory.next()) {
            baseBody += "<tr>";

            //we will be making the basebody ourselves
            baseBody += "<td>" + inventory.getString(1) + "</td>";
            baseBody += "<td>$" + String.format("%.2f", inventory.getFloat(2)) + "</td>";
            baseBody += "<td>" + inventory.getString(3) + "</td>";
            baseBody += "<td>" + inventory.getInt(4) + "</td>";
            baseBody += "<td>" + inventory.getInt(5) + "</td>";

            //check if we need a restock
            if (inventory.getInt(5) == 0) {

                if (inventory.getInt(4) == 0) {

                    baseBody += "<td>Needs Restocked</td>";

                } else {

                    baseBody += "<td>No Restock</td>";

                }

            } else if (inventory.getInt(5) > 0) {

                if (inventory.getInt(4) < inventory.getInt(5)) {

                    baseBody += "<td>Needs Restocked</td>";

                } else {

                    baseBody += "<td>No Restock</td>";

                }

            }

//            for (int i = 1; i < md.getColumnCount() + 1; i++) {
//                baseBody += "<td>" + rs.getString(i) + "</td>";
//            }
            baseBody += "</tr>\n";
        }

    }

    /**
     * A method to fill the report table with correct information if the report is a sales report. It even does methods to display the
     * correct date and even give totals on a daily, weekly, monthly, and even yearly basis.
     * 
     * @param sales
     * A result set of the orders made
     * @param specific
     * True if for a specific customer, False if not.
     * @throws SQLException
     * @throws ParseException 
     */
    void salesReport(ResultSet sales, boolean specific) throws SQLException, ParseException {

        LocalDate currentDate = null;
        LocalDate oldDate = null;
        LocalDate startWeek = null;
        LocalDate startMonth = null;
        LocalDate startYear = null;

        //boolean to only edit start week and month when needed
        boolean startWeekEdited = false;
        boolean startMonthEdited = false;
        boolean startYearEdited = false;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        
        while (sales.next()) {
            
            //first thing we need to do is to get the date
            Date newDate = dateFormatter.parse(sales.getString(5));

            currentDate = LocalDate.parse(dateFormatter.format(newDate));
            
            System.out.println(sales.getString(5));
            System.out.println(currentDate.toString());

            if (!startWeekEdited) {

                startWeek = LocalDate.parse(dateFormatter.format(newDate));
                startWeekEdited = true;
            }

            if (!startMonthEdited) {

                startMonth = LocalDate.parse(dateFormatter.format(newDate));
                startMonthEdited = true;

            }
            if (!startYearEdited) {

                startYear = LocalDate.parse(dateFormatter.format(newDate));
                startYearEdited = true;

            }

            if (oldDate != null && currentDate.isAfter(oldDate)) {

                //when this is true we should run another query to get the daily earnings
                Float total = SQLManager.dailyCheck(oldDate, specific);

                baseBody += "<tr>";

                baseBody += "<td></td>";
                baseBody += "<td></td>";
                baseBody += "<td></td>";
                if (!specific) {
                    baseBody += "<td>Daily Earnings: </td>";
                } else {
                    baseBody += "<td>Daily Spending: </td>";
                }
                baseBody += "<td>$" + String.format("%.2f", total) + "</td>";

                baseBody += "</tr>\n";

                //Now we will check for weekly and monthly earnings from the starting point if possible
                if (startWeek.plusDays(6).equals(currentDate) || startWeek.plusDays(6).isBefore(currentDate)) {

                    //we now display earnings we got in a week from the starting point
                    Float weekTotal = SQLManager.weekCheck(startWeek, startWeek.plusDays(6), specific);

                    baseBody += "<tr>";

                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    if (!specific) {
                        baseBody += "<td>Weekly Earnings: </td>";
                    } else {
                        baseBody += "<td>Weekly Spending: </td>";
                    }
                    baseBody += "<td>$" + String.format("%.2f", weekTotal) + "</td>";

                    baseBody += "</tr>\n";

                    startWeek = currentDate.minusDays(currentDate.getDayOfWeek().getValue() - 1);

                }

                //Since month is depending on the month itself rather than the passing days we need to take year into account as well
                if (startMonth.getYear() == currentDate.getYear()) {

                    if (startMonth.getMonthValue() < currentDate.getMonthValue()) {

                        //show the monthly earnings
                        Float monthTotal = SQLManager.monthCheck(startMonth, startMonth.plusDays(startMonth.lengthOfMonth() - startMonth.getDayOfMonth()), specific);

                        baseBody += "<tr>";

                        baseBody += "<td></td>";
                        baseBody += "<td></td>";
                        baseBody += "<td></td>";
                        if (!specific) {
                            baseBody += "<td>Monthly Earnings: </td>";
                        } else {
                            baseBody += "<td>Monthly Spending: </td>";
                        }
                        baseBody += "<td>$" + String.format("%.2f", monthTotal) + "</td>";

                        baseBody += "</tr>\n";

                        startMonth = startMonth.plusDays(startMonth.lengthOfMonth() - startMonth.getDayOfMonth()).plusDays(1);

                    }

                } else if (startMonth.getYear() < currentDate.getYear()) {

                    //we are in a whole new year now
                    //we can reuse the top but we need to make sure the new starting month is in the next year after
                    Float monthTotal = SQLManager.monthCheck(startMonth, startMonth.plusDays(startMonth.lengthOfMonth() - startMonth.getDayOfMonth()), specific);

                    baseBody += "<tr>";

                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    if (!specific) {
                        baseBody += "<td>Monthly Earnings: </td>";
                    } else {
                        baseBody += "<td>Monthly Spending: </td>";
                    }
                    baseBody += "<td>$" + String.format("%.2f", monthTotal) + "</td>";

                    baseBody += "</tr>\n";

                    startMonth = currentDate.minusDays(currentDate.getDayOfMonth() - 1);
                }

                //Year should be super easy to do
                //all we need to do is check if its now in another year
                if (startYear.getYear() < currentDate.getYear()) {

                    Float yearTotal = SQLManager.yearCheck(startYear, startYear.plusDays(startYear.lengthOfYear() - startYear.getDayOfYear() - 1), specific);

                    baseBody += "<tr>";

                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    if (!specific) {
                        baseBody += "<td>Yearly Earnings: </td>";
                    } else {
                        baseBody += "<td>Yearly Spending: </td>";
                    }
                    baseBody += "<td>$" + String.format("%.2f", yearTotal) + "</td>";

                    baseBody += "</tr>\n";

                    startYear = currentDate.minusDays(currentDate.getDayOfYear() - 1);

                }

            }

            System.out.println("Row being made");
            baseBody += "<tr>";

            baseBody += "<td>" + sales.getString(1) + "</td>";
            baseBody += "<td>" + sales.getString(2) + "</td>";
            baseBody += "<td>" + sales.getInt(3) + "</td>";
            baseBody += "<td>$" + String.format("%.2f", sales.getFloat(4)) + "</td>";
            baseBody += "<td>" + sales.getString(5) + "</td>";

            baseBody += "</tr>\n";
            System.out.println("Row made");
            
            System.out.println(sales.getString(1));

            
            oldDate = currentDate;
            
            

        }
        
        if (oldDate != null && currentDate.isAfter(oldDate)) {

                    //when this is true we should run another query to get the daily earnings
                    Float total = SQLManager.dailyCheck(currentDate, specific);

                    baseBody += "<tr>";

                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    baseBody += "<td></td>";
                    if (!specific) {
                        baseBody += "<td>Daily Earnings: </td>";
                    } else {
                        baseBody += "<td>Daily Spending: </td>";
                    }
                    baseBody += "<td>$" + String.format("%.2f", total) + "</td>";

                    baseBody += "</tr>\n";

                    //Now we will check for weekly and monthlu earnings from the starting point if possible
                    if (startWeek.plusDays(6).equals(currentDate) || startWeek.plusDays(6).isBefore(currentDate)) {

                        //we now display earnings we got in a week from the starting point
                        Float weekTotal = SQLManager.weekCheck(startWeek, startWeek.plusDays(6), specific);

                        baseBody += "<tr>";

                        baseBody += "<td></td>";
                        baseBody += "<td></td>";
                        if (!specific) {
                            baseBody += "<td>Weekly Earnings: </td>";
                        } else {
                            baseBody += "<td>Weekly Spending: </td>";
                        }
                        baseBody += "<td>$" + String.format("%.2f", weekTotal) + "</td>";

                        baseBody += "</tr>\n";

                    }

                    if (startMonth.getYear() == currentDate.getYear()) {

                        if (startMonth.getMonthValue() < currentDate.getMonthValue()) {

                            //show the monthly earnings
                            Float monthTotal = SQLManager.monthCheck(startMonth, startMonth.plusDays(startMonth.lengthOfMonth() - startMonth.getDayOfMonth()), specific);

                            baseBody += "<tr>";

                            baseBody += "<td></td>";
                            baseBody += "<td></td>";
                            baseBody += "<td></td>";
                            if (!specific) {
                                baseBody += "<td>Monthly Earnings: </td>";
                            } else {
                                baseBody += "<td>Monthly Spending: </td>";
                            }
                            baseBody += "<td>$" + String.format("%.2f", monthTotal) + "</td>";

                            baseBody += "</tr>\n";

                            startMonth = startMonth.plusDays(startMonth.lengthOfMonth() - startMonth.getDayOfMonth()).plusDays(1);

                        }

                    } else if (startMonth.getYear() < currentDate.getYear()) {

                        //we are in a whole new year now
                        //we can reuse the top but we need to make sure the new starting month is in the next year after
                        Float monthTotal = SQLManager.monthCheck(startMonth, startMonth.plusDays(startMonth.lengthOfMonth() - startMonth.getDayOfMonth()), specific);

                        baseBody += "<tr>";

                        baseBody += "<td></td>";
                        baseBody += "<td></td>";
                        baseBody += "<td></td>";
                        if (!specific) {
                            baseBody += "<td>Monthly Earnings: </td>";
                        } else {
                            baseBody += "<td>Monthly Spending: </td>";
                        }
                        baseBody += "<td>$" + String.format("%.2f", monthTotal) + "</td>";

                        baseBody += "</tr>\n";

                        startMonth = currentDate.minusDays(currentDate.getDayOfMonth() - 1);
                    }

                    if (startYear.getYear() < currentDate.getYear()) {

                        Float yearTotal = SQLManager.yearCheck(startYear, startYear.plusDays(startYear.lengthOfYear() - startYear.getDayOfYear() - 1), specific);

                        baseBody += "<tr>";

                        baseBody += "<td></td>";
                        baseBody += "<td></td>";
                        baseBody += "<td></td>";
                        if (!specific) {
                                baseBody += "<td>Yearly Earnings: </td>";
                            } else {
                                baseBody += "<td>Yearly Spending: </td>";
                            }
                        baseBody += "<td>$" + String.format("%.2f", yearTotal) + "</td>";

                        baseBody += "</tr>\n";

                        startYear = currentDate.minusDays(currentDate.getDayOfYear() - 1);

                    }
        }
        
        

    }
    
    public void tradeReport(ArrayList<Game> games, ArrayList<Game> newGames, float amount) throws Exception{
    
        header = "<div class = TableTitle>" + "Trade-In Report" + "</div><table><tr>\n";
        
        header += "<th>Title</th>";
        header += "<th>Console</th>";
        header += "<th>Total Added</th>";
        
        
        //first thing we need to do is check if there is any games
        if(games.size() > 0){
        
            baseBody += "<tr>";
                baseBody += "<td></td>";
                baseBody += "<td> Added Games </td>";
                baseBody += "<td></td>";
                baseBody += "</tr>\n";
            
            //now we set the base body
            for(Game game: games){
            
                baseBody += "<tr>";
                baseBody += "<td>" + game.name +"</td>";
                baseBody += "<td>" + game.system +"</td>";
                baseBody += "<td>" + game.quantity +"</td>";
                baseBody += "</tr>\n";
            }
            
        }
        
        if(newGames.size() > 0){
        
            baseBody += "<tr>";
                baseBody += "<td></td>";
                baseBody += "<td> New Games </td>";
                baseBody += "<td></td>";
                baseBody += "</tr>\n";
            
            for(Game game: newGames){
            
                baseBody += "<tr>";
                baseBody += "<td>" + game.name +"</td>";
                baseBody += "<td>" + game.system +"</td>";
                baseBody += "<td>" + game.quantity +"</td>";
                baseBody += "</tr>\n";
            }
            
        }
        baseBody += "<tr>";
                baseBody += "<td></td>";
                baseBody += "<td>Amount Spent:</td>";
                baseBody += "<td>$"+ amount + "</td>";
                baseBody += "</tr>\n";
    
        //now we build the report
        buildReport();
        
    }

    /**
     * A method to build the report made no matter what report it is. It saves it to a file and opens it on the default browser of the user.
     * @throws Exception 
     */
    void buildReport() throws Exception {
        String time = Calendar.getInstance(Locale.getDefault()).getTime().toString();
        time = time.substring(3, 19);
        time = time.replace(" ", "");
        time = time.replace(":", "_");
        System.out.println("Time: " + time);
        String fileName = time + "report.html";
        String filePath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        File theDir = new File(filePath + "/UsedGames");

        //first we see if the directory exists
        if (!theDir.exists()) {

            //now create it
            theDir.mkdirs();

        }

        filePath = theDir + "/" + fileName;

        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        String finalReport = baseIntro + style + header + baseBody + baseClose;
        bw.write(finalReport);
        bw.close();
        System.out.println("Report saved ");

        //And then we open it in the browser
        File htmlFile = new File(filePath);
        Desktop.getDesktop().browse(htmlFile.toURI());

    }
}
