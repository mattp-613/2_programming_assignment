public class KDtree {

    private KDnode root;

    public KDtree(){
        this.root = null;
    }

    //use this function to add
    public void add(Point3D pt){

        KDnode node = null;
        insert(pt, node, 0);

    }

    //used for recursion of add function
    public KDnode insert(Point3D pt, KDnode node, int axis){

        //note: this has not yet been confirmed to work as the visual of the tree hasn't been established

        if(node == null){
            node = new KDnode(pt,axis);
        }

        else if(pt.get(axis) <= node.value){
            node.left = insert(pt, node.left, (axis+1) % 3); //module dimension
        }

        else{
            node.right = insert(pt, node.right, (axis+1) %3);
        }

        if (this.root == null) this.root = node;
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

        System.out.println(tree.root.point.getZ());
    }

}