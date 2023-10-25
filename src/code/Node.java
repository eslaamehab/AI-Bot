package code;
import java.util.ArrayList;

public class Node {
	public String NeoState;
	public Node ParentNode;
//	public String Operator;
	public int depth;
	public int pathCost;
	public int heuristic;
	public String prevOp;
	public Node(String InitialState) {
//		Operator="";
		NeoState = InitialState;
		depth=0;
		pathCost=0;
		heuristic = 0;
		ParentNode = null;
		prevOp = null;
	}
	public Node(String State,int cost, int newdepth, Node PNode, int heuristic, String prevOp) {
		NeoState = State;
		depth = newdepth+1;
		pathCost = cost;
		ParentNode = PNode;
		this.heuristic = heuristic;
		this.prevOp = prevOp;
	}
//	public boolean isVisited() {
//		return visited;
//	}
//	public void setVisited(boolean visited) {
//		this.visited = visited;
//	}
	public String getNeoState() {
		return NeoState;
	}
	public void setNeoState(String neoState) {
		NeoState = neoState;
	}
	public Node getParentNode() {
		return ParentNode;
	}

	public String getPrevOp() {
		return prevOp;
	}
	
	
	

}