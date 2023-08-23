import java.util.stream.Stream;

public class Fibonachi {
        public static void main(String[] args) {

            int num0 = 0;
            int num1 = 1;
            int num2;
            System.out.println(num0 + " " + num1 + " ");
            for (int i = 3; i <= 11; i++) {
                num2 = num0 + num1;
                System.out.println(num2 + " ");
                num0 = num1;
                num1 = num2;
            }
            int num = 9;
            int fibonacciValues = Stream.iterate(new int[] {0, 1}, arr -> new int[] {arr[1], arr[0] + arr[1]})
                    .limit(num)
                    .map(y -> y[0])
                    .mapToInt(Integer::intValue)
                    .sum();
            System.out.println(fibonacciValues);

        }

        int n = 100;
        /*     long[] mem = new long[n + 1];

               Arrays.fill(mem, -1);
               System.out.println(fibNaive(n, mem));
           }
           //мемоизация
           private static long fibNaive(int n, long[] mem){
               if (mem[n] != -1){
                   return n;
               }
               if (n <= 1){
                   return n;
               }
               long result = fibNaive(n-1, mem) + fibNaive(n-2, mem);
               mem[n] = result;
               return result;
           }*/
        //O(n)

        private static long fibEffective(int n){
            long[] arr = new long[n+1];

            arr[0] = 0;
            arr[1] = 1;

            for (int i = 2; i <= n; i++){
                arr[i] = arr[i-1] + arr[i-2];

            }
            return arr[n];
        }

    }

