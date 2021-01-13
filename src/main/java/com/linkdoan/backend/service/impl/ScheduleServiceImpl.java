package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.GAScheduleModel.CourseClass;
import com.linkdoan.backend.GAScheduleModel.GA;
import com.linkdoan.backend.GAScheduleModel.Room;
import com.linkdoan.backend.GAScheduleModel.Schedule;
import com.linkdoan.backend.repository.RoomRepository;
import com.linkdoan.backend.service.ScheduleService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    RoomRepository roomRepository;

    GA ga;

    Schedule schedule;
    private static final int DAY_HOURS = 12;
    private static final int ROOM_NUM = 5;
    private final String fileName = "Schedule.xls";

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
    private void loadTableByRoom(int i, List<Room> roomList) {
        data = new Object[][]{{"ROOM : " + roomList.get(i).getName() + "\n" + (roomList.get(i).isLab() ? "lab\n" : "\n") + roomList.get(i).getNumberOfSeats(), "MON", "TUE", "WED", "THU", "FRI"},
                {"07h00 - 07h50", showLesson(i * DAY_HOURS + 0), showLesson(i * DAY_HOURS + 0 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 4 * DAY_HOURS * ROOM_NUM)},
                {"07h55 - 08h45", showLesson(i * DAY_HOURS + 1), showLesson(i * DAY_HOURS + 1 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 4 * DAY_HOURS * ROOM_NUM)},
                {"08h55 - 09h45", showLesson(i * DAY_HOURS + 2), showLesson(i * DAY_HOURS + 2 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 4 * DAY_HOURS * ROOM_NUM)},
                {"09h50 - 10h40", showLesson(i * DAY_HOURS + 3), showLesson(i * DAY_HOURS + 3 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 4 * DAY_HOURS * ROOM_NUM)},
                {"10h50 - 11h40", showLesson(i * DAY_HOURS + 5), showLesson(i * DAY_HOURS + 5 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 4 * DAY_HOURS * ROOM_NUM)},
                {"12h30 - 13h20", showLesson(i * DAY_HOURS + 6), showLesson(i * DAY_HOURS + 6 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 4 * DAY_HOURS * ROOM_NUM)},
                {"13h25 - 14h15", showLesson(i * DAY_HOURS + 7), showLesson(i * DAY_HOURS + 7 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 4 * DAY_HOURS * ROOM_NUM)},
                {"14h25 - 15h15", showLesson(i * DAY_HOURS + 8), showLesson(i * DAY_HOURS + 8 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 4 * DAY_HOURS * ROOM_NUM)},
                {"15h20 - 16h20", showLesson(i * DAY_HOURS + 9), showLesson(i * DAY_HOURS + 9 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 4 * DAY_HOURS * ROOM_NUM)},
                {"16h30 - 17h20", showLesson(i * DAY_HOURS + 10), showLesson(i * DAY_HOURS + 10 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 10 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 10 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 10 + 4 * DAY_HOURS * ROOM_NUM)},
        };
    }

    public void writeFileExcel(List<Room> roomList) {
        WritableWorkbook workbook;
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(fileName));

            // create sheet
            WritableSheet sheet1 = workbook.createSheet("AI-Genetic Algorithm", 0);

            // create Label and add to sheet
            sheet1.addCell(new Label(0, 0, "Making a Class Schedule Using a Genetic Algorithm "));

            // row begin write data
            int rowBegin = 2;
            int colBegin = 0;

            for (int x = 0; x < ROOM_NUM; x++) {
                loadTableByRoom(x, roomList);
                for (int row = rowBegin, i = 0; row < data.length + rowBegin; row++, i++) {
                    for (int col = colBegin, j = 0; col < data[0].length + colBegin; col++, j++) {
                        sheet1.addCell(new Label(col, row, (String) data[i][j]));
                    }
                }
                colBegin += data[0].length + 1;
            }
            // write file
            workbook.write();

            // close
            workbook.close();
            System.out.println("create and write success");
            JOptionPane.showMessageDialog(null, "Export Success!");
        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
            JOptionPane.showMessageDialog(null, "Error create file!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
            JOptionPane.showMessageDialog(null, "Error write file!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
            JOptionPane.showMessageDialog(null, "Error write file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public int create() {

        return 1;
    }
}
