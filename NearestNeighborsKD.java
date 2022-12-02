/*
 * NearestNeighborsKD
 *
 * Matthew Petrucci
 *
 * 30011935
 *
*/ 

import java.util.List;
import java.util.ArrayList;

public class NearestNeighborsKD {

    public java.util.List<Point3D> points; //todo: remove public once testing is done
    public KDtree tree; //todo: remove public once testing is done

    NearestNeighborsKD(List<Point3D> points){
        //builds a KDtree from the list of points

        this.points = points;
        this.tree = new KDtree();

        for(int i = 0; i < points.size(); i++){

            tree.add(points.get(i));

        }

    }
    public List<Point3D> rangeQuery(Point3D p, double eps){
        List<Point3D> neighbors = new ArrayList<Point3D>();
        return rangeQueryRec(p, eps, neighbors, this.tree.root);
    }

    //recurison of rangeQuery
    public List<Point3D> rangeQueryRec(Point3D p, double eps, List<Point3D> points, KDnode node) {
        //finds the nearest neighbors of point p from distance eps. returns list of points

        if(node == null){
            return null;
        }
        
        if(p.distance(node.point) < eps){
            points.add(node.point);
        }

        if((p.get(node.axis) - eps) <= node.value){
            rangeQueryRec(p, eps, points, node.left);
        }

        if((p.get(node.axis) + eps) > node.value){
            rangeQueryRec(p, eps, points, node.right);
        }

        return points;
        
    }

    public static void main(String[] args){
        
        KDtree tree = new KDtree();
        Point3D p1 = new Point3D(2,1,3);
        Point3D p2 = new Point3D(-5,2,10);
        Point3D p3 = new Point3D(12,7,4);
        Point3D p4 = new Point3D(3,2,3);
        Point3D p5 = new Point3D(-1,1,3);
        Point3D p6 = new Point3D(9,-4,-2);
        Point3D p7 = new Point3D(2,1,-4);

        java.util.List<Point3D> points = new ArrayList<Point3D>();

        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);

        tree.add(p1);
        tree.add(p2);
        tree.add(p3);
        tree.add(p4);
        tree.add(p5);
        tree.add(p6);
        tree.add(p7);

        NearestNeighborsKD n = new NearestNeighborsKD(points);

        java.util.List<Point3D> neighbors = n.rangeQuery(p2, 4);


        System.out.println(neighbors);

    }

}