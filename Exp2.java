import java.util.List;
import java.util.ArrayList;

import java.io.*;  
import java.util.Scanner;  

public class Exp2 {

    public static void main(String[] args) throws Exception {
        
         // choose between linear or kd
         String method = args[0];

         // reads the csv file
         List<Point3D> points = Experiments.read(args[1]);
 
         double eps = Double.parseDouble(args[2]);
         
         // read the point given
         Point3D query = new Point3D(Double.parseDouble(args[3]),
                                     Double.parseDouble(args[4]),
                                     Double.parseDouble(args[5]));
                                    
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