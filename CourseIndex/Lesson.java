package CourseIndex;

/**
 * Lesson is an object within the CourseIndex object that stores information of
 * what types of lesson a student will be taking for a particular course index
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class Lesson {
    /**
     * The lesson start time in the 24 hr format HH:MM (min 08:30 max 23:30 )
     */
    private String startTime;
    /**
     * The lesson end time in the 24 hr format HH:MM (min 08:30 max 23:30 )
     */
    private String endTime;

    /**
     * The lesson type (Lecture, Lab, Tutorial)
     */
    private Type type;
    /**
     * The Day of the lesson (MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY)
     */
    private Day day;

    /**
     * Creates the lesson
     * 
     * @param startTime start time of the lesson
     * @param endTime   end time of the lesson
     * @param type      type of lesson
     * @param day       day of the lesson
     */
    public Lesson(String startTime, String endTime, Type type, Day day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.day = day;
    }

    /**
     * Gets the lesson's starting time
     * 
     * @return this Lesson's startTime
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the lesson's ending time
     * 
     * @return this Lesson's endTime
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the lesson's type (Lecture, Lab, Tutorial)
     * 
     * @return
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Returns the lesson type as a string
     * 
     * @return The type of lesson as a String
     */
    public String getTypeToString() {
        return this.type.toString();
    }

    /**
     * Gets the day of the lesson (MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY)
     * 
     * @return The day of the lesson
     */
    public Day getDay() {
        return this.day;
    }

    /**
     * Get the day of the lesson as a string
     * 
     * @return The day of the lesson as a string
     */
    public String getDayToString() {
        return this.day.toString();
    }

}
