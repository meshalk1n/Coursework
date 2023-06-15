import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ApartmentDatabase extends JDialog {
    private static final String URL = "jdbc:mysql://localhost:3306/mytrialdatabasesql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mySQLMikhai_D1234";
    private JPanel panel1;
    private JTable table1;
    private JTextField textSearch;
    private JComboBox comboBoxSearch;
    private JButton historyButton;
    private JButton backButton;
    private JButton tableButton;
    private JButton requestButton;
    private JButton deteleButton;
    private JButton updateButton;
    private JButton addButton;
    private JButton textButton;
    private JTextField textTcena;
    private JTextField textKolKomnat;
    private JTextField textPloshchKvart;
    private JTextField textNomerKvart;
    private JTextField textId;
    private JTextField textUlica;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    Connection connection;
    PreparedStatement prepareStatement;
    public ApartmentDatabase(){
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prepareStatement = connection.prepareStatement(
                            "insert into kvart(ulica,nomer_kvart,ploshch_kvart,kol_komnat,tcena)values(?,?,?,?,?)");
                    prepareStatement.setString(1, textUlica.getText());
                    prepareStatement.setString(2, textNomerKvart.getText());
                    prepareStatement.setString(3, textPloshchKvart.getText());
                    prepareStatement.setString(4, textKolKomnat.getText());
                    prepareStatement.setString(5, textTcena.getText());
                    prepareStatement.execute();
                    JOptionPane.showMessageDialog(null,"успешно");
                    table_load();
                    prepareStatement.close();
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textId.setText("");
                textUlica.setText("");
                textNomerKvart.setText("");
                textPloshchKvart.setText("");
                textKolKomnat.setText("");
                textTcena.setText("");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    prepareStatement = connection.prepareStatement(
                            "update kvart set ulica = ?,nomer_kvart = ?,ploshch_kvart = ?,kol_komnat = ?,tcena = ? where id = ?");
                    prepareStatement.setString(1, textUlica.getText());
                    prepareStatement.setString(2, textNomerKvart.getText());
                    prepareStatement.setString(3, textPloshchKvart.getText());
                    prepareStatement.setString(4, textKolKomnat.getText());
                    prepareStatement.setString(5, textTcena.getText());
                    prepareStatement.setString(6, textId.getText());
                    prepareStatement.execute();
                    JOptionPane.showMessageDialog(null, "успешно");
                    table_load();
                    prepareStatement.close();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
        deteleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(
                        null,"вы уверены?","Delete",
                        JOptionPane.YES_NO_OPTION);
                if(action == 0) {
                    try {
                        String query = ("delete from kvart  where id = '" + textId.getText() + "' ");
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.execute();
                        JOptionPane.showMessageDialog(null, "успешно");
                        preparedStatement.close();
                        table_load();
                    } catch (SQLException e1) {

                        e1.printStackTrace();
                    }
                }
            }
        });
        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_load();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int row = table1.getSelectedRow();
                    String ID_=(table1.getModel().getValueAt(row,0)).toString();
                    String query = "select * from kvart where id = '" + ID_ + " ' ";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    while(resultSet1.next()){
                        textId.setText(resultSet1.getString("id"));
                        textUlica.setText(resultSet1.getString("ulica"));
                        textNomerKvart.setText(resultSet1.getString("nomer_kvart"));
                        textPloshchKvart.setText(resultSet1.getString("ploshch_kvart"));
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
                            "select id,ulica,nomer_kvart,ploshch_kvart,kol_komnat,tcena from kvart where "+selection+" = ?";
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
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistorySelectionApartmentHistoryMenu historySelectionApartmentHistoryMenu =
                        new HistorySelectionApartmentHistoryMenu();
                historySelectionApartmentHistoryMenu.setVisible(true);
            }
        });
        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(
                        null,"вы уверены?","request",
                        JOptionPane.YES_NO_OPTION);
                if(action == 0) {
                    try {
                        prepareStatement = connection.prepareStatement(
                                "insert into kvart_in_progress(ulica,nomer_kvart,ploshch_kvart,kol_komnat,tcena)values(?,?,?,?,?)");
                        prepareStatement.setString(1, textUlica.getText());
                        prepareStatement.setString(2, textNomerKvart.getText());
                        prepareStatement.setString(3, textPloshchKvart.getText());
                        prepareStatement.setString(4, textKolKomnat.getText());
                        prepareStatement.setString(5, textTcena.getText());
                        prepareStatement.execute();
                        table_load();
                        prepareStatement.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        String query = ("delete from kvart  where id = '" + textId.getText() + "' ");
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.execute();
                        JOptionPane.showMessageDialog(null, "успешно");
                        preparedStatement.close();
                        table_load();
                    } catch (SQLException e1) {

                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    void png()
    {
        ImageIcon imageIcon = new ImageIcon("src/pngApartmentDatabase/refresh.png");
        label1.setIcon(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("src/pngApartmentDatabase/add-user.png");
        label2.setIcon(imageIcon1);
        ImageIcon imageIcon2 = new ImageIcon("src/pngApartmentDatabase/refresh.png");
        label3.setIcon(imageIcon2);
        ImageIcon imageIcon3 = new ImageIcon("src/pngApartmentDatabase/delete-user.png");
        label4.setIcon(imageIcon3);
        ImageIcon imageIcon4 = new ImageIcon("src/pngApartmentDatabase/timeline.png");
        label5.setIcon(imageIcon4);
        ImageIcon imageIcon5 = new ImageIcon("src/pngApartmentDatabase/file.png");
        label6.setIcon(imageIcon5);
        ImageIcon imageIcon6 = new ImageIcon("src/pngApartmentDatabase/refresh.png");
        label7.setIcon(imageIcon6);
        ImageIcon imageIcon7 = new ImageIcon("src/pngApartmentDatabase/back-button.png");
        label8.setIcon(imageIcon7);
        ImageIcon imageIcon8 = new ImageIcon("src/pngApartmentDatabase/search.png");
        label9.setIcon(imageIcon8);
    }
    void table_load()
    {
        try
        {
            prepareStatement = connection.prepareStatement("select * from kvart");
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
}
