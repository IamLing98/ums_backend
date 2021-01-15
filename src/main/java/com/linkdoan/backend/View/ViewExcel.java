package com.linkdoan.backend.View;

import com.linkdoan.backend.GAScheduleModel.CourseClass;
import com.linkdoan.backend.GAScheduleModel.InputFromFile;
import com.linkdoan.backend.GAScheduleModel.Room;
import com.linkdoan.backend.GAScheduleModel.Schedule;
import com.linkdoan.backend.service.impl.ScheduleServiceImpl;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class ViewExcel {

    Schedule schedule;
    private static final int DAY_HOURS = ScheduleServiceImpl.getDAY_HOURS();
    private static final int ROOM_NUM = ScheduleServiceImpl.getROOM_NUM();
    private final String path = "C://linkdoan//uploads//";
    private final String fileName = "Schedule.xls";

    public String getFileName() {
        return fileName;
    }

    // data to write file
    private Object[][] data = {
            {"ROOM : ", "MON", "TUE", "WED", "THU", "FRI"},
            {"07h00 - 07h50", null, null, null, null, null},
            {"07h55 - 08h45", null, null, null, null, null},
            {"08h55 - 09h45", null, null, null, null, null},
            {"09h50 - 10h40", null, null, null, null, null},
            {"10h50 - 11h40", null, null, null, null, null},
            {"12h30 - 13h20", null, null, null, null, null},
            {"13h25 - 14h15", null, null, null, null, null},
            {"14h25 - 15h15", null, null, null, null, null},
            {"15h20 - 16h20", null, null, null, null, null},
            {"16h30 - 17h20", null, null, null, null, null}
    };

    public ViewExcel(Schedule schedule) {
        this.schedule = schedule;
        Vector<ArrayList<CourseClass>> slots = schedule.getSlots();
        ArrayList<Room> roomArrayList =  InputFromFile.getRoomList();
        for(int i = 0; i< roomArrayList.size() ;i++){
            System.out.println("Room name: " + roomArrayList.get(i).getName());
        }
        for(int i = 0 ; i < 250; i++){
            if(slots.get(i).size() > 0){
                System.out.println("Slot: " + i + slots.get(i).get(0).getCourse().getName() + "\n");
            }
        }
        for(int i = 0 ; i < 5; i++){
            for(int j = 0 ; j < 5; j++){
                for(int k = 0 ; k < 10; k++){
                    int currentVal = i * 5 * 10 + j *10 + k;
                    if(slots.get(currentVal).size() > 0) {
//                        for(int s = 0 ; s < slots.get(currentVal).size() ; s++){
                            System.out.println("Room: " + InputFromFile.getRoomList().get(j).getName() + " Day: " + i + " Hour: " + (k + 1));
                            System.out.println(slots.get(currentVal).get(0).getCourse().getName() + "\n");

                    }
                }
            }
        }
    }

    // create and write new file *.xls
    public void writeFileExcel() {
        WritableWorkbook workbook;
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(path + fileName));

            // create sheet
            WritableSheet sheet1 = workbook.createSheet("AI-Genetic Algorithm", 0);

            // create Label and add to sheet
            sheet1.addCell(new Label(0, 0, "Making a Class Schedule Using a Genetic Algorithm "));

            // row begin write data
            int rowBegin = 2;
            int colBegin = 0;

            for (int x = 0; x < ROOM_NUM; x++) {
                loadTableByRoom(x);
                for (int row = rowBegin, i = 0; row < data.length + rowBegin; row++, i++) {
                    for (int col = colBegin, j = 0; col < data[0].length + colBegin; col++, j++) {
                        sheet1.addCell(new Label(col, row, (String) data[i][j]));
                    }
                }
                colBegin += data[0].length + 1;
            }
            workbook.write();
            workbook.close();
            System.out.println("create and write success");

        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
        }

    }

    //Hien thi thong tin cua tiet hoc
    private String showLesson(int i) {
        StringBuilder s = new StringBuilder();
        if (schedule.getSlots().get(i).size() > 0) {
            for (CourseClass cc : schedule.getSlots().get(i)) {
                s.append(cc.getId()).append("\n");
                s.append(cc.getCourse().getName()).append("\n");
                s.append(cc.getProfessor().getName()).append("\n");
                if (cc.isLabRequired()) {
                    s.append("lab\n");
                }
                s.append(cc.getNumberOfSeats());
            }
        }
        return s.toString();
    }

    @SuppressWarnings("empty-statement")
    //data tro den lich cua phong roomList[i]
    private void loadTableByRoom(int i) {
        data = new Object[][]{
                {"ROOM : " + InputFromFile.getRoomList().get(i).getName() + "\n" + (InputFromFile.getRoomList().get(i).isLab() ? "lab\n" : "\n") + InputFromFile.getRoomList().get(i).getNumberOfSeats(), "MON", "TUE", "WED", "THU", "FRI"},
                {"07h00 - 07h50", showLesson(i * DAY_HOURS + 0), showLesson(i * DAY_HOURS + 0 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 4 * DAY_HOURS * ROOM_NUM)},
                {"07h55 - 08h45", showLesson(i * DAY_HOURS + 1), showLesson(i * DAY_HOURS + 1 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 4 * DAY_HOURS * ROOM_NUM)},
                {"08h55 - 09h45", showLesson(i * DAY_HOURS + 2), showLesson(i * DAY_HOURS + 2 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 4 * DAY_HOURS * ROOM_NUM)},
                {"09h50 - 10h40", showLesson(i * DAY_HOURS + 3), showLesson(i * DAY_HOURS + 3 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 4 * DAY_HOURS * ROOM_NUM)},
                {"10h50 - 11h40", showLesson(i * DAY_HOURS + 4), showLesson(i * DAY_HOURS + 4 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 4 * DAY_HOURS * ROOM_NUM)},
                {"12h30 - 13h20", showLesson(i * DAY_HOURS + 5), showLesson(i * DAY_HOURS + 5 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 4 * DAY_HOURS * ROOM_NUM)},
                {"13h25 - 14h15", showLesson(i * DAY_HOURS + 6), showLesson(i * DAY_HOURS + 6 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 4 * DAY_HOURS * ROOM_NUM)},
                {"14h25 - 15h15", showLesson(i * DAY_HOURS + 7), showLesson(i * DAY_HOURS + 7 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 4 * DAY_HOURS * ROOM_NUM)},
                {"15h20 - 16h20", showLesson(i * DAY_HOURS + 8), showLesson(i * DAY_HOURS + 8 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 4 * DAY_HOURS * ROOM_NUM)},
                {"16h30 - 17h20", showLesson(i * DAY_HOURS + 9), showLesson(i * DAY_HOURS + 9 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 4 * DAY_HOURS * ROOM_NUM)},
        };
    }
}
