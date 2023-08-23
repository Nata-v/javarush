package buildtree;

import java.io.Serializable;
import java.util.*;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    // стартовый корень для дерева
    Entry<String> root;

    // коллекция для хранения элементов дерева
    private transient ArrayList<Entry<String>> queue;

    // root инициализируется по умолчанию, и становится первым элементом обозначенным как "новый корневой элемент
    // для текущего уровня дерева".
    // newLineRootElement - всегда крайний левый нижний элемент дерева.

    public CustomTree(){
        root = new Entry<String>(null);
        root.newLineRootElement = true;
        root.lineNumber = 0;
    }

    // Метод getParent принимает значение String value, ищет в древе Entry с значением elementName эквивалентным
    //  значению value, и возвращает значение elementName у найденного Entry (Entry.parent)
    // @param value elementName переданного Entry
    // @return parent.elementName найденного Entry (Entry.parent)

    public String getParent(String value){
        setValidCollection();
        String s = null;
        for (Entry<String> entry : queue){
            if (entry.lineNumber != 1){
                if (entry.elementName.equals(value)){
                    s = entry.parent.elementName;
                    break;
                }
            }
        }
        return s;
    }
    // Метод setUpCollection проходит по дереву, начиная с элемента Entry<String> root и перезаписывает все элементы в queue
// @param  root начальный элемент Entry<String> для вертикального прохода по дереву
    private void setUpCollection(Entry<String> root){
        queue = new ArrayList<>();
        Queue<Entry<String>> subQueue = new LinkedList<Entry<String>>();
        queue.add(root);
        subQueue.add(root);
        do{
            if (!subQueue.isEmpty()){
                root = subQueue.poll();
            }
            if (root.leftChild != null){
                queue.add(root.leftChild);
                subQueue.add(root.leftChild);
            }
            if (root.rightChild != null){
                queue.add(root.rightChild);
                subQueue.add(root.rightChild);
            }

        } while (!subQueue.isEmpty());
    }
    /*
    Метод getCollection  отличается от метода setUpCollection только тем, что возвращает полученную коллекцию.
    начальный элемент для вертикального прохода по дереву.
     */
    private List<Entry<String>> getCollection(Entry<String> entry){
        ArrayList<Entry<String>> list = new ArrayList<>();
        Queue<Entry<String>> subQueue = new LinkedList<Entry<String>>();
        list.add(entry);
        subQueue.add(entry);
        do{
            if (!subQueue.isEmpty()){
                entry = subQueue.poll();
            }
            if (entry.leftChild != null){
                list.add(entry.leftChild);
                subQueue.add(entry.leftChild);
            }
            if (entry.rightChild != null){
                list.add(entry.rightChild);
                subQueue.add(entry.rightChild);
            }

        } while (!subQueue.isEmpty());
        return list;
        }

    // Метод setValidCollection записывает полную коллекцию элементов дерева, а затем удаляет 1 элемент коллекции,
    // который является изначальным корнем.
    // Используется в методах remove и т.д. для валидного прохода по коллекции

    private void setValidCollection(){
        setUpCollection(root);
        queue.remove(0);
    }
    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }
    /*
    Метод indexOf возвращает индекс элемента в коллекции, или -1 если такового элемента нет.
    o - искомый элемент
     */
    @Override
    public int indexOf(Object o){
        String string = String.valueOf(o);
        setValidCollection();
        for (Entry<String> entry : queue){
            if (entry.elementName.equals(string)){
                return queue.indexOf(entry);
            }
        }
        return -1;
    }
    // Метод lastIndexOf возвращает индекс элемента в коллекции, или -1 если такого элемента нет. o - искомый элемент
    @Override
    public int lastIndexOf(Object o){
        String string = String.valueOf(o);
        setValidCollection();
        Entry<String> element = null;
        for (Entry<String> entry : queue){
            if (entry.elementName.equals(string)){
                element = entry;
            }
        }
        return queue.lastIndexOf(element);
    }
    /*
     Метод add добавляет элемент в дерево. Сначала устанавливается полная коллекция элементов, потом происходит
     итерация по элементам. При итерации каждый элемент проверяется методом checkChildren на возможность иметь
     ветви. isAvailableToAddChildren() возвращает true если такая возможность имеется. После чего создается и
     инициализируется новый элемент Entry<String>, добавляется в коллекцию. После чего коллекция заного проверяется
     и инициализируется с помощью setValidCollection().
     Если после прохода по всей коллекции ни один элемент не способен иметь новые ветви, это означает, что
     все вершины обрезаны. Тогда происходит повторная итерация по коллекции с целью поиска элемента с boolean
     флагом newLineRootElement. Найденный элемент и все последующие элементы в коллекции востанавливают возможность
     иметь потомков.
     @param s строка (String) которую необходимо добавить в коллекцию
     @return true после добавления нового элемента
     */

    @Override
    public boolean add (String s) {
        setUpCollection(root);
        for (Entry<String> entry : queue) {
            entry.checkChildren();
            if (entry.isAvailableToAddChildren()) {
                createChild(entry, s);
                setValidCollection();
                return true;
            }
        }

        boolean reAddingChildren = false;
        for (Entry<String> entry : queue) {
            if (entry.newLineRootElement) {
                reAddingChildren = true;
            }
            if (reAddingChildren) {
                entry.availableToAddLeftChildren = true;
                entry.availableToAddRightChildren = true;

            }
        }
        return add(s);
    }

    @Override
    public void add(int index, String element){
        throw new UnsupportedOperationException();
    }


    // method toArray возвращает массив elementName текущих элементов коллекции
    @Override
    public String[] toArray(){
        setValidCollection();
        int size = size();
        String[] array = new String[size];
        for (int i = 0; i < size; i++){
            array[i] = queue.get(i).elementName;
        }
        return array;
    }
    @Override
    public <T> T[] toArray(T[] a){
        int size = size();
        T[] array = a.length >= size ? a : (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<String> iterator = iterator();
        for (int i = 0; i < array.length; i++){
            if (!iterator.hasNext()){
                if (a != array){
                    return Arrays.copyOf(array, i);
                }
                array[i] = null;  // null-terminate
                return array;
            }
            array[i] = (T) iterator.next();
        }
        return array;
    }
    /*
    Метод changeNewLineRootElement меняет boolean флаг newLineRootElement с элементов, подлежащих удалению.
    Новый элемент ищется по принципу : если остались элементы справа, то выбирается соседний, если не осталось,
    то поднимаемся на уровень выше и ищем там самый левый.
    entry Entry<String> - текущий элемент являющийся newLineRootElement.
    deList - список элементов коллекции Entry<String> подлежащий удалению.

     */
    private void changeNewLineRootElement(Entry<String> entry, List<Entry<String>> deList){
        setValidCollection();
        int size = size();
        for (int i = 0; i < size; i++){
            Entry<String> newEntry = queue.get(i);
            if (newEntry == entry){
                if (i < size - 1){
                    newEntry.newLineRootElement = false;
                    for (int k = i + 1; k < size; k++){
                        Entry<String> newRootEntry = queue.get(k);
                        if (!deList.contains(newRootEntry)){
                            newRootEntry.newLineRootElement = true;
                            break;
                        }
                    }
                    //если все элементы справа подлежат удалению идем вверх по дереву
                    Entry<String> element = getUndelRoot(newEntry.parent, deList);
                    element.newLineRootElement = true;
                    break;
                }else {
                    newEntry.newLineRootElement = false;
                    Entry<String> element = getUndelRoot(newEntry.parent, deList);
                    element.newLineRootElement = true;
                    break;
                }
            }
        }
    }
    /*
    Метод getUndelRoot ищет вертикально и горизонтально первый элемент Entry<String> не входящий в лист deList
    (список элементов подлнжащих удалению)
    entry - начальный элемент Entry<String> от которого начинается поиск.
    deList - список элементов подлежащих удалению.
    метод возвращает первый найденный верхний левый элемент Entry<String> не входящий в deList/
     */
    private Entry<String> getUndelRoot(Entry<String> entry, List<Entry<String>> deList){
        for (Entry<String> element : queue){
            if (element.lineNumber == entry.lineNumber){
                if (!deList.contains(element)){
                    return element;
                }
            }
        }
        if (entry.lineNumber == 0){ // дошли до root и возвращаем его
            return entry;
        }
        return getUndelRoot(entry.parent, deList);
    }
    /*
    Доп. метод для подстраховки. При удалении иногда получается 2 элемента newLineRootElement.
    Этот метод собирает вертикально все такие элементы и снимает флаги newLineRootElement.
    entry Entry<String> со значением true переменной newLineRootElement, которая подлежит изменению.
    Метод возвращает список начиная с Entry<String> entry и заканчивая всеми его parent со значением true
    переменной newLineRootElement, которая подлежит изменению.
     */
    private List<Entry<String>> getNewLineRootElementsCollection(Entry<String> entry){
        ArrayList<Entry<String>> list = new ArrayList<>();
        list.add(entry);
        if ((entry.parent != null) && (entry.parent.newLineRootElement)){
            list.addAll(getNewLineRootElementsCollection(entry.parent));
        }

     return list;
    }


    /*
    Метод createChild создает новый элемент Entry<String> и устанавливает значение переменной parent
    @param parent родительский элемент Entry<String>.
    @param s - значение elementName для нового элемента Entry<String>.
     */
    private void createChild(Entry<String> parent, String s){
        Entry<String> newOne = new Entry<String>(s);
        newOne.parent = parent;
        newOne.lineNumber = parent.lineNumber + 1;
        setChild(parent, newOne);
    }
    /*
     Метод setChild() присваивает переменным left/rightChild родителя ссылку на элемент Entry<String> child.
     Если переменная newLineRootElement родителя имела значение true, то это значение передастся ребенку,
     у родителя изменится на false.
     @param parent родительский элемент Entry<String>.
     @param child элемент потомок Entry<String>.
     */
    private void setChild(Entry<String> parent, Entry<String> child){
        if (parent.availableToAddLeftChildren){
            parent.leftChild = child;
            parent.availableToAddLeftChildren = false;
            if (parent.newLineRootElement){
                List<Entry<String>> list = getNewLineRootElementsCollection(parent);
                for (Entry<String> entry : list){
                   entry.newLineRootElement = false;
                }
                child.newLineRootElement = true;
            }
        } else {
            parent.rightChild = child;
            parent.availableToAddRightChildren = false;
            if (parent.newLineRootElement){
                List<Entry<String>> list = getNewLineRootElementsCollection(parent);
                for (Entry<String>  entry : list){
                    entry.newLineRootElement = false;
                }
                child.newLineRootElement = true;
            }
        }
    }
    @Override
    public boolean containsAll(Collection<?> c){
        setValidCollection();
        for (Object o : c){
            if (!contains(o)){
                return false;
            }
        }
        return true;
    }
    @Override
    public String get(int index){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }

    /*
    This implementation iterates over this collection, checking each element returned by the iterator
    in turn to see if it's contained in the specified collection. If it's not so contained, it's removed
    from this collection with the iterator's remove method.
    c - коллекция элементы которой будут оставлены в текущей коллекции.
    return true если текущая коллекция подверглась изменению, иначе false.
     */
    @Override
    public boolean retainAll(Collection<?> c){
        setValidCollection();
        boolean modified = false;
        Iterator<String> iterator = iterator();
        while (iterator.hasNext()){
            if (!c.contains(iterator.next())){
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }
    /*
    This implementation iterates over the specified collection, and adds each object returned by the itarator
    to this collection, in turn.
    collection - элементы для добавления
    return true - если коллекция изменилась

     */
    @Override
    public boolean addAll(Collection<? extends String> collection){
        boolean modified = false;
        for (String line : collection){
            if (add(line)){
                modified = true;
            }
        }
        return modified;
    }

    // @return размер коллекции элементов Entry<String>
    @Override
    public int size(){
        setValidCollection();
        return queue.size();
    }

    /*
    удаляет первый найденный в дереве элемент Entry<String> со значением параметра elementName
    эквивалентного o и в случаях успеха возвращает true. Если элемент не найден возвращает false.
     o - значение строки подлежащей удалению из дерева.
     Если элемент найден и удален возвращается true, иначе false.
     */
    @Override
    public boolean remove(Object o){
        if (!(o instanceof String)){
            throw new UnsupportedOperationException();
        }
        String value = (String) o;
        setValidCollection();
        for (int i = 0; i < queue.size(); i++){
            Entry<String> entry = queue.get(i);
            if (entry.elementName.equals(value)){
                List<Entry<String>> list = getCollection(entry);
                for (Entry<String> stringEntry : list){
                    if (stringEntry.newLineRootElement){
                        changeNewLineRootElement(stringEntry, list);
                    }
                }
                if (entry.parent.leftChild == entry){
                    entry.parent.leftChild = null;
                    setValidCollection();
                    return true;
                }else {
                    entry.parent.rightChild = null;
                    setValidCollection();
                    return true;
                }
            }
        }
        return false;
    }
    /*
    Метод removeAll удаляет все элементы из текущей коллекции queue, elementName которых входит в коллекцию c.
    с - коллекция с элементами подлежащими удалению.
    return true если удалился 1 и более элементов, иначе false.
     */
    @Override
    public boolean removeAll(Collection<?> c){
        boolean modified = false;
        setValidCollection();
        for (Entry<String> entry : queue){
            if (c.contains(entry.elementName)){
                remove(entry.elementName);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public String remove(int index){
        throw new UnsupportedOperationException();
    }

    //return true если коллекция пуста
    @Override
    public boolean isEmpty(){
        setValidCollection();
        return size() == 0;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    @Override
    public void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    /*
    Removes all of the elements from this list (optional operation).
    The list will be empty after this call returns.
     */
    @Override
    public void clear(){
        root.newLineRootElement = true;
        root.availableToAddLeftChildren = true;
        root.availableToAddRightChildren = true;
        root.leftChild = null;
        root.rightChild = null;
        setValidCollection();
    }
    @Override
    public boolean contains(Object o){
        return indexOf(o) != -1;
    }
    @Override
    protected CustomTree clone()throws CloneNotSupportedException{
        CustomTree customTree = (CustomTree) super.clone();
        return customTree;
    }



    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        boolean newLineRootElement;
        Entry<T> parent, leftChild, rightChild;

        Entry (String name){
            elementName = name;
            newLineRootElement = false;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }
        private void checkChildren(){
            if (this.leftChild != null){
                availableToAddLeftChildren = false;
            }
            if (this.rightChild != null){
                availableToAddRightChildren = false;
            }
        }
        public boolean isAvailableToAddChildren(){
            return this.availableToAddRightChildren || this.availableToAddLeftChildren;
        }
    }

}