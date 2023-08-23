package graf;

public class Runner {
        public static void main(String[] args) {
            Graf graf = new Graf();
            graf.addVertex('A'); // 0
            graf.addVertex('B'); // 1
            graf.addVertex('C'); // 2
            graf.addVertex('D'); // 3
            graf.addVertex('E'); // 4
            graf.addVertex('F'); // 5

            graf.addEdge(0, 1, 1); // AB
            graf.addEdge(1, 2, 1); // BC
            graf.addEdge(2, 3, 1); // CD
            graf.addEdge(0, 4, 1); // AE
            graf.addEdge(4, 5, 1); // EF

            //  graf.passInDeep(0);   // обход в глубину
            graf.passInWidth(0);    // обход в ширину
        }
    }

