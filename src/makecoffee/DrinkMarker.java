package makecoffee;

public abstract class DrinkMarker {
        public abstract void getRightCup();
        public abstract void putIngredient();
        public abstract void pour();

        public void makeDrink(){
            getRightCup();
            putIngredient();
            pour();

        }
}
