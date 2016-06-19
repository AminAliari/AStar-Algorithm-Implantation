public class Node {

    Node parent;
    int heuristicCost = 0; //Heuristic cost
    int finalCost = 0; // F = G + H
    int i, j;

    Node(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "[" + this.i + ", " + this.j + "]";
    }
}