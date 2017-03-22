package babiy.reminder;

public class Task {

    private String myTask;
    private String day;
    private int id;

    public Task (){

    }

    public Task (String myTask , String day){
        this.myTask = myTask;
        this.day = day;
    }

    public String getTask () {
        return this.myTask;
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

    public void setId (int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return this.myTask;
    }
}
