package factorymethod.male;

import factorymethod.AbstractFactory;
import factorymethod.Human;

public class MaleFactory implements AbstractFactory {

    public Human getPerson(int age){
        Human human = null;
        if (age <= KidBoy.MAX_AGE){
            human = new KidBoy();
        } else if (age <= TeenBoy.MAX_AGE) {
          human = new TeenBoy();
        }else {
            human = new Man();
        }
        return human;
    }
}
