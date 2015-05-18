import java.util.ArrayList;


public class ShortestPath {
	ArrayList<Double> Dis;
	ArrayList<Integer> prev;
	double[][] map;
	int start;
	ArrayList<Boolean> notfind = new ArrayList<Boolean>();
	
	public ShortestPath(){}
	
	public ShortestPath(double[][] map, ArrayList<Double> Dis, ArrayList<Integer> prev, int start){
		this.Dis = Dis;
		this.map = map;
		this.start = start;
		this.prev = prev;
	}
	
	public void findpaths(){
		int MinNode;
		Dis.clear();
		prev.clear();
		
		for(int i = 0; i < map.length; i++){
			notfind.add(true);
			Dis.add(map[start][i]);
			prev.add(start);
		}
		
		notfind.set(start, false);
		for(int i = 0; i < map.length-1; i++){
			MinNode = MinDisNode();
			notfind.set(MinNode, false);
			refreshDis(MinNode);
		}
	}
	
	private void refreshDis(int node){
		for(int i = 0; i < map.length; i++){
			if(!notfind.get(i))continue;
			if(Dis.get(node) + map[node][i] < Dis.get(i) && Dis.get(node) + map[node][i] > 0){
				Dis.set(i, Dis.get(node) + map[node][i]);
				prev.set(i, node);
			}
		}
	}
	
	private int MinDisNode(){
		int Min = -1;
		for(int i = 0; i < map.length; i++){
			if(!notfind.get(i))continue;
			if(Min == -1)Min = i;
			if(Dis.get(Min) > Dis.get(i))Min = i;
		}
		return Min;
	}
	
	public void getStart(int start){
		this.start = start;
	}
	
	public void getInit(double[][] map, ArrayList<Double> Dis, ArrayList<Integer> prev, int start){
		this.Dis = Dis;
		this.map = map;
		this.start = start;
		this.prev = prev;
	}
}
