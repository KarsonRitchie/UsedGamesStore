/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gamestoreant;

//Importing login package
import Global.Lists;
import Login.LogonPage;

//Importing SQL classes we make
import SQL.GeneralSQL;
import SQL.SQLImages;
import SQL.SQLStore;

//import the main image placeholder class
//this is to take its image and upload it to the list so we have a default image
import Global.MainImagePlaceholder;
import Global.ThumbnailPlaceholder;
import java.awt.Image;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * This is the class that contains the main method of the used games program and will run upon boot up.
 * 
 * <br><br>
 * There is one method here and it is to check if the user is an admin. This is here so that if the program is made into an 
 * application, they are forced to be an admin so that sqlite will allow them to edit the database.
 */
public class GameStoreAnt {
    
    /**
     * Checks to see if the user is opening as admin
     * @return True if application was opened by admin. False if not
     */
    public static boolean isAdmin() {
        Preferences prefs = Preferences.systemRoot();
        PrintStream systemErr = System.err;
        synchronized (systemErr) {    // better synchronize to avoid problems with other threads that access System.err
            System.setErr(null);
            try {
                prefs.put("foo", "bar"); // SecurityException on Windows
                prefs.remove("foo");
                prefs.flush(); // BackingStoreException on Linux
                return true;
            } catch (Exception e) {
                return false;
            } finally {
                System.setErr(systemErr);
            }
        }
    }

    /**
     * The main method of the class that will start the program
     * @param args
     */
    public static void main(String[] args) {

        if (isAdmin()) {
            try {
                GeneralSQL.startConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameStoreAnt.class.getName()).log(Level.SEVERE, null, ex);
            }

            // TODO code application logic here
            //lets make sure we also get the defaul image for main images
            MainImagePlaceholder placeholder = new MainImagePlaceholder();
            ThumbnailPlaceholder thumbPlaceholder = new ThumbnailPlaceholder();

            ImageIcon tempImage = (ImageIcon) placeholder.mainImage.getIcon();
            ImageIcon tempThumbnail = (ImageIcon) thumbPlaceholder.thumbnail.getIcon();

            //now add it to the list
            Object[] placeholderImage = {0, 1, tempImage};
            Lists.images.add(placeholderImage);
            Object[] placeholderThumbnail = {0, 0, tempThumbnail};
            Lists.images.add(placeholderThumbnail);

            //First creat the pages needed
            //We will not need to make every page begause the main pages of each package will create the other pages
            LogonPage login = new LogonPage();

            //some sql to start off
            SQLStore.createFilterLists();
            Thread getImages = new Thread(() -> {
                SQLImages.loadImages();
            });

            getImages.start();

            //First setup the required panels and stuff to run
            //login.setup();
            //And once its done we now use it
            login.run();
        } else {
            //Check for environment and escalate privelegs
            JOptionPane.showMessageDialog(new JFrame(), "Due to issues with Sqlite conversion, SQLite version of the Used Games Program must be run as an adminstrator");
            System.exit(1);
        }

    }

}
