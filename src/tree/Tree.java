package tree;

public class Tree { //обход дерева в глубину

        private int value;
        private Tree left;
        private Tree right;

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public Tree (int value){
            this.value = value;
        }
        public int sum(){
            int sum = value;

            if (left != null){
                sum += left.sum();
            }
            if (right != null){
                sum += right.sum();
            }
            return sum;
        }


    }

