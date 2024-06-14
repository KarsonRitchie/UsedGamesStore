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
 * @author karso
 */
public class SQLImages extends GeneralSQL {

    //this is for messing with images
    //static Blob imgBlob = null;
    static byte[] imgBlob = null;

    //first we want to do an upload feature
    public static boolean uploadImage(int gameID, int imageType, byte[] b) {

        try {
            
            GeneralSQL.getConnection();
            //First we need to connect to the database
            //con = DriverManager.getConnection(mySQLURL, userName, password);

            //the first thing we should do is check if an image is already in the place we are uploading
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
                
                return true;
            }

            //con.close();
        } catch (Exception ex) {

            System.out.println("Can not save image to database");
            ex.printStackTrace();
            return false;
        }

    }

    public static void loadImages() {

        //here we will load images from a database to a dictionary
        try {
            
            GeneralSQL.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT GameID, ImageType, Image FROM Images");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                imgBlob = rs.getBytes(3);

                //allso make an array
                Object[] imageInfo = {rs.getInt(1), rs.getInt(2), imgBlob};

                //now upload it to the what is basically acting as a dictionary
                Lists.images.add(imageInfo);

            }

            //con.close();
        } catch (Exception ex) {

            System.out.println("Can not load images from database");
            ex.printStackTrace();
        }

    }

    public static byte[] retrieveImage(int gameID, int imageType) {

        PreparedStatement ps;
        
        try {
            
            GeneralSQL.getConnection();
            
            ps = con.prepareStatement("SELECT Image FROM Images WHERE GameID = " + gameID + " AND ImageType = " + imageType);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBytes(1);
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        

    }

}
