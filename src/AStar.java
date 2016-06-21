import java.awt.*;
import java.util.PriorityQueue;

public class AStar implements Runnable {

    public Node[][] grid;
    private PriorityQueue<Node> open;
    private Node current;
    private Thread parent;
    private boolean isRunning;

    private boolean closed[][];
    private final int vhCost, dCost;
    private int si, sj, ei, ej;

    public AStar(int row, int colm, Thread parent) {

        this.parent = parent;
        grid = new Node[row][colm];
        closed = new boolean[row][colm];

        vhCost = 10;
        dCost = 14;

        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Node c1 = (Node) o1;
            Node c2 = (Node) o2;

            return c1.finalCost < c2.finalCost ? -1 :
                    c1.finalCost > c2.finalCost ? 1 : 0;
        });
    }

    public void checkAndUpdateCost(Node current, Node t, int cost) {
        if (t == null || closed[t.i][t.j]) return;
        int finalCost = t.heuristicCost + cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || finalCost < t.finalCost) {
            t.finalCost = finalCost;
            t.parent = current;
            if (!inOpen) open.add(t);
        }
    }

    public void run() {

        open.add(grid[si][sj]);
        isRunning = true;

        while (isRunning) {

            current = open.poll();
            if (current == null) break;
            closed[current.i][current.j] = true;

            if (current.equals(grid[ei][ej])) {
                if (closed[ei][ej]) {

                    System.out.println("Path: ");
                    Node current = grid[ei][ej];
                    System.out.print(current);
                    Frame.btn[current.i][current.j].setBackground(Color.GREEN);
                    Frame.colored.add(Frame.btn[current.i][current.j]);

                    while (current.parent != null) {
                        System.out.print(" -> " + current.parent);
                        Frame.btn[current.parent.i][current.parent.j].setBackground(Color.GREEN);
                        Frame.colored.add(Frame.btn[current.parent.i][current.parent.j]);
                        current = current.parent;
                    }
                    System.out.println();
                } else {
                    System.out.println("No possible path");
                }

                Frame.btn[si][sj].setBackground(Color.RED);
                Frame.btn[ei][ej].setBackground(Color.RED);

                isRunning = false;
                return;
            }

            Node t;

            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                checkAndUpdateCost(current, t, current.finalCost + vhCost);

                if (current.j - 1 >= 0) {
                    t = grid[current.i - 1][current.j - 1];
                    checkAndUpdateCost(current, t, current.finalCost + dCost);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i - 1][current.j + 1];
                    checkAndUpdateCost(current, t, current.finalCost + dCost);
                }
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                checkAndUpdateCost(current, t, current.finalCost + vhCost);
            }

            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                checkAndUpdateCost(current, t, current.finalCost + vhCost);
            }

            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                checkAndUpdateCost(current, t, current.finalCost + vhCost);

                if (current.j - 1 >= 0) {
                    t = grid[current.i + 1][current.j - 1];
                    checkAndUpdateCost(current, t, current.finalCost + dCost);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i + 1][current.j + 1];
                    checkAndUpdateCost(current, t, current.finalCost + dCost);
                }
            }
        }
    }

    public void setBlocked(int i, int j) {
        grid[i][j] = null;
    }

    public void setStartCell(int i, int j) {
        si = i;
        sj = j;
    }

    public void setEndCell(int i, int j) {
        ei = i;
        ej = j;
    }

    public void stop() {
        isRunning = false;
    }
}