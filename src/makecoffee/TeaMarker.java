package makecoffee;

public class TeaMarker extends DrinkMarker{
        @Override
        public   void getRightCup() {
            System.out.println("Берем чашку для чая.");
        }

        @Override
        public   void putIngredient() {
            System.out.println("Насыпаем чай.");
        }

        @Override
        public   void pour() {
            System.out.println("Заливаем кипятком.");
        }
    }

