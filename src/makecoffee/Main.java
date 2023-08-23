package makecoffee;

public class Main {

        public static void main(String[] args) {
            DrinkMarker latterMaker = new LatteMarker();
            DrinkMarker teaMaker = new TeaMarker();

            latterMaker.makeDrink();
            teaMaker.makeDrink();
        }
    }

