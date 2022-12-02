/*
 * KDtree (KDnode)
 *
 * Matthew Petrucci
 *
 * 30011935
 *
*/ 

public class KDtree {

    public KDnode root;

    public KDtree(){
        this.root = null;
    }

    public void add(Point3D pt){
        //adds a point to the tree
        
        insert(pt, this.root, 0);

    }

    public KDnode insert(Point3D pt, KDnode node, int axis){
        //recursive helper to the add function that checks 
        //if a node is currently occupied and if so where to place the point
        //based off the points axis

        if(node == null){
            node = new KDnode(pt,axis);
            if (this.root == null){ //may be redundant
                this.root = node;
            }
        }

        else if(pt.get(axis) <= node.value){
            node.left = insert(pt, node.left, (axis+1) % 3); //module dimension
        }

        else{
            node.right = insert(pt, node.right, (axis+1) %3);
        }

        return node;
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

        tree.add(p1);
        tree.add(p2);
        tree.add(p3);
        tree.add(p4);
        tree.add(p5);
        tree.add(p6);
        tree.add(p7);

        //System.out.println(tree.root.right.left.point);
    }

}