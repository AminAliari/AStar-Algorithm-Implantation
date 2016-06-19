import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class Frame extends JFrame {

    public static int[][] map;
    public static Button btn[][];

    public static final Color[] colors = {new Color(125, 167, 116), new Color(42, 179, 231), new Color(70, 67, 123), new Color(130, 100, 84), new Color(252, 211, 61), new Color(241, 98, 69), new Color(217, 146, 54), new Color(63, 121, 186)};

    private Thread ast;
    private AStar as;
    private Point start, end;
    private int row, colm;

    public Frame(int row, int colm, Point start, Point end) {

        this.row = row;
        this.colm = colm;
        this.start = start;
        this.end = end;

        btn = new Button[row][colm];
        map = new int[row][colm];

        setLayout(new GridLayout(row, colm));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colm; j++) {
                btn[i][j] = new Button("[0]", i, j);
                add(btn[i][j]);
            }
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    solve();
                }
            }
        });

        setTitle("A*");
        setSize(600, 600);
//        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void solve() {
        Thread ast = new Thread();
        AStar as = new AStar(row, colm, ast);
        ast = new Thread(as);

        as.setStartCell(start.x, start.y);
        as.setEndCell(end.y, end.y);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colm; j++) {
                if (map[i][j] < 5) {
                    as.grid[i][j] = new Node(i, j, map[i][j]);
                    as.grid[i][j].heuristicCost = Math.abs(i - end.x) + Math.abs(j - end.y);
                } else {
                    as.setBlocked(i, j);
                }
            }
        }
        as.grid[start.x][start.y].finalCost = 0;
        ast.start();
    }
}