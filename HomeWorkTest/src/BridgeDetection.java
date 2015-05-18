import java.util.*;

public class BridgeDetection {
	private int[][] map;
	private LinkedList<Edge> result;
	private int nodeNum;
	
	public BridgeDetection(int[][] map, LinkedList<Edge> result, int nodeNum){
		this.map = map;
		this.result = result;
		this.nodeNum = nodeNum;
	}
	
	public LinkedList<Edge> Detect(){
		for(int i = 0; i < nodeNum; i++){
			for(int j = i+1; j < nodeNum; j++){
				if(map[i][j] == 1){
					map[i][j] = 0;
					map[j][i] = 0;
					if(!BFS(i, j)){
						Edge e = new Edge(i, j);
						result.add(e);
					}
					map[i][j] = 1;
					map[j][i] = 1;
				}
			}
		}
		return result;
	}
	
	public boolean BFS(int a, int b){
		LinkedList<Integer> queue = new LinkedList<Integer>();
		ArrayList<Boolean> exitance = new ArrayList<Boolean>(); 
		int nodeNow;
		
		for(int i = 0; i < nodeNum; i++){
			exitance.add(false);
		}
		
		queue.add(a);
		exitance.set(a, true);
		while(!queue.isEmpty()){
			nodeNow = queue.getFirst();
			queue.remove(0);
			if(nodeNow == b)return true;
			for(int i = 0; i < nodeNum; i++){
				if(map[nodeNow][i] == 1 && !exitance.get(i)){
					queue.add(i);
					exitance.set(i, true);
				}
			}
		}
		return false;
	}
}
