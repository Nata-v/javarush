package jeansshop;

import java.util.List;

public class Main {
        public static List<Jeans> allJeans = Util.getAllJeans();

        public static void main(String[] args) {
            for (Jeans jeans : allJeans){
                System.out.println(jeans);
            }
        }
    }
