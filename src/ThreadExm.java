import static sun.text.bidi.BidiBase.BidiPairedBracketType.COUNT;

public class ThreadExm {
        public volatile static int count = 4;

        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < count; i++) {
                new SleepingThread().join();
            }

        }
        private static class SleepingThread extends Thread{
            private static volatile int threadCount = 0;
            private volatile int countdownIndex = COUNT;

            public SleepingThread(){
                super(String.valueOf(++threadCount));
                start();
            }

            @Override
            public void run() {
                while (true){
                    System.out.println(this);
                    if (-- countdownIndex == 0) return;
                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        System.out.println("Нить прервана");
                        return;
                    }
                }
            }

            @Override
            public String toString() {
                return "#" + getName() + ": " + countdownIndex;
            }
        }
    }

