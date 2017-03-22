package babiy.reminder;

public class Task {

    private String myTask;
    private String day;

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

    @Override
    public String toString() {
        return myTask;
    }
}
