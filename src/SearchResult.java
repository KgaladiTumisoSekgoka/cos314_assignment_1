import java.util.List;

public class SearchResult {
    public List<Node> path;
    public int pathCost;
    public int nodesVisited;
    public boolean pathFound;
    public long executionTime;

    public SearchResult() {
        this.path = null;
        this.pathCost = 0;
        this.nodesVisited = 0;
        this.pathFound = false;
        this.executionTime = 0;
    }
}