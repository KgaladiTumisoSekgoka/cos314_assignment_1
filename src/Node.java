import java.util.Objects;

public class Node {
    public int x, y;
    public Node parent;
    public double gCost; // Cost from start to current node
    public double hCost; // Heuristic cost to goal
    public double fCost; // Total cost (g + h)

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
    }

    // Manhattan Distance heuristic
    public double calculateHeuristic(Node goal) {
        return Math.abs(this.x - goal.x) + Math.abs(this.y - goal.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}