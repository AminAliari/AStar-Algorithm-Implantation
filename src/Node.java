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
}