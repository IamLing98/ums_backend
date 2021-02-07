package com.linkdoan.backend.GAScheduleModel;

import lombok.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@Data
public class InputFromFile {

    private Scanner sc;
    private ArrayList<Professor> profList = new ArrayList();
    private ArrayList<CourseClass> classList = new ArrayList();
    private ArrayList<Course> courseList = new ArrayList();
    private ArrayList<StudentsGroup> groupList = new ArrayList();
    private ArrayList<Room> roomList = new ArrayList();

    public ArrayList<Professor> getProfList() {
        return profList;
    }

    public ArrayList<CourseClass> getClassList() {
        return classList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<StudentsGroup> getGroupList() {
        return groupList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public InputFromFile(Scanner sc) throws Exception {
        this.sc = sc;
        readFile();
    }

    public InputFromFile() {

    }

    public void setScanner(Scanner sc) {
        this.sc = sc;
    }

    public Room getRoomById(int id) {
        for (Room r : roomList) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    private void readProfessor() {
        String temp;
        Professor i = new Professor();
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }
            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    i.setId(attr.get(2));
                    System.out.println(attr.get(2));
                    break;
                case "name":
                    ArrayList<String> tmpName = new ArrayList();
                    for (int j = 2; j < attr.size(); j++) {
                        tmpName.add(attr.get(j));
                    }
                    i.setName(String.join(" ", tmpName));
                    break;

            }
        }

        profList.add(i);
    }

    private void readStudentsGroup() {
        /**
         * @status: done
         */
        String temp;
        StudentsGroup i = new StudentsGroup();
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    i.setId(Integer.parseInt(attr.get(2)));
                    break;
                case "name":
                    i.setName(attr.get(2));
                    break;
                case "size":
                    i.setNumberOfStudents(Integer.parseInt(attr.get(2)));
                    break;
            }
        }

        groupList.add(i);
    }

    private void readCourse() {
        /**
         * @status: done
         */
        String temp;
        Course i = new Course();
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    i.setId(attr.get(2));
                    break;
                case "name":
                    ArrayList<String> tmpName = new ArrayList();
                    for (int j = 2; j < attr.size(); j++) {
                        tmpName.add(attr.get(j));
                    }
                    i.setName(String.join(" ", tmpName));
                    break;
            }
        }

        courseList.add(i);
    }

    private void readCourseClass() {
        String temp;
        String id = "";
        Professor tempProf = new Professor();
        Course tempCourse = new Course();
        ArrayList<StudentsGroup> tempGroups = new ArrayList();
        boolean requiresLab = false;
        int duration = 0;
        String subjectClassId = "";
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "id":
                    id = attr.get(2);
                    break;
                case "professor":
                    String idProf = attr.get(2);
                    for (Professor i : profList) {
                        if (i.getId().equals(idProf)) {
                            tempProf = i;
                        }
                    }
                    break;
                case "course":
                    String idCourse = attr.get(2);
                    for (Course i : courseList) {
                        if (i.getId().equals(idCourse)) {
                            tempCourse = i;
                        }
                    }
                    break;
                case "lab":
                    requiresLab = Boolean.parseBoolean(attr.get(2));
                    break;
                case "duration":
                    duration = Integer.parseInt(attr.get(2));
                    break;
                case "group":
                    int idGroup = Integer.parseInt(attr.get(2));
                    groupList.stream().filter((i) -> (i.getId() == idGroup)).forEach((i) -> {
                        tempGroups.add(i);
                    });
                    break;
                case "subjectClassId":
                    subjectClassId = attr.get(2);
                    break;
            }

        }
        classList.add(new CourseClass(id, tempProf, tempCourse, tempGroups, requiresLab, duration, subjectClassId));
    }

    private void readRoom() {
        String temp;
        String temp_name = new String();
        boolean temp_lab = false;
        int temp_size = 0;
        int temp_distance = 0;
        while (sc.hasNext()) {
            temp = sc.nextLine().trim();
            if (temp.equals("#end")) {
                break;
            }

            ArrayList<String> attr = new ArrayList(Arrays.asList(temp.split(" ")));

            switch (attr.get(0)) {
                case "name":
                    temp_name = attr.get(2);
                    break;
                case "lab":
                    temp_lab = Boolean.parseBoolean(attr.get(2));
                    break;
                case "size":
                    temp_size = Integer.parseInt(attr.get(2));
                    break;
                case "distance":
                    temp_distance = Integer.parseInt(attr.get(2));
            }
        }

        roomList.add(new Room(temp_name, temp_lab, temp_size, temp_distance));
    }

    public void readFile() throws FileNotFoundException {
        System.setIn(new FileInputStream("src//main//java//com//linkdoan//backend//GAScheduleModel//ax.txt"));
        Scanner sc = new Scanner(System.in);
        setSc(sc);
        String temp;
        while (sc.hasNext()) {
            temp = sc.nextLine();
            switch (temp) {
                case "#prof":
                    readProfessor();
                    break;
                case "#course":
                    readCourse();
                    break;
                case "#group":
                    readStudentsGroup();
                    break;
                case "#room":
                    readRoom();
                    break;
                case "#class":
                    readCourseClass();
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}
