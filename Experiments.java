/*
 * Incomplete Experiment 1 
 *
 * CSI2510 Algorithmes et Structures de Donnees
 * www.uottawa.ca
 *
 * Robert Laganiere, 2022
 *
*/ 
import java.util.List;
import java.util.ArrayList;

import java.io.*;  
import java.util.Scanner;  

public class Experiments {

	//this is Experiment 2
	public static long averageRangequeryTimeLinear(List<Point3D> points, double eps){
			NearestNeighbors nn = new NearestNeighbors(points);

		long average = 0;
		for(int i = 0; i < points.size() / 10; i++){
			Point3D point = points.get(i * 10); //this should get 0, 10, 20, 30, etc...
			long timeStart = System.nanoTime();
			nn.rangeQuery(point,eps);
			long timeEnd = System.nanoTime();

			average += (timeEnd - timeStart);
			
		}

		return (average / (points.size() / 10));
	}

	//this is Experiment 2
	public static long averageRangequeryTimeKD(List<Point3D> points, double eps){
		NearestNeighborsKD nn = new NearestNeighborsKD(points);

		long average = 0;
		for(int i = 0; i < points.size() / 10; i++){
			Point3D point = points.get(i * 10); //this should get 0, 10, 20, 30, etc...
			long timeStart = System.nanoTime();
			nn.rangeQuery(point,eps);
			long timeEnd = System.nanoTime();

			average += (timeEnd - timeStart); //NOT SURE ABOUT THIS
			
		}

		return (average / (points.size() / 10));
	}


	// reads a csv file of 3D points (rethrow exceptions!)
	public static List<Point3D> read(String filename) throws Exception {
		
	List<Point3D> points= new ArrayList<Point3D>(); 
	double x,y,z;

	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); sc.next(); sc.next();

	// read points
	while (sc.hasNext())  
	{  
		x= Double.parseDouble(sc.next());
		y= Double.parseDouble(sc.next());
		z= Double.parseDouble(sc.next());
		points.add(new Point3D(x,y,z));  
	}   

	sc.close();  //closes the scanner  

	return points;
	}
  
  public static void main(String[] args) throws Exception {  
  
	//Choose between exp1 or exp2
	String experiment = args[0];
    // choose between linear or kd
	String method = args[1];

	// reads the csv file
	List<Point3D> points = Experiments.read(args[2]);

	double eps = Double.parseDouble(args[3]);
	
	// read the point given
	Point3D query = new Point3D(Double.parseDouble(args[4]),
								Double.parseDouble(args[5]),
								Double.parseDouble(args[6]));



	//Experiment 1
	if(experiment.equals("exp1")){

		if (method.equals("lin")){
			NearestNeighbors nn = new NearestNeighbors(points);
			List<Point3D> neighbors = nn.rangeQuery(query,eps);
			System.out.println("number of neighbors = "+neighbors.size());
			System.out.println(neighbors);
		}

		else{
			NearestNeighborsKD nn = new NearestNeighborsKD(points);
			List<Point3D> neighbors = nn.rangeQuery(query,eps);
			System.out.println("number of neighbors = "+neighbors.size());
			System.out.println(neighbors);
		}

	}

	//Experiment 2
	else{

		if (method.equals("lin")){
			NearestNeighbors nn = new NearestNeighbors(points);
			List<Point3D> neighbors = nn.rangeQuery(query,eps);
			System.out.println("number of neighbors = "+neighbors.size());
			long averageTime = Experiments.averageRangequeryTimeLinear(points, 0.5);
			System.out.println("Average rangequery time for " + method + " at 0.5 eps: " + averageTime);
		}


		else{
			NearestNeighborsKD nn = new NearestNeighborsKD(points);
			List<Point3D> neighbors = nn.rangeQuery(query,eps);
			System.out.println("number of neighbors = "+neighbors.size());
			long averageTime = Experiments.averageRangequeryTimeKD(points, 0.5);
			System.out.println("Average rangequery time for " + method + " at 0.5 eps: " + averageTime);
		}

	}
  }   
}
