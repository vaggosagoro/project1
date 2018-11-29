import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;
import java.sql.Date;
import java.util.*;

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
                TreeMap <String,car> uninsuredCars=new TreeMap<>();
                uninsuredCars=expiriesByPlate();
                textArea.append(uninsuredCars.entrySet().toString());

            }
        });

        FC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                FineCalculationFrame finecal = new FineCalculationFrame(con);

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

    private TreeMap<String,car> expiriesByPlate() {
        TreeMap<String,car> uninsuredCars = new TreeMap<>();

        Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
        String sql= "SELECT * FROM VEHICLESINSURANCECHECK.cars  WHERE ExpirationDate <'"+sqldate+"' or ExpirationDate IS NULL" ;
        Date expD8 =null,   regD8=null;

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
             if(!rs.next()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "No uninsured vehicles in DB" , "Insurance status",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                do {
                    int year=rs.getInt(1);
                    int ownerID=rs.getInt(9);
                    String color=rs.getString(7);
                    String manufacturer=rs.getString(2);
                    String Model=rs.getString(3);
                    String platte=  rs.getString(8);
                    String insco = rs.getString(6);
                    expD8 = rs.getDate(5);
                    regD8 = rs.getDate(4);
                    car carTemp=new car(year,manufacturer,Model,regD8,expD8,insco,color,platte,ownerID) ;
                    uninsuredCars.put(carTemp.getPlate(),carTemp);

                 }while(rs.next());


            }
        }  catch(Exception e){
             final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, e.getMessage() , "error!!",
                    JOptionPane.INFORMATION_MESSAGE);
        } return uninsuredCars;
    }

    private void insertVehicle() {
        try{
        //INSERT OWNER
            String sql = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES ('Xenofon','Kotsaris','Andrea_Papandreou_155_Ilion','2102622140','6980327900', '12345678' )";

            String sql2 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES('Vaggelis','Agorogiannis','Andrea_Kalvou_155_Zografou','2102622142','6980327903','91375846' )";

            String sql3 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES('Eleni','Dimopoulou','Papandreou_143_Ilion','2102522140','6980443330','74103265' )";

            String sql4 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES('Giannis','Kurillos','Andrea_133_Kifissia','2109922140','6983427900','96587412' )";
            Statement sttt = con.createStatement();
            sttt.executeUpdate(sql);
            sttt.executeUpdate(sql2);
            sttt.executeUpdate(sql3);
            sttt.executeUpdate(sql4);

            sql = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(1998,'Lancia','Ypsilon','2017-08-09','2018-01-31','Interamerican','blue','ASD-1234',1 )";

            sql2 = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(2008,'Fiat','Seicento','2017-09-09','2018-01-01','Interamerican','blue','SDF-1234',2 )";

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
                    " plate ) VALUES (1, null,'2018-11-26','ASD-1234' )";
            sttt.executeUpdate(sql);

            textArea.setText("Inserted Sample Data");
        }catch(Exception e){
            textArea.setText(e.getMessage());
        }
    }

    private void createDB() {
        try{
            con = DriverManager.getConnection ("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root", "root");
            Statement s=  con.createStatement();
            int Result=s.executeUpdate("CREATE DATABASE if not exists VEHICLESINSURANCECHECK");
            //creation of table owners
            checkDBConnection();
            PreparedStatement stmt2=con.prepareStatement("Create table if not exists owners(Name varchar(20) not null,LastName varchar(30) not null," +
                    "ownerId integer not null auto_increment,Address Varchar(60) not null,Telephone Varchar(30) not null,Mobile Varchar(30) not null, AFM Varchar(30) Unique not null, Primary Key(ownerId) );");
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
                    "Amount float null, FineDate DATE  not null, plate varchar(10) not null, Primary Key (FineId), foreign Key(ownerId) references owners(ownerId)," +
                    "foreign Key (plate) references cars(plate));");
            stmt4.execute();
            textArea.setText("Db created");
        }catch(Exception e){
            System.out.println(e.getMessage());
            textArea.setText(e.getLocalizedMessage());
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

