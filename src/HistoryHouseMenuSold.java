import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HistoryHouseMenuSold extends JDialog {
    private static final String URL = "jdbc:mysql://localhost:3306/mytrialdatabasesql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mySQLMikhai_D1234";
    private JPanel panel1;
    private JTable table1;
    private JButton returnButton;
    private JButton tableButton;
    private JButton backButton;
    private JComboBox comboBoxSearch;
    private JTextField textSearch;
    private JTextField textTcena;
    private JTextField textKolKomnat;
    private JTextField textPloshchDom;
    private JTextField textNomerDom;
    private JTextField textUlica;
    private JTextField textId;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    Connection connection;
    PreparedStatement prepareStatement;
    public HistoryHouseMenuSold(){
        png();
        Connect();
        table_load();
        setTitle("");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 3);
        int y = (int) ((dimension.getHeight() - getHeight()) / 5);
        setLocation(x, y);
        setContentPane(panel1);
        setMinimumSize(new Dimension(500,474));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_load();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryHouseMenu historyHouseMenu = new HistoryHouseMenu();
                historyHouseMenu.setVisible(true);
                dispose();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int row = table1.getSelectedRow();
                    String ID_=(table1.getModel().getValueAt(row,0)).toString();
                    String query = "select * from dom_sold where id = '" + ID_ + " ' ";
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
                            "select id,ulica,nomer_dom,ploshch_dom,kol_komnat,tcena from dom_sold where "+selection+" = ?";
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
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(
                        null,"вы уверены?","return",
                        JOptionPane.YES_NO_OPTION);
                if(action == 0) {
                    try {
                        prepareStatement = connection.prepareStatement(
                                "insert into dom_in_progress(ulica,nomer_dom,ploshch_dom,kol_komnat,tcena)values(?,?,?,?,?)");
                        prepareStatement.setString(1, textUlica.getText());
                        prepareStatement.setString(2, textNomerDom.getText());
                        prepareStatement.setString(3, textPloshchDom.getText());
                        prepareStatement.setString(4, textKolKomnat.getText());
                        prepareStatement.setString(5, textTcena.getText());
                        prepareStatement.execute();
                        table_load();
                        prepareStatement.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        String query = ("delete from dom_sold  where id = '" + textId.getText() + "' ");
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
    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("соединение успешно");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void table_load()
    {
        try
        {
            prepareStatement = connection.prepareStatement("select * from dom_sold");
            ResultSet resultSet = prepareStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(resultSet));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    void png()
    {
        ImageIcon imageIcon = new ImageIcon("src/pngHistoryHouseMenuSold/back-button.png");
        label1.setIcon(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("src/pngHistoryHouseMenuSold/refresh.png");
        label2.setIcon(imageIcon1);
        ImageIcon imageIcon2 = new ImageIcon("src/pngHistoryHouseMenuSold/back-button.png");
        label3.setIcon(imageIcon2);
        ImageIcon imageIcon3 = new ImageIcon("src/pngHistoryHouseMenuSold/search.png");
        label4.setIcon(imageIcon3);
    }

}





















