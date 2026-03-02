import java.util.*;

public class BFS implements SearchAlgorithm {

    @Override
    public SearchResult search(Grid grid, Node start, Node goal) {
        SearchResult result = new SearchResult();
        long startTime = System.nanoTime();

        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start.x + "," + start.y);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.nodesVisited++;

            if (current.x == goal.x && current.y == goal.y) {
                result.path = reconstructPath(current);
                result.pathCost = result.path.size() - 1;
                result.pathFound = true;
                result.executionTime = (System.nanoTime() - startTime) / 1_000_000;
                return result;
            }

            List<Node> neighbors = getNeighbors(grid, current);

            for (Node neighbor : neighbors) {
                String key = neighbor.x + "," + neighbor.y;
                if (!visited.contains(key)) {
                    visited.add(key);
                    neighbor.parent = current;
                    queue.offer(neighbor);
                }
            }
        }

        result.executionTime = (System.nanoTime() - startTime) / 1_000_000;
        return result;
    }

    private List<Node> getNeighbors(Grid grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

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