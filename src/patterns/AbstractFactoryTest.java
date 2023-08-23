package patterns;
    interface Lada {
        long getLadaPrice();
    }
    interface Ferrari {
        long getFerrariPrice();
    }
    interface Porshe {
        long getPorshePrice();
    }
    interface InteAbsFactory {
        Lada getLada();
        Ferrari getFerrari();
        Porshe getPorshe();
    }
    class UaLadaImpl implements Lada {// первая
        public long getLadaPrice() {
            return 1000;
        }
    }
    class UaFerrariImpl implements Ferrari {
        public long getFerrariPrice() {
            return 3000;
        }
    }
    class UaPorsheImpl implements Porshe {
        public long getPorshePrice() {
            return 2000;
        }
    }
    class UaCarPriceAbsFactory implements InteAbsFactory {
        public Lada getLada() {
            return new UaLadaImpl();
        }
        public Ferrari getFerrari() {
            return new UaFerrariImpl();
        }
        public Porshe getPorshe() {
            return new UaPorsheImpl();
        }
    }// первая
    class RuLadaImpl implements Lada {// вторая
        public long getLadaPrice() {
            return 10000;
        }
    }
    class RuFerrariImpl implements Ferrari {
        public long getFerrariPrice() {
            return 30000;
        }
    }
    class RuPorsheImpl implements Porshe {
        public long getPorshePrice() {
            return 20000;
        }
    }
    class RuCarPriceAbsFactory implements InteAbsFactory {
        public Lada getLada() {
            return new RuLadaImpl();
        }
        public Ferrari getFerrari() {
            return new RuFerrariImpl();
        }
        public Porshe getPorshe() {
            return new RuPorsheImpl();
        }
    }// вторая

    public class AbstractFactoryTest {//тест
        public static void main(String[] args) {
            String country = "UA";
            InteAbsFactory ifactory = null;
            if(country.equals("UA")) {
                ifactory = new UaCarPriceAbsFactory();
            } else if(country.equals("RU")) {
                ifactory = new RuCarPriceAbsFactory();
            }

            Lada lada = ifactory.getLada();
            System.out.println(lada.getLadaPrice());
        }
    }

