package babiy.reminder;

/**
 * Created by Iгор on 18.03.2017.
 */

public class Task {

    String myTask;
    String day;

    public Task (){

    }

    public Task (String myTask , String day){
        this.myTask = myTask;
        this.day = day;
    }

    public String getTask () {
        return myTask;
    }

    public void setTask (String myTask) {
        this.myTask = myTask;
    }

    public String getDay () {
        return this.day;
    }

    public void setDay (String day) {
        this.day = day;
    }
}
