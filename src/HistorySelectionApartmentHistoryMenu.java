import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistorySelectionApartmentHistoryMenu extends JDialog {

    private JPanel panel1;
    private JButton inProgressButton;
    private JButton soldButton;
    private JButton backButton;
    private JLabel label1;

    public HistorySelectionApartmentHistoryMenu(){
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
                HistorySelectionApartmentHistoryMenuInProgress historySelectionApartmentHistoryMenuInProgress =
                        new HistorySelectionApartmentHistoryMenuInProgress();
                historySelectionApartmentHistoryMenuInProgress.setVisible(true);
                dispose();
            }
        });
        soldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistorySelectionApartmentHistoryMenuSold historySelectionApartmentHistoryMenuSold =
                        new HistorySelectionApartmentHistoryMenuSold();
                historySelectionApartmentHistoryMenuSold.setVisible(true);
                dispose();
            }
        });
    }
    void png(){
        ImageIcon imageIcon = new ImageIcon("src/pngHistorySelectionApartmentHistoryMenu/big-data.png");
        label1.setIcon(imageIcon);
    }
}
