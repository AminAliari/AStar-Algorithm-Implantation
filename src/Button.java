import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {

    private int currentNum;
    private boolean block;

    public Button(String s, int i, int j) {
        super(s);
        currentNum = 0;
        block = false;

        Frame.map[i][j] = 0;
        setBorder(null);
        setFocusable(false);
        setOpaque(true);
        setBorderPainted(false);

        addActionListener(e -> {
            if (currentNum < Frame.max) {
                block = true;
                currentNum++;
                Frame.map[i][j] = currentNum;
                setBackground(Frame.colors[currentNum]);
                setText("[" + currentNum + "]");
            } else {
                block = false;
                currentNum = 0;
                Frame.map[i][j] = 0;
                setText("[0]");
                setBackground(null);
            }
            Frame.isClean = true;
        });
    }

    public void reset() {
        if (block) return;

        setText("[0]");
        currentNum = 0;
        block = false;
        setBackground(null);
        Frame.colored.remove(this);
    }
}