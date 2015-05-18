import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class HomeWorkTest {
	public static void main(String[] args){
		
/*		//≤‚ ‘BridgeDetection¿‡	
		int[][] map = {{0,1,0,1,0,0,0,0},
						{1,0,1,1,0,0,0,0},
						{0,1,0,0,0,0,0,0},
						{1,1,0,0,1,0,0,0},
						{0,0,0,1,0,1,1,0},
						{0,0,0,0,1,0,1,1},
						{0,0,0,0,1,1,0,0},
						{0,0,0,0,0,1,0,0},
						};
		LinkedList<Edge> result = new LinkedList<Edge>();
		BridgeDetection bri = new BridgeDetection(map, result, 8);
		bri.Detect();
		showLinkedList(result);*/
		
		double arr[][] = {{0,1,0,0,0,0,0,0,0,0},
							{1,0,1,0,0,0,0,0,0,0},
							{0,1,0,1,1,1,0,0,0,0},
							{0,0,1,0,1,1,0,0,0,0},
							{0,0,1,1,0,1,0,0,0,0},
							{0,0,1,1,1,0,1,0,0,0},
							{0,0,0,0,0,1,0,1,1,0},
							{0,0,0,0,0,0,1,0,1,1},
							{0,0,0,0,0,0,1,1,0,1},
							{0,0,0,0,0,0,0,1,1,0}
							};
		NetworkMeasure net = new NetworkMeasure(arr);
		System.out.print("\t\t\t\tV1\tV2\tV3\tV4\tV5\tV6\tV7\tV8\tV9\tV10");
		System.out.println();
		showNetworkRes("DegreeCentrality", net.DegreeCentrality());
		showNetworkRes("EigenvectorCentrality", net.EigenvectorCentrality());
		showNetworkRes("KatzCentrality     ",net.KatzCentrality(0.3, 0.3));
		showNetworkRes("PageRank        ", net.PageRank(0.3, 0.3));
		showNetworkRes("BetweennessCentrality", net.BetweennessCentrality());
		showNetworkRes("ClosenessCentrality", net.ClosenessCentrality());
		
		
		//≤‚ ‘ShortestPath¿‡
/*		final int maxDis = Integer.MAX_VALUE;
		double[][] mapS = {{9999,9999,10,9999,30,100},
	            		{9999,9999,5,9999,9999,9999},
	            		{9999,9999,9999,50,9999,9999},
	            		{9999,9999,9999,9999,9999,10},
	            		{9999,9999,9999,20,9999,60},
	            		{9999,9999,9999,9999,9999,9999}
	            		};//{{0,2,1,maxDis},{2,0,maxDis,5},{1,maxDis,0,3},{maxDis,5,3,0}};
		ArrayList<Double> Dis = new ArrayList<Double>();
		ArrayList<Integer> prev = new ArrayList<Integer>();
		ShortestPath path = new ShortestPath(mapS, Dis, prev, 0);
		path.findpaths();
		for(int i = 0; i < mapS.length; i++){
			System.out.println(Dis.get(i) + " " + prev.get(i));
		}*/
	}
	
	private static void showNetworkRes(String name, double[] res){
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.print(name + "\t\t");
		for(int i = 0; i < res.length; i++){
			System.out.print(df.format(res[i])+"\t");
		}
		System.out.println();
	}
	
	private static void showLinkedList(LinkedList<Edge> result){
		if(result.size() == 0){
			System.out.println("None!");
			return;
		}
		for(int i = 0; i < result.size(); i++){
			System.out.println(result.get(i).source + " " + result.get(i).target);
		}
	}
}
