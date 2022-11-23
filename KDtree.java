public class KDtree {
    public class KDnode {

        public Point3D point;
        public int axis;
        public double value;
        public KDnode left;
        public KDnode right;

        public KDnode(Point3D pt, int axis) {
            this.point = pt;
            this.axis = axis;
            this.value = pt.get(axis);
            this.left = null;
            this.right = null;
        }

    }

}