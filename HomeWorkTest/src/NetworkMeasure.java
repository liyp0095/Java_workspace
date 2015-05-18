import org.graphstream.algorithm.BetweennessCentrality; 
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import Jama.*;

import java.util.ArrayList;

public class NetworkMeasure {
	private Matrix A;
	private int MatrixSize;
	private Graph graph = new SingleGraph("graph");
	private BetweennessCentrality bcb = new BetweennessCentrality();
	private double[][] map;
	private final int maxDis = Integer.MAX_VALUE;
	
	public NetworkMeasure(double[][] arr){
		A = new Matrix(arr);
//		A.print(9, 1);
		MatrixSize = A.getColumnDimension();
		
		Node[] V = new Node[MatrixSize];
		for(int i=0; i<MatrixSize; i++){
			V[i] = graph.addNode("V" + i);
		}
		for(int i=0; i<MatrixSize; i++){
			for(int j = i; j < MatrixSize; j++){
				if(A.get(i, j) != 0){
					graph.addEdge("E"+i+"E"+j, "V"+i, "V"+j);
					bcb.setWeight(V[i], V[j], 1);
				}
			}
		}
		
		double[][] trans = new double[MatrixSize][MatrixSize];
		for(int i = 0; i < MatrixSize; i++){
			for(int j = 0; j < MatrixSize; j++){
				if(arr[i][j] == 0 && i != j)
					trans[i][j] = maxDis;
				else
					trans[i][j] = arr[i][j];
			}
		}
		map = trans;
	}
	
	public double[] DegreeCentrality(){
		double[] cen = new double[MatrixSize];
		for(int i = 0; i < MatrixSize; i++){
			cen[i] = 0;
			for(int j = 0; j < MatrixSize; j++){
				cen[i] += A.get(i, j); 
			}
//			System.out.println(cen[i]);
		}
		return cen;
	}
	
	public double[] EigenvectorCentrality(){
		double[] cen = new double[MatrixSize];
		Matrix res = A.eig().getV();
		for(int i = 0; i < MatrixSize; i++){
			cen[i] = res.get(i, (MatrixSize-1));
		}
		return cen;
	}
	
	public double[] KatzCentrality(double alpha, double beta){
		double[] cen = new double[MatrixSize];
		Matrix u = MakeI(MatrixSize);						  				//unit 1 Matrix I
		Matrix c1 = new Matrix(MatrixSize, 1, 1.0d);						//vector of all 1's
		Matrix res = A.transpose().times(alpha);
		res = u.minus(res).inverse().times(beta).times(c1);
		for(int i = 0; i < MatrixSize; i++){
			cen[i] = res.get(i, 0);
//			System.out.println(cen[i]);
		}
//		res.print(2, 2);
		return cen;
	}
	
	public double[] PageRank(double alpha, double beta){
		double[] cen = new double[MatrixSize];
		Matrix diag = MakeDout();
		Matrix u = MakeI(MatrixSize);						  				//unit 1 Matrix I
		Matrix c1 = new Matrix(MatrixSize, 1, 1.0d);						//vector of all 1's
		Matrix res = A.transpose().times(alpha).times(diag.inverse());
		res = u.minus(res).inverse().times(beta).times(c1);
		for(int i = 0; i < MatrixSize; i++){
			cen[i] = res.get(i, 0);
		}
//		res.print(2, 2);
		return cen;
	}
	
	public double[] BetweennessCentrality(){
		double[] cen = new double[MatrixSize];
        bcb.init(graph);
        bcb.compute();
        for(int i = 0; i < MatrixSize; i++){
        	cen[i] = graph.getNode("V"+i).getAttribute("Cb");
//        	System.out.println(cen[i]);
        }
		return cen;
	}
	
	public double[] ClosenessCentrality(){
		double[] cen = new double[MatrixSize];
//		path.findpaths();
		for(int i = 0; i < MatrixSize; i++){
			ArrayList<Double> result = new ArrayList<Double>();
			ArrayList<Integer> pre = new ArrayList<Integer>();
			ShortestPath path = new ShortestPath(map, result, pre, i);
			path.findpaths();
			for(int j = 0; j < MatrixSize; j++){
				cen[i] += result.get(j);
//				System.out.println(result.get(j));
			}
			cen[i] = (MatrixSize-1)/cen[i];
//			System.out.println(cen[i]);
		}
		
		return cen;
	}
	
	private Matrix MakeI(int a){
		Matrix x = new Matrix(MatrixSize, MatrixSize, 1.0d);
		for(int i = 0; i < MatrixSize; i++){
			for(int j = 0; j < MatrixSize; j++){
				if(i != j)
					x.set(i, j, 0);
			}
		}
//		x.print(2, 2);
		return x;
	}
	
	private Matrix MakeDout(){
		double[] dout = new double[MatrixSize];
		for(int i = 0; i < MatrixSize; i++){
			dout[i] = 0;
			for(int j = 0; j < MatrixSize; j++){
				dout[i] += A.get(i, j); 
			}
//			System.out.println(cen[i]);
		}
		
		Matrix x = new Matrix(MatrixSize, MatrixSize, 1.0d);
		for(int i = 0; i < MatrixSize; i++){
			for(int j = 0; j < MatrixSize; j++){
				if(i != j)
					x.set(i, j, 0);
				else
					x.set(i, j, dout[i]);
			}
		}
//		x.print(2, 2);
		return x;
	}
}
