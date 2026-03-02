import java.util.*;

public class AStar implements SearchAlgorithm {

    @Override
    public SearchResult search(Grid grid, Node start, Node goal) {
        SearchResult result = new SearchResult();
        long startTime = System.nanoTime();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> n.fCost));
        Set<String> closedSet = new HashSet<>();
        Map<String, Node> openSetMap = new HashMap<>(); // For quick lookup

        start.gCost = 0;
        start.hCost = start.calculateHeuristic(goal);
        start.fCost = start.gCost + start.hCost;

        openSet.offer(start);
        openSetMap.put(start.x + "," + start.y, start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            openSetMap.remove(current.x + "," + current.y);
            result.nodesVisited++;

            if (current.x == goal.x && current.y == goal.y) {
                result.path = reconstructPath(current);
                result.pathCost = (int) current.gCost;
                result.pathFound = true;
                result.executionTime = (System.nanoTime() - startTime) / 1_000_000;
                return result;
            }

            closedSet.add(current.x + "," + current.y);

            List<Node> neighbors = getNeighbors(grid, current);

            for (Node neighbor : neighbors) {
                String neighborKey = neighbor.x + "," + neighbor.y;

                if (closedSet.contains(neighborKey)) continue;

                double tentativeGCost = current.gCost + 1; // Uniform cost

                Node existingNode = openSetMap.get(neighborKey);

                if (existingNode == null || tentativeGCost < existingNode.gCost) {
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = neighbor.calculateHeuristic(goal);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.parent = current;

                    if (existingNode == null) {
                        openSet.offer(neighbor);
                        openSetMap.put(neighborKey, neighbor);
                    } else {
                        // Update existing node
                        existingNode.gCost = neighbor.gCost;
                        existingNode.fCost = neighbor.fCost;
                        existingNode.parent = current;
                        // Re-heapify
                        openSet.remove(existingNode);
                        openSet.offer(existingNode);
                    }
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