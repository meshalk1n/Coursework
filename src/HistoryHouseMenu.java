import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryHouseMenu extends JDialog {
    private JPanel panel1;
    private JButton soldButton;
    private JButton inProgressButton;
    private JButton backButton;
    private JLabel label1;

    public HistoryHouseMenu(){
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
                HistorySelection historySelection = new HistorySelection();
                historySelection.setVisible(true);
                dispose();
            }
        });
        inProgressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryHouseMenuInProgress historyHouseMenuInProgress = new HistoryHouseMenuInProgress();
                historyHouseMenuInProgress.setVisible(true);
                dispose();
            }
        });
        soldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryHouseMenuSold historyHouseMenuSold = new HistoryHouseMenuSold();
                historyHouseMenuSold.setVisible(true);
                dispose();
            }
        });
    }
    void png(){
        ImageIcon imageIcon = new ImageIcon("src/pngHistoryHouseMenu/big-data.png");
        label1.setIcon(imageIcon);
    }
}










