import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

public class mainFrame extends JFrame {
     private JTextArea  textArea;
     private Connection con;

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
                createDB();
            }
        });

        insve.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                insertVehicle();
            }
        });

        VIS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                SearchVehicleFrame sv=new SearchVehicleFrame(con);
            }
        });

        EBP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
            }
        });

        FC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);

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
                timeFrameWindow tf=new timeFrameWindow(con);
            }
        });

        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);
        checkDBConnection();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void insertVehicle() {
        try{
//            insVeFrame iv=new insVeFrame(con);
//INSERT OWNER
            String sql = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile) "
                    + "VALUES ('Xenofon','Kotsaris','Andrea_Papandreou_155_Ilion','2102622140','6980327900' )";

            String sql2 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile) "
                    + "VALUES('Vaggelis','Agorogiannis','Andrea_Kalvou_155_Zografou','2102622142','6980327903' )";

            String sql3 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile) "
                    + "VALUES('Eleni','Dimopoulou','Papandreou_143_Ilion','2102522140','6980443330' )";

            String sql4 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile) "
                    + "VALUES('Giannis','Kurillos','Andrea_133_Kifissia','2109922140','6983427900' )";
            Statement sttt = con.createStatement();
            sttt.executeUpdate(sql);
            sttt.executeUpdate(sql2);
            sttt.executeUpdate(sql3);
            sttt.executeUpdate(sql4);

               sql = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(1998,'Lancia','Ypsilon',null,null,null,'blue','ASD-1234',1 )";

              sql2 = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(2008,'Fiat','Seicento','2017-09-09','2018-01-01','Interamerican','blue','SDF-1234',2 )";
//
              sql3 = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(2009,'Ferrari','Murcielago','2018-09-09','2019-01-01','Interamerican','blue','QWE-1234',3 )";

              sql4 = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(2018,'Fiat','Tipo','2017-09-09','2019-01-01','Interamerican','yellow','ERT-1234',2 )";

           sttt.executeUpdate(sql);
           sttt.executeUpdate(sql2);
           sttt.executeUpdate(sql3);
           sttt.executeUpdate(sql4);

            sql=  "INSERT INTO VEHICLESINSURANCECHECK.fines( ownerId, Amount  , FineDate  ," +
                    " plate ) VALUES (1,200.2,'2018-11-26','ASD-1234' )";
            sttt.executeUpdate(sql);

         //   con.commit();

        textArea.setText("Inserted Sample Data");
        }catch(Exception e){
            textArea.setText(e.getMessage());
        }
    }

    private void createDB() {
        try{
            con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/VEHICLESINSURANCECHECK?autoReconnect=true&useSSL=false", "root", "root");
            Statement s=  con.createStatement();
            int Result=s.executeUpdate("CREATE DATABASE if not exists VEHICLESINSURANCECHECK");
           //creation of table owners
            checkDBConnection();
            PreparedStatement stmt2=con.prepareStatement("Create table if not exists owners(Name varchar(20) not null,LastName varchar(30) not null," +
                    "ownerId integer not null auto_increment,Address Varchar(60) not null,Telephone Varchar(30) not null,Mobile Varchar(30) not null, Primary Key(ownerId) );");
            //creation of table cars
            stmt2.execute();
            PreparedStatement stmt3=con.prepareStatement("Create table if not exists cars(year integer not null,Manufacturer varchar(30) not null," +
                    "Model Varchar(30) not null,RegistrationDate DATE  ,ExpirationDate DATE  ,InsuranceCompany Varchar(30) ," +
                    " Color varchar(20)not null," +
                    "Plate varchar(10)not null,ownerId integer not null, Primary Key(plate)," +
                    "foreign Key(ownerId) references owners(ownerId));");
            //creation of table fines
            stmt3.execute();
            PreparedStatement stmt4=con.prepareStatement("Create table if not exists fines(FineId integer not null auto_increment,ownerId integer not null," +
                    "Amount float not null, FineDate DATE  not null, plate varchar(10) not null, Primary Key (FineId), foreign Key(ownerId) references owners(ownerId)," +
                    "foreign Key (plate) references cars(plate));");
            stmt4.execute();
            textArea.setText("Db created");
        }catch(Exception e){
            System.out.println(e.getMessage());
             textArea.setText(e.getLocalizedMessage());
//                     "Db already exists");
        }
    }

    private void checkDBConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/VEHICLESINSURANCECHECK?autoReconnect=true&useSSL=false","root","root");
             //here VEHICLESINSURANCECHECK is database name, root is username and password
            textArea.setText("Connected to DB");
        }catch(Exception e){
            textArea.setText("Can't connect to DB. " +
                    "\n"+"Wrong credentials or must create new DB");
        }
    }
}
