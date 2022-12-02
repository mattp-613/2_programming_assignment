/*
 * Experiment 3 for linear stack implementation
 *
 * Matthew Petrucci
 *
 * 30011935
 *
*/ 

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Exp3_lin{
    
    List<Point3D> pnts;
    double eps; //distance
    double minPnts; //min # of points to be considered a cluster

    Exp3_lin(List<Point3D> pnts){
        this.pnts = pnts;
    }

    Exp3_lin(String fileName) throws FileNotFoundException{
        this.pnts = read(fileName);
    }

    //default constructor
    Exp3_lin(String fileName, double eps, double minPnts) throws FileNotFoundException{
        this.pnts = read(fileName);
        this.eps = eps;
        this.minPnts = minPnts;
    }

    public double setEps(double eps){
        //sets distance in between points to be considered in a cluster
        this.eps = eps;
        return eps;
    }

    public double setMinPnts(double minPnts){
        //sets minimum amount of points required to be considered in a cluster
        this.minPnts = minPnts;
        return minPnts;
    }

    public void findClusters(){
        Stack<Point3D> S = new Stack<Point3D>();
        int C = 0; /* Cluster counter */
        NearestNeighbors NN = new NearestNeighbors(this.pnts);
        for(Point3D P : this.pnts) {
            if (P.label != -1 ){continue;} /* Already processed */
            List<Point3D> N = NN.rangeQuery(P,this.eps); /* Find neighbors */
            if (N.size() < this.minPnts) { /* Density check */
                P.label = 0; /* Label as Noise */
                continue;
            }

            C++; /* next cluster label */
            P.label= C; /* Label initial point */            
            S.addAll(N); /* Push neighbors to stack */

            while (!S.empty()) { 
                Point3D Q = S.pop(); /* Process point Q */
                if (Q.label == 0){
                    Q.label = C; /* Noise becomes border pt */
                    continue; //not actually necessary since the next if causes a continue
                }
                if (Q.label != -1){continue;} /* Previously processed */
                Q.label= C; /* Label neighbor */
                N = NN.rangeQuery(Q,this.eps); /* Find neighbors */
                if (N.size() >= this.minPnts) { /* Density check */
                    S.addAll(N); /* Push new neighbors to stack */
                }
            }
        }
    }

    public int getNumberOfClusters(){
        //goes through the list of points
        //finds and returns the number of clustered points
        //requires findClusters() to label the points in clusters, otherwise will return dumb/inaccurate answer
        //this method uses a Set object

        List<Integer> allClusters = new ArrayList<>();
        for(int i = 0; i < this.pnts.size(); i++){
            allClusters.add(i,this.pnts.get(i).getClusterLabel());

        }

        Set<Integer> sizeSet = new HashSet<>(allClusters);
        return sizeSet.size();

    }


    public List<Point3D> getPoints(){return this.pnts;};


    public static List<Point3D> read(String fileName) throws FileNotFoundException{
        //reads CSV file using java's Scanner util
        //returns the list of points from the CSV file in List format

        List<Point3D> pnts = new ArrayList<>();

        Scanner sc = new Scanner(new File(fileName));  
        sc.nextLine(); //skips the x,y,z string headers
        sc.useDelimiter(",|\\n"); //uses , and new line as delimiters

        while (sc.hasNext()){

            Double x = Double.parseDouble(sc.next());
            Double y = Double.parseDouble(sc.next());
            Double z = Double.parseDouble(sc.next());
            Point3D pnt = new Point3D(x,y,z); //sc.next() moves to the next value and returns the current value
            pnts.add(pnt);

        }

        sc.close();
        return pnts;
    }

    public void save(String fileName) throws IOException{
        //saves CSV File as filename_clusters_#eps_#minPts_#nClusters.csv
        //includes x,y,z,cluster #, and RGB
        //ensure that findClusters() and findRGB() have ran with newest data, otherwise this will output bad values

        int nClusters = getNumberOfClusters();
        String saveFileName = (fileName + "_clusters_" + eps + "_" + minPnts + "_" + nClusters + ".csv");
        
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(saveFileName));
        
            //header record
            writer.write("X,Y,Z,C,R,G,B");
            writer.newLine();
        
            for (int i = 0; i < this.pnts.size(); i++) {
                writer.write(this.pnts.get(i).getX() + ",");
                writer.write(this.pnts.get(i).getY() + ",");
                writer.write(this.pnts.get(i).getZ() + ",");
                writer.write(this.pnts.get(i).getClusterLabel() + "," + "\n");
            }
        
            writer.close();
        

        }

    public static void main(String[] args) throws FileNotFoundException, IOException{

        //javac DBScan.java && java DBScan Point_Cloud_2.csv 7 10

        //FILENAME, EPS AND MINPOINTS
        String fileName = args[0];
        double eps = Double.parseDouble(args[1]);
        double minPnts = Double.parseDouble(args[2]);


        //TESTING GROUNDS

         
        Exp3_lin db = new Exp3_lin(fileName, eps, minPnts);

        NearestNeighbors nb = new NearestNeighbors(db.getPoints());

        db.findClusters();

        List<Point3D> dbpoints = db.getPoints();

        long timeStart = System.nanoTime();
        db.save("Cloud3");
		long timeEnd = System.nanoTime();

        System.out.println("Time taken: " + (timeEnd - timeStart));

    }

}
