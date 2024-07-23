package SQL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Global.Lists;
import SQL.GeneralSQL;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karson
 * 
 * A class for methods relating to SQL that concern images
 */
public class SQLImages extends GeneralSQL {

    //this is for messing with images
    //static Blob imgBlob = null;
    static byte[] imgBlob = null;

    /**
     * Uploads image to the database
     * 
     * @param gameID
     * The ID of the game in the database
     * 
     * @param imageType
     * The image type (0 for thumbnail, 1 for main image)
     * 
     * @param b
     * The byte array
     * 
     * @return True if image was uploaded successfully. False if not.
     */
    public static boolean uploadImage(int gameID, int imageType, byte[] b) {

        try {
            
            GeneralSQL.getConnection();
            //First we need to connect to the database
            //con = DriverManager.getConnection(mySQLURL, userName, password);

            //then we should do is check if an image is already in the place we are uploading
            //if it is we will rewrite it
            PreparedStatement ps = con.prepareStatement("SELECT GameID, ImageType FROM Images WHERE GameID = " + gameID + " AND ImageType = " + imageType);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getInt(1) == gameID && rs.getInt(2) == imageType) {

                    ps = con.prepareStatement("UPDATE Images SET Image = ? WHERE GameID = " + gameID + " AND ImageType = " + imageType + ";");
                    ps.setBytes(1, b);

                    ps.executeUpdate();

                    System.out.println("updating image");
                }
                
                //Close connection
                con.close();
                
                return true;
            } else {
                
                //run a little query to get the previous id
                ps = con.prepareStatement("SELECT ImageID, ImageType FROM Images");
                rs = ps.executeQuery();
                
                int tempID = 0;
                
                while(rs.next()){
                
                    tempID = rs.getInt(1);
                
                }
                
                System.out.println(b);

                ps = con.prepareStatement("insert into Images VALUES(" + (tempID + 1) + ", " + gameID
                        + ", " + imageType + ", ?);");
                ps.setBytes(1, b);

                ps.executeUpdate();

                System.out.println("Saving image");
                
                //Close connection
                con.close();
                
                return true;
            }

            //con.close();
        } catch (Exception ex) {

            System.out.println("Can not save image to database");
            ex.printStackTrace();
            return false;
        }

    }

    /**
     * This is to load the images from the database
     */
    public static void loadImages() {

        //here we will load images from a database to a dictionary
        try {
            
            //Connect to the database first
            GeneralSQL.getConnection();

            //Retrieve images
            PreparedStatement ps = con.prepareStatement("SELECT GameID, ImageType, Image FROM Images");
            ResultSet rs = ps.executeQuery();

            //Then we go through the result set in order to upload each image to a dictionary in the program
            while (rs.next()) {

                imgBlob = rs.getBytes(3);

                //allso make an array
                Object[] imageInfo = {rs.getInt(1), rs.getInt(2), imgBlob};

                //now upload it to the what is basically acting as a dictionary
                Lists.images.add(imageInfo);

            }

            //Close connection
            con.close();
        } catch (Exception ex) {

            System.out.println("Can not load images from database");
            ex.printStackTrace();
            
        }

    }

    /**
     * A method to fetch a more specific image
     * 
     * @param gameID
     * ID of the game associated with the image
     * 
     * @param imageType
     * The image type (0 for thumbnail, 1 for main image)
     * @return 
     */
    public static byte[] retrieveImage(int gameID, int imageType) {

        PreparedStatement ps;
        
        try {
            
            //Connect to database
            GeneralSQL.getConnection();
            
            //retrieve specified image
            ps = con.prepareStatement("SELECT Image FROM Images WHERE GameID = " + gameID + " AND ImageType = " + imageType);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            byte[] b = rs.getBytes(1);
            
            //Close connection
            con.close();
            
            return b;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        

    }

}
