// Importing the libraries
import gui.MainWindow;
import javax.swing.*;
import java.awt.*;

// Creating the main class
public class Main {
    // creating the main method
    public static void main(String[] args) {
        try { // exception handling try block
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        } // exception handling catch block

        JFrame j = new JFrame();
        j.setTitle("Dijkstra Algorithm");

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(new Dimension(900, 600));
        j.add(new MainWindow());
        j.setVisible(true);

    }

}
