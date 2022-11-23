import java.util.List;
import java.util.ArrayList;

public class NearestNeighborsKD {

    private java.util.List<Point3D> points; 
    private KDtree tree;

    NearestNeighborsKD(java.util.List<Point3D> points){

        this.points = points;

    }


    public List<Point3D> rangeQuery(Point3D p, double eps) {
        //finds the nearest neighbors of point p from distance eps, given n list of points and 
        return null;
    }

    public KDtree NearestNeighborsKD(List<Point3D> list){
        //builds a KDtree from the list of points

        KDtree tree = new KDtree();
        return tree;
    }


    public static void main(String[] args){        

    }

}