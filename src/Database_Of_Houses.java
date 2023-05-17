import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Database_Of_Houses extends JDialog{
    private static final String URL = "jdbc:mysql://localhost:3306/mytrialdatabasesql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mySQLMikhai_D1234";
    private JPanel panel1;
    private JTable table1;
    private JButton backButton;
    private JButton addButton;
    private JLabel label1;
    private JLabel label2;
    private JButton deleteButton;
    private JButton updateButton;
    private JLabel label3;
    private JLabel label4;
    private JTextField textUlica;
    private JTextField textNomerDom;
    private JTextField textPloshchDom;
    private JTextField textKolKomnat;
    private JTextField textTcena;
    private JTextField textId;
    private JTextField textSearch;
    private JComboBox comboBoxSearch;
    private JButton tableButton;
    private JLabel label6;
    private JLabel label5;
    private JButton requestButton;
    private JLabel label7;
    private JButton historyButton;
    private JLabel label8;

    Connection connection;
    PreparedStatement prepareStatement;
    public Database_Of_Houses(){
        png();
        Connect();
        table_load();
        setTitle("");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - getHeight()) / 5);
        setLocation(x, y);
        setContentPane(panel1);
        setMinimumSize(new Dimension(500,474));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prepareStatement = connection.prepareStatement(
                            "insert into dom(ulica,nomer_dom,ploshch_dom,kol_komnat,tcena)values(?,?,?,?,?)");
                    prepareStatement.setString(1, textUlica.getText());
                    prepareStatement.setString(2, textNomerDom.getText());
                    prepareStatement.setString(3, textPloshchDom.getText());
                    prepareStatement.setString(4, textPloshchDom.getText());
                    prepareStatement.setString(5, textTcena.getText());
                    prepareStatement.execute();
                    JOptionPane.showMessageDialog(null,"Record Addedddddd!!!!");
                    table_load();
                    prepareStatement.close();
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(
                        null,"are you sure you want to delete this data?","Delete",
                        JOptionPane.YES_NO_OPTION);
                if(action == 0) {
                    try {
                        String query = ("delete from dom  where id = '" + textId.getText() + "' ");
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.execute();
                        JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                        preparedStatement.close();
                        table_load();
                    } catch (SQLException e1) {

                        e1.printStackTrace();
                    }
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    prepareStatement = connection.prepareStatement(
                            "update dom set ulica = ?,nomer_dom = ?,ploshch_dom = ?,kol_komnat = ?,tcena = ? where id = ?");
                    prepareStatement.setString(1, textUlica.getText());
                    prepareStatement.setString(2, textNomerDom.getText());
                    prepareStatement.setString(3, textPloshchDom.getText());
                    prepareStatement.setString(4, textPloshchDom.getText());
                    prepareStatement.setString(5, textTcena.getText());
                    prepareStatement.setString(6, textId.getText());
                    prepareStatement.execute();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    prepareStatement.close();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               try {
                   int row = table1.getSelectedRow();
                   String ID_=(table1.getModel().getValueAt(row,0)).toString();
                   String query = "select * from dom where id = '" + ID_ + " ' ";
                   PreparedStatement preparedStatement = connection.prepareStatement(query);
                   ResultSet resultSet1 = preparedStatement.executeQuery();
                   while(resultSet1.next()){
                       textId.setText(resultSet1.getString("id"));
                       textUlica.setText(resultSet1.getString("ulica"));
                       textNomerDom.setText(resultSet1.getString("nomer_dom"));
                       textPloshchDom.setText(resultSet1.getString("ploshch_dom"));
                       textKolKomnat.setText(resultSet1.getString("kol_komnat"));
                       textTcena.setText(resultSet1.getString("tcena"));
                   }
                   preparedStatement.close();
               }catch (Exception exception){
                   exception.printStackTrace();
               }
            }
        });
        textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String selection = (String)comboBoxSearch.getSelectedItem();
                    String query =
                            "select id,ulica,nomer_dom,ploshch_dom,kol_komnat,tcena from dom where "+selection+" = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,textSearch.getText());
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(resultSet1));
                    preparedStatement.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_load();
            }
        });
    }
    void png(){
        ImageIcon imageIcon = new ImageIcon("src/pngDatabaseOfHouses/add-user.png");
        label1.setIcon(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("src/pngDatabaseOfHouses/back-button.png");
        label2.setIcon(imageIcon1);
        ImageIcon imageIcon2 = new ImageIcon("src/pngDatabaseOfHouses/delete-user.png");
        label3.setIcon(imageIcon2);
        ImageIcon imageIcon3 = new ImageIcon("src/pngDatabaseOfHouses/refresh.png");
        label4.setIcon(imageIcon3);
        ImageIcon imageIcon4 = new ImageIcon("src/pngDatabaseOfHouses/search.png");
        label5.setIcon(imageIcon4);
        ImageIcon imageIcon5 = new ImageIcon("src/pngDatabaseOfHouses/refresh.png");
        label6.setIcon(imageIcon5);
        ImageIcon imageIcon6 = new ImageIcon("src/pngDatabaseOfHouses/timeline.png");
        label7.setIcon(imageIcon6);
        ImageIcon imageIcon7 = new ImageIcon("src/pngDatabaseOfHouses/file.png");
        label8.setIcon(imageIcon7);
    }
    void table_load()
    {
        try
        {
            prepareStatement = connection.prepareStatement("select * from dom");
            ResultSet resultSet = prepareStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(resultSet));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("соединение успешно");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}