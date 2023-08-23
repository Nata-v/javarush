package tree;

public class MainTree {
        public static void main(String[] args) {
            Tree root =
                    new Tree(20,                         // 20 - вершина дерева
                            new Tree(7,             //7 - левая ветка щт вершины 20
                                    new Tree(4, null, new Tree(6)), new Tree(9)),   // 4- левая ветвь от 7, 6 - ветвь от 4; 9 - правя ветвь от 7
                            new Tree(35,              // 35 - правая ветка от вершины 20
                                    new Tree(31, new Tree(28), null), //31 - левая ветвь от 35, 28 -левая ветвь от 31
                                    new Tree(40, new Tree(38), new Tree(52)))); // 40 - правая ветвь от 35, 38 - левая ветвь от 40, 52 - правая ветвь от 40

            System.out.println("Сумма деревьев:" + root.sum());
        }
    }

