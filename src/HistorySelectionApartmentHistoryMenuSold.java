import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HistorySelectionApartmentHistoryMenuSold extends JDialog {
    private static final String URL = "jdbc:mysql://localhost:3306/mytrialdatabasesql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mySQLMikhai_D1234";
    private JPanel panel1;
    private JTable table1;
    private JButton returnButton;
    private JButton tableButton;
    private JButton backButton;
    private JTextField textUlica;
    private JTextField textNomerKvart;
    private JTextField textPloshchKvart;
    private JTextField textKolKomnat;
    private JTextField textTcena;
    private JTextField textId;
    private JComboBox comboBoxSearch;
    private JTextField textSearch;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    Connection connection;
    PreparedStatement prepareStatement;
    public HistorySelectionApartmentHistoryMenuSold(){
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistorySelectionApartmentHistoryMenu historySelectionApartmentHistoryMenu =
                        new HistorySelectionApartmentHistoryMenu();
                historySelectionApartmentHistoryMenu.setVisible(true);
                dispose();
            }
        });
        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_load();
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
                        String query = ("delete from kvart_sold  where id = '" + textId.getText() + "' ");
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
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int row = table1.getSelectedRow();
                    String ID_=(table1.getModel().getValueAt(row,0)).toString();
                    String query = "select * from kvart_sold where id = '" + ID_ + " ' ";
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
                            "select id,ulica,nomer_kvart,ploshch_kvart,kol_komnat,tcena from kvart_sold where "+selection+" = ?";
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
    }
    void table_load()
    {
        try
        {
            prepareStatement = connection.prepareStatement("select * from kvart_sold");
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
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void png()
    {
        ImageIcon imageIcon = new ImageIcon("src/pngHistorySelectionApartmentHistoryMenuSold/back-button.png");
        label1.setIcon(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("src/pngHistorySelectionApartmentHistoryMenuSold/refresh.png");
        label2.setIcon(imageIcon1);
        ImageIcon imageIcon2 = new ImageIcon("src/pngHistorySelectionApartmentHistoryMenuSold/back-button.png");
        label3.setIcon(imageIcon2);
        ImageIcon imageIcon3 = new ImageIcon("src/pngHistorySelectionApartmentHistoryMenuSold/search.png");
        label4.setIcon(imageIcon3);
    }
}















