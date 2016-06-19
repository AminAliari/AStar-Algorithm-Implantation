import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {

    private int currentNum;

    public Button(String s, int i, int j) {
        super(s);
        currentNum = 0;
        Frame.map[i][j] = 0;
        setBorder(null);
        setFocusable(false);
        setOpaque(true);
        setBorderPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentNum < 7) {
                    currentNum++;
                    Frame.map[i][j] = currentNum;
                    setBackground(Frame.colors[currentNum]);
                    setText("[" + currentNum + "]");
                } else {
                    currentNum = 0;
                    Frame.map[i][j] = 0;
                    setText("[0]");
                    setBackground(null);
                }
            }
        });
    }
}