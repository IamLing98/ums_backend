package com.linkdoan.backend.View;

import com.linkdoan.backend.GAScheduleModel.GA;
import com.linkdoan.backend.GAScheduleModel.InputFromFile;
import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Room;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.model.SubjectClass;
import com.linkdoan.backend.repository.EmployeeRepository;
import com.linkdoan.backend.repository.RoomRepository;
import com.linkdoan.backend.repository.SubjectClassRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
public class ViewSchedule {

    @Autowired
    SubjectClassRepository subjectClassRepository;

    private static GA ga;
    private static final int DAY_HOURS = 12;
    private static final int DAY_NUM = 5;
    private static int ROOM_NUM;

    public static int getDAY_HOURS() {
        return DAY_HOURS;
    }

    public static int getROOM_NUM() {
        return ROOM_NUM;
    }

    public static int getDAY_NUM() {
        return DAY_NUM;
    }

    public static GA getGA() {
        return ga;
    }


    @Autowired
    EmployeeRepository employeeRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    RoomRepository roomRepository;

    public String initData() throws Exception {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream("src//main//java//com//linkdoan//backend//GAScheduleModel//ax.txt"), "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            List<Employee> employeeList = employeeRepository.findAll();
            bufferedWriter.newLine();
            for (int i = 0; i < employeeList.size(); i++) {
                bufferedWriter.write("#prof");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "id = " + employeeList.get(i).getEmployeeId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "name = " + employeeList.get(i).getFullName());
                bufferedWriter.newLine();
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }

            List<SubjectDTO> subjectDTOList = subjectRepository.getAllSubject();
            for (int i = 0; i < subjectDTOList.size(); i++) {
                bufferedWriter.write("#course");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "id = " + subjectDTOList.get(i).getSubjectId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "name = " + subjectDTOList.get(i).getSubjectName());
                bufferedWriter.newLine();
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }

            List<Room> roomList = roomRepository.findAll();
            for (int i = 0; i < roomList.size(); i++) {
                Room room = roomList.get(i);
                bufferedWriter.write("#room");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "name = " + room.getRoomId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "lab = " + (room.getIsLab() == 1 ?  "true" : "false"));
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "size = " + room.getNumberOfSeats());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "distance = " + 0);
                bufferedWriter.newLine();
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }

            List<SubjectClassDTO> subjectClassList = subjectClassRepository.getListSubjectClassByTermId("20202");
            for (int i = 0; i < subjectClassList.size(); i++) {
                SubjectClassDTO subjectClassDTO = subjectClassList.get(i);
                bufferedWriter.write("#group");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "id = " + (i+1));
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "name = " + "no name");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "size = " + subjectClassDTO.getNumberOfSeats());
                bufferedWriter.newLine();
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }

            for (int i = 0; i < subjectClassList.size(); i++) {
                SubjectClassDTO subjectClassDTO = subjectClassList.get(i);
                bufferedWriter.write("#class");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "id = " + (i+1));
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "professor = " + subjectClassDTO.getTeacherId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "course = " + subjectClassDTO.getSubjectId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "duration = " + subjectClassDTO.getDuration());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "group = " + (i+1));
                bufferedWriter.newLine();
                if(subjectClassDTO.getIsRequireLab() ==1 ){
                    bufferedWriter.write("\t" + "lab = true"  );
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return create();

    }

    public String create() throws Exception {
        try {
            InputFromFile.readFile();
        } catch (Exception ex) {

        }
        ROOM_NUM = InputFromFile.getRoomList().size();
        System.out.println("room num" +  ROOM_NUM);
        ga = new GA(80, 10, 1, 2000);
        System.out.println("prosss");
        long start = System.currentTimeMillis();
        int generation = ga.runGA();
        long end = System.currentTimeMillis();
        double time = (double) (end - start) / 1000;
//
//        lbCrossover.setText("Crossover           : " + ga.getCrossoverProbability() + " %");
//        lbMutation.setText("Mutation              : " + ga.getMutationProbability() + " %");
//        lbPopSize.setText("Population Size  : " + ga.getPopSize());
//
//        tfFitness.setText(Double.toString(ga.getBestOfBest().getFitness()));
//        tfGeneration.setText(Integer.toString(generation));
//        tfTime.setText(Double.toString(time));
//        cbPhong.setEnabled(true);
        ViewExcel excel = new ViewExcel(ga.getBestOfBest());
        excel.writeFileExcel();
        return excel.getFileName();
    }

}
