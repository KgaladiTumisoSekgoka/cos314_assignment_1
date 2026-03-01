import java.util.*;

public class DFS implements SearchAlgorithm {

    @Override
    public SearchResult search(Grid grid, Node start, Node goal) {
        SearchResult result = new SearchResult();
        long startTime = System.nanoTime();

        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);
        visited.add(start.x + "," + start.y);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            result.nodesVisited++;

            if (current.x == goal.x && current.y == goal.y) {
                // Reconstruct path
                result.path = reconstructPath(current);
                result.pathCost = result.path.size() - 1;
                result.pathFound = true;
                result.executionTime = (System.nanoTime() - startTime) / 1_000_000;
                return result;
            }

            // Get neighbors (4-connected: up, down, left, right)
            List<Node> neighbors = getNeighbors(grid, current);

            for (Node neighbor : neighbors) {
                String key = neighbor.x + "," + neighbor.y;
                if (!visited.contains(key)) {
                    visited.add(key);
                    neighbor.parent = current;
                    stack.push(neighbor);
                }
            }
        }

        result.executionTime = (System.nanoTime() - startTime) / 1_000_000;
        return result;
    }

    private List<Node> getNeighbors(Grid grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // N, S, E, W

        for (int[] dir : directions) {
            int newX = node.x + dir[0];
            int newY = node.y + dir[1];

            if (grid.isValid(newX, newY)) {
                neighbors.add(new Node(newX, newY));
            }
        }

        return neighbors;
    }

    private List<Node> reconstructPath(Node goal) {
        List<Node> path = new ArrayList<>();
        Node current = goal;

        while (current != null) {
            path.add(0, current);
            current = current.parent;
        }

        return path;
    }
}