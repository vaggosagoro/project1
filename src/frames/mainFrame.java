package frames;

import domain.Car;
import exception.DriverNotFoundException;
import databaseHelper.DatabaseInitializer;
import service.CarService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class mainFrame extends JFrame {
     private JTextArea  textArea;
     private CarService carService = new CarService();
     private DatabaseInitializer databaseInitializer = new DatabaseInitializer();

    public mainFrame(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        JMenuBar mb=new JMenuBar();
        JMenu menu=new JMenu("Menu");
        JMenuItem cdb=new JMenuItem("Create Database");
        JMenuItem insve=new JMenuItem("Insert Sample Data");
        JMenuItem VIS=new JMenuItem("Vehicle Insurance Status");
        JMenuItem EBP=new JMenuItem("Expiries by plate");
        JMenuItem FC=new JMenuItem("Fine Calculation");
        menu.add(cdb);
        menu.add(insve);
        menu.add(VIS);
        JMenu submenu=new JMenu("Forecoming Expiries");
        JMenuItem FILE=new JMenuItem("File");
        JMenuItem HERE=new JMenuItem("Here");
        menu.add(submenu);
        menu.add(EBP);
        menu.add(FC);
        submenu.add(FILE);
        submenu.add(HERE);
        mb.add(menu);
        setJMenuBar(mb);
        textArea = new JTextArea(5, 20);

        cdb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                try{
                    databaseInitializer.initializeDataBase();
                } catch(DriverNotFoundException ex){
                    showMessage("Driver Error", ex.getMessage());
                } catch (SQLException ex) {
                    showMessage("SQL Error", ex.getMessage());
                }
            }
        });

        insve.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                try {
                    databaseInitializer.importSampleData();
                    textArea.setText("Inserted Sample Data");
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }
            }
        });

        VIS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                SearchVehicleFrame sv=new SearchVehicleFrame();
            }
        });

        EBP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                textArea.setText(null);
                try {
                    Map<String, Car> cars = carService.getExpiredInsurancedCarsSortedByLicencePlate();
                    if (cars.isEmpty()) {
                        showMessage("Insurance status", "No uninsured vehicles in DB");
                    } else {
                    textArea.append(cars.entrySet().toString());}
                } catch (SQLException ex) {
                    showMessage("Error", ex.getMessage());
                }
            }
        });

        FC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                FineCalculationFrame finecal = new FineCalculationFrame();

            }
        });

        FILE.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);

            }
        });

        HERE.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                timeFrameWindow tf=new timeFrameWindow();
            }
        });

        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);
        if (databaseInitializer.isConnectionPossible()) {
            textArea.setText("Connected to DB");

        } else {
            textArea.setText("Can't connect to DB. Wrong credentials or must create new DB");
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showMessage(String title, String msg) {
        final JPanel panel = new JPanel();
        JOptionPane.showMessageDialog(panel, msg , title,
                JOptionPane.INFORMATION_MESSAGE);
    }
}

