package patterns;

 class Factory {
        public OS getCurrentOS(String inputos) {
            OS os = null;
            if (inputos.equals("windows")) {
                os = new windowsOS();
            } else if (inputos.equals("linux")) {
                os = new linuxOS();
            } else if (inputos.equals("mac")) {
                os = new macOS();
            }
            return os;
        }
    }
    interface OS {
        void getOS();
    }
    class windowsOS implements OS {
        public void getOS () {
            System.out.println("применить для виндовс");
        }
    }
    class linuxOS implements OS {
        public void getOS () {
            System.out.println("применить для линукс");
        }
    }
    class macOS implements OS {
        public void getOS () {
            System.out.println("применить для мак");
        }
    }

    public class FactoryTest {//тест
        public static void main(String[] args){
            String win = "linux";
            Factory factory = new Factory();
            OS os = factory.getCurrentOS(win);
            os.getOS();
        }
    }

