import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistorySelection extends JDialog {
    private JPanel panel1;
    private JButton apartmentHistoryButton;
    private JButton historyOfHousesButton;
    private JButton backButton;
    private JLabel label1;

    public HistorySelection(){
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });
        historyOfHousesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryHouseMenu historyHouseMenu = new HistoryHouseMenu();
                historyHouseMenu.setVisible(true);
                dispose();
            }
        });
    }
    void png(){
        ImageIcon imageIcon = new ImageIcon("src/pngHistorySelection/history.png");
        label1.setIcon(imageIcon);
    }

}
