package dinamicproxy;

import java.lang.reflect.Proxy;

public class Main {
        public static void main(String[] args) {
            //создаем оригинальный обьект
            Man vasia = new Man("Vasia", 30, "Sait-Petersburg", "Russia");

            //получаем загрузчик класса у оригинального обьекта
            ClassLoader vasiaClassLoader = vasia.getClass().getClassLoader();

            //получаем все интерфейсы, которые реализует оригинальный обьект
            Class[] interfaces = vasia.getClass().getInterfaces();

            //создаем прокси нашего обьекта vasia именно для интерфейса Person
            //создание обьектов прокси происходит на уровне интерфейсов, а не классовюПрокси создается для интерфейса!!!
            Person proxyVasia = (Person) Proxy.newProxyInstance(vasiaClassLoader, interfaces, new PersonInvocationHandler(vasia));

            //вызываем у прокси обьекта один из методов нашего оригинального обьекта
            proxyVasia.introduce(vasia.getName());
        }
    }

