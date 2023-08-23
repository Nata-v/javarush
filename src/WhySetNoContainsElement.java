

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class WhySetNoContainsElement {
    private Set<Date> dates;
    private Date lastDate;

    public static void main(String[] args) {
        WhySetNoContainsElement element = new WhySetNoContainsElement();
        element.initializeDates();
        element.updateLastDate(3_600_000L);
        System.out.println(element.isLastDateInDates());


    }
    public boolean isLastDateInDates(){
        return dates.contains(lastDate);
    }
    private void initializeDates(){
        dates = new HashSet<>();
        lastDate = new Date(999999L);
        dates.add(lastDate);
        dates.add(new Date(2222222L));
        dates.add(new Date(3333333L));
        dates.add(new Date(4444444L));
        dates.add(new Date(5555555L));
    }
    /*
    сначала удалили обьект из сета, который нужно обновить.  Обновили, а потом добавили в сет
    если обновить обьект не удаляя, у него поменяется hash-code и мы его уже не найдем в сете
     */
    protected void updateLastDate(long delta){
        dates.remove(lastDate);
        lastDate.setTime(lastDate.getTime() + delta);
        dates.add(lastDate);
    }
}
