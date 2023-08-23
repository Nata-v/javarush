package snakegame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Room { // основной класс программы
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

public Room(int width, int height, Snake snake){
    this.width = width;
    this.height = height;
    this.snake = snake;
    game = this;
}

    public Snake getSnake() {
        return snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
    //основной цикл программы. Здесь происходят все важные действия

    public void run(){
    //создаем обьект "наблюдатель за клавиатурой" и стартуем его
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //пока змея жива

        while (snake.isAlive()) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                //если равно символу q выйти из игры
                if (event.getKeyChar() == 'q')
                    return;

                //если стрелка влево - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);

                //если "стрелка вправо" сдвинуть фигурку вправо
             else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                snake.setDirection(SnakeDirection.RIGHT);

            //если "стрелка вверх" - сдвинуть фигурку вверх
            else if (event.getKeyCode() == KeyEvent.VK_UP)
                snake.setDirection(SnakeDirection.UP);


            //если "стрелка вниз" - сдвинуть фигурку вниз
            else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                snake.setDirection(SnakeDirection.DOWN);

        }
        snake.move();// двигаем змею
        print(); // отображаем текущее состояние игры
        sleep(); // пауза между ходами
    }
   // выводим сообщение"game over "
    System.out.println("Game Over!");
}
// выводим на экран текущее состояние игры
public void print(){
    // создаем массив куда будем рисовать текущее состояние игры
    int[][] matrix = new int[height][width];

    // рисуем все кусочки змеи
    ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>(snake.getSections());
    for (SnakeSection snakeSection : sections){
        matrix[snakeSection.getY()][snakeSection.getX()] = 1;
    }
    //рисуем голову змеи(4 - если змея мертвая)
    matrix[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;

    //рисуем мышь
    matrix[mouse.getY()][mouse.getX()] = 3;

    //выводим все это на экран
    String[] symbols = {" . ", " x ", " X ", "^_^", "RIP"};
    for (int y = 0; y < height; y++ ){
        for (int x = 0; x < width; x++){
            System.out.print(symbols[matrix[y][x]]);
        }
        System.out.println();
    }
    System.out.println();
    System.out.println();
    System.out.println();
}

//метод вызывается, когда мышь сьели
    public void eatMouse(){
    createMouse();
    }
//создает новую мышь

public void createMouse(){
    int x = (int) (Math.random() * width);
    int y = (int) (Math.random() * height);

    mouse = new Mouse(x, y);
}
public static Room game;

    public static void main(String[] args) {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }
    private int initialDelay = 520;
    private int delayStep = 20;

    //программа делает паузу, длинна которой зависит от длинны змеи

public void sleep(){
    try{
        int level = snake.getSections().size();
        int delay = level < 15 ? (initialDelay - delayStep * level) : 200;
        Thread.sleep(delay);
    }catch (InterruptedException e){

    }
}
}