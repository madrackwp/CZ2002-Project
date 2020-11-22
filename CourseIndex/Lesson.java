package CourseIndex;

public class Lesson {
    private String startTime;
    private String endTime;
    private Type type;
    private Day day;

    public Lesson(String startTime, String endTime, Type type, Day day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.day = day;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Type getType() {
        return this.type;
    }

    public String getTypeToString() {
        return this.type.toString();
    }

    public Day getDay() {
        return this.day;
    }

    public String getDayToString() {
        return this.day.toString();
    }

}
