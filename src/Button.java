import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    private boolean selected;

    public Button(String s, int i, int j, final Color color) {
        super(s);
        Frame.map[i][j] = 0;
        selected = false;
        setFocusable(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected) {
                    Frame.map[i][j] = 0;
                    setForeground(Color.BLACK);
                } else {
                    Frame.map[i][j] = 1;
                    setForeground(color);
                }
                selected = !selected;
            }
        });
    }
}