package pathfinding;


/**
 * Created by Suwadith 2015214 on 3/28/2017.
 */

public class Node {

    public int x;
    public int y;
    double hValue;
    int gValue;
    double fValue;
    Node parent;


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
