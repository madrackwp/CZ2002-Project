package Timetable;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import CourseIndex.CourseIndex;

public class Timetable {
    private ArrayList<CourseIndex> coursestaken;
    private String[][] timetable;

    public Timetable(ArrayList<CourseIndex> c) {
        this.coursestaken = c;
        this.timetable = new String[6][15];
        this.timetable[0][0] = "Day";
        this.timetable[1][0] = "Monday";
        this.timetable[2][0] = "Tuesday";
        this.timetable[3][0] = "Wednesday";
        this.timetable[4][0] = "Thursday";
        this.timetable[5][0] = "Friday";
        this.timetable[0][1] = "8:30";
        this.timetable[0][2] = "9:30";
        this.timetable[0][3] = "10:30";
        this.timetable[0][4] = "11:30";
        this.timetable[0][5] = "12:30";
        this.timetable[0][6] = "13:30";
        this.timetable[0][7] = "14:30";
        this.timetable[0][8] = "15:30";
        this.timetable[0][9] = "16:30";
        this.timetable[0][10] = "17:30";
        this.timetable[0][11] = "18:30";
        this.timetable[0][12] = "19:30";
        this.timetable[0][13] = "20:30";
        this.timetable[0][14] = "21:30";
    }

    String[][] getTimetable(){
        return this.timetable;
    }

    void addIndex(CourseIndex c) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date lectStartTime = null;
        Date lectEndTime = null;
        Date lectDayStart = null;
        try {
            lectStartTime = format.parse(c.getLectureStartTime());
            lectEndTime = format.parse(c.getLectureEndTime());
            lectDayStart = format.parse("08:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long lectDiff = lectEndTime.getTime() - lectStartTime.getTime();
        long lectDiffHours = lectDiff/(60*60*1000);
        int lectExactDiffHours = (int)(lectDiffHours);
        long lectStart = lectStartTime.getTime() - lectDayStart.getTime();
        long lectStartHours = lectStart/(60*60*1000);
        int lectExactStartHours = (int)(lectStartHours);
        String lectDay = c.getLectureDay();
        switch (lectDay){
            case "Monday": 
                for (int i=1;i<lectExactDiffHours+1;i++){
                    timetable[1][lectExactStartHours+1] = (c.getCourseCode()+" Lecture");
                    lectExactStartHours++;
                }
                break;
            case "Tuesday": 
                for (int i=1;i<lectExactDiffHours+1;i++){
                    timetable[2][lectExactStartHours+1] = (c.getCourseCode()+" Lecture");
                    lectExactStartHours++;
                }
                break;
            case "Wednesday": 
                for (int i=1;i<lectExactDiffHours+1;i++){
                    timetable[3][lectExactStartHours+1] = (c.getCourseCode()+" Lecture");
                    lectExactStartHours++;
                }
                break;
            case "Thursday": 
                for (int i=1;i<lectExactDiffHours+1;i++){
                    timetable[4][lectExactStartHours+1] = (c.getCourseCode()+" Lecture");
                    lectExactStartHours++;
                }
                break;
            case "Friday": 
                for (int i=1;i<lectExactDiffHours+1;i++){
                    timetable[5][lectExactStartHours+1] = (c.getCourseCode()+" Lecture");
                    lectExactStartHours++;
                }
                break;
        }

        Date tutorialStartTime = null;
        Date tutorialEndTime = null;
        Date tutorialDayStart = null;
        try {
            tutorialStartTime = format.parse(c.getTutorialStartTime());
            tutorialEndTime = format.parse(c.getTutorialEndTime());
            tutorialDayStart = format.parse("08:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long tutorialDiff = tutorialEndTime.getTime() - tutorialStartTime.getTime();
        long tutorialDiffHours = tutorialDiff/(60*60*1000);
        int tutorialExactDiffHours = (int)(tutorialDiffHours);
        long tutorialStart = tutorialStartTime.getTime() - tutorialDayStart.getTime();
        long tutorialStartHours = tutorialStart/(60*60*1000);
        int tutorialExactStartHours = (int)(tutorialStartHours);
        String tutorialDay = c.getTutorialDay();
        switch (tutorialDay){
            case "Monday": 
                for (int i=1;i<tutorialExactDiffHours+1;i++){
                    timetable[1][tutorialExactStartHours+1] = (c.getCourseCode()+" Tutorial");
                    tutorialExactStartHours++;
                }
                break;
            case "Tuesday": 
                for (int i=1;i<tutorialExactDiffHours+1;i++){
                    timetable[2][tutorialExactStartHours+1] = (c.getCourseCode()+" Tutorial");
                    tutorialExactStartHours++;
                }
                break;
            case "Wednesday": 
                for (int i=1;i<tutorialExactDiffHours+1;i++){
                    timetable[3][tutorialExactStartHours+1] = (c.getCourseCode()+" Tutorial");
                    tutorialExactStartHours++;
                }
                break;
            case "Thursday": 
                for (int i=1;i<tutorialExactDiffHours+1;i++){
                    timetable[4][tutorialExactStartHours+1] = (c.getCourseCode()+" Tutorial");
                    tutorialExactStartHours++;
                }
                break;
            case "Friday": 
                for (int i=1;i<tutorialExactDiffHours+1;i++){
                    timetable[5][tutorialExactStartHours+1] = (c.getCourseCode()+" Tutorial");
                    tutorialExactStartHours++;
                }
                break;
        }
    }

    void printTimetable(String[][] timetable){
        for(int i = 0; i<6; i++){
            for(int j = 0; j<15; j++){
                System.out.print(timetable[i][j]+" ");
            }
            System.out.println();
        }
    }
}