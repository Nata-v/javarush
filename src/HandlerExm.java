public class HandlerExm {
        public static Thread.UncaughtExceptionHandler handler = new OurUncaughtExceptionHandler();

        public static void main(String[] args) {
            TestedThread commonThread = new TestedThread(handler);

            Thread thredA = new Thread(commonThread, "Нить 1");
            Thread thredB = new Thread(commonThread, "Нить 2 ");

            thredA.setUncaughtExceptionHandler(handler);
            thredB.setUncaughtExceptionHandler(handler);

            thredA.start();
            thredB.start();

            thredA.interrupt();
            thredB.interrupt();
        }
        public static class TestedThread extends Thread {
            public TestedThread(Thread.UncaughtExceptionHandler handler){
                setUncaughtExceptionHandler(handler);
                start();
            }

            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    throw new RuntimeException("My exception message");
                }
            }
        }
        public static class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
            @Override
            public  void uncaughtException(Thread t, Throwable e){
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        }
    }

