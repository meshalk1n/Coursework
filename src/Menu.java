import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu  extends JDialog{

    private JPanel panel1;
    private JButton historyButton;
    private JButton apartmentDatabaseButton;
    private JButton button3;
    private JButton exitButton;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    public Menu() {
        png();
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

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database_Of_Houses databaseOfHouses = new Database_Of_Houses();
                databaseOfHouses.setVisible(true);
                dispose();
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistorySelection historySelection = new HistorySelection();
                historySelection.setVisible(true);
                dispose();
            }
        });
        apartmentDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApartmentDatabase apartmentDatabase = new ApartmentDatabase();
                apartmentDatabase.setVisible(true);
                dispose();
            }
        });
    }
    void png(){
        ImageIcon imageIcon = new ImageIcon("src/pngMenu/exit-door.png");
        label1.setIcon(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("src/pngMenu/agreement.png");
        label2.setIcon(imageIcon1);
        ImageIcon imageIcon2 = new ImageIcon("src/pngMenu/server.png");
        label3.setIcon(imageIcon2);
        ImageIcon imageIcon3 = new ImageIcon("src/pngMenu/apartment.png");
        label4.setIcon(imageIcon3);
        ImageIcon imageIcon4 = new ImageIcon("src/pngMenu/history.png");
        label5.setIcon(imageIcon4);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        /*
        JFrame frame = new JFrame("");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 5);
        frame.setLocation(x, y);

        frame.setContentPane(new Menu().panel1);
        frame.setMinimumSize(new Dimension(500,474));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

         */
    }
}
