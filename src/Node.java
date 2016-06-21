public class Node {

    Node parent;
    int heuristicCost = 0; // Heuristic cost
    int finalCost = 0; // F = G + H
    int i, j;

    Node(int i, int j, int addCost) {
        this.i = i;
        this.j = j;
        heuristicCost += addCost;
    }

    @Override
    public String toString() {
        return "[" + this.i + ", " + this.j + "]";
    }

    public String direction(Node next, boolean direction) {

        if (next == null) return "";

        if (direction) {
            if (next.j == j) {
                if (next.i < i) {
                    return "⇧ ";
                } else {
                    return "⇩ ";
                }
            }

            if (next.i == i) {
                if (next.j < j) {
                    return "⇦ ";
                } else {
                    return "⇒ ";
                }
            }

            if (next.i < i) {
                if (next.j < j) {
                    return "⬁ ";
                } else {
                    return "⬃ ";
                }
            } else {
                if (next.j < j) {
                    return "⬀ ";
                } else {
                    return "⬂ ";
                }
            }
        } else {
            if (next.j == j) {
                if (next.i < i) {
                    return "2 ";
                } else {
                    return "6 ";
                }
            }

            if (next.i == i) {
                if (next.j < j) {
                    return "4 ";
                } else {
                    return "0 ";
                }
            }

            if (next.i < i) {
                if (next.j < j) {
                    return "3 ";
                } else {
                    return "5 ";
                }
            } else {
                if (next.j < j) {
                    return "1 ";
                } else {
                    return "7 ";
                }
            }
        }
    }
}