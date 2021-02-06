package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.GAScheduleModel.CourseClass;
import com.linkdoan.backend.GAScheduleModel.GA;
import com.linkdoan.backend.GAScheduleModel.InputFromFile;
import com.linkdoan.backend.GAScheduleModel.Schedule;
import com.linkdoan.backend.View.ViewExcel;
import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Room;
import com.linkdoan.backend.model.ScheduleSubjectClass;
import com.linkdoan.backend.model.SubjectClassRegistration;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    SubjectClassRepository subjectClassRepository;

    private static final int DAY_HOURS = 10;
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


    @Autowired
    EmployeeRepository employeeRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleSubjectClassRepository scheduleSubjectClassRepository;

    @Autowired
    SubjectClassRegistrationRepository subjectClassRegistrationRepository;

    @Override
    public List<com.linkdoan.backend.model.Schedule> getSchedule(String termId) {
        return scheduleRepository.findAllByTermId(termId);
    }

    @Override
    public String initData(String termId) throws Exception {
        InputFromFile inputFromFile = null;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream("src//main//java//com//linkdoan//backend//GAScheduleModel//ax.txt"), "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            System.out.println("----------GET LIST TEACHER----------");
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

            System.out.println("----------GET LIST SUBJECT----------");
            List<SubjectDTO> subjectDTOList = subjectRepository.getAllSubjectInSubjectClass();
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

            System.out.println("----------GET LIST ROOM----------");
            List<com.linkdoan.backend.model.Room> roomList = roomRepository.findAll();
            for (int i = 0; i < roomList.size(); i++) {
                Room room = roomList.get(i);
                bufferedWriter.write("#room");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "name = " + room.getRoomId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "lab = " + (room.getIsLab() == 1 ? "true" : "false"));
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "size = " + room.getNumberOfSeats());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "distance = " + 0);
                bufferedWriter.newLine();
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }


            System.out.println("----------GET LIST GROUPS----------");
            List<SubjectClassDTO> subjectClassList = subjectClassRepository.getListSubjectClassByTermId(termId);
            System.out.println("SUBJECT CLASSES SIZE: " + subjectClassList.size());
            for (int i = 0; i < subjectClassList.size(); i++) {
                SubjectClassDTO subjectClassDTO = subjectClassList.get(i);
                bufferedWriter.write("#group");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "id = " + (i + 1));
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "name = " + "no name");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "size = " + subjectClassDTO.getNumberOfSeats());
                bufferedWriter.newLine();
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }


            System.out.println("----------GET LIST CLASS----------");
            for (int i = 0; i < subjectClassList.size(); i++) {
                SubjectClassDTO subjectClassDTO = subjectClassList.get(i);
                bufferedWriter.write("#class");
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "id = " + (i + 1));
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "professor = " + subjectClassDTO.getTeacherId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "course = " + subjectClassDTO.getSubjectId());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "duration = " + subjectClassDTO.getDuration());
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "group = " + (i + 1));
                bufferedWriter.newLine();
                bufferedWriter.write("\t" + "subjectClassId = " + subjectClassDTO.getSubjectClassId());
                bufferedWriter.newLine();
                if (subjectClassDTO.getIsRequireLab() == 1) {
                    bufferedWriter.write("\t" + "lab = true");
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("#end");
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

            inputFromFile = new InputFromFile();
            inputFromFile.readFile();
            ROOM_NUM = inputFromFile.getRoomList().size();
            System.out.println("room num: " + ROOM_NUM);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return create(termId, inputFromFile);

    }

    @Override
    public List<Map<String, Object>> getScheduleInfo(Long id) {
        Optional<com.linkdoan.backend.model.Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            List<Object[]> subjectClassInfoObject = scheduleRepository.getScheduleListObject(id);
            List<Map<String, Object>> subjectClassObjectMapList = new ArrayList<>();
            String[] stringList = {"subjectId", "subjectName", "eachSubject", "departmentId",
                    "theoryNumber", "selfLearningNumber", "exerciseNumber", "discussNumber",
                    "practiceNumber", "subjectClassId", "isRequireLab", "teacherId", "duration",
                    "numberOfSeats", "mainSubjectClassId", "dayOfWeek", "hourOfDay", "roomId",
                    "fullName", "employeeId", "departmentName", "subjectType", "status"};
            for (Object[] objectArray : subjectClassInfoObject) {
                Map<String, Object> stringObjectMap = new HashMap<>();
                for (int i = 0; i < 23; i++) {
                    stringObjectMap.put(stringList[i], objectArray[i]);
                }
                Long currentOfSubmittingNumber = scheduleRepository.countSubmittedNumberBySubjectClassId(objectArray[9].toString());
                stringObjectMap.put("currentOfSubmittingNumber", currentOfSubmittingNumber);
                subjectClassObjectMapList.add(stringObjectMap);
            }
            System.out.println("subjectClassInfoObject: " +
                    subjectClassInfoObject.size());

            return subjectClassObjectMapList;
        }
        return null;
    }

    public String create(String termId, InputFromFile inputFromFile) throws Exception {
        GA ga = new GA(80, 10, 1, 2000, inputFromFile);
        System.out.println("processing....");
        long start = System.currentTimeMillis();
        int generation = ga.runGA();
        long end = System.currentTimeMillis();
        double time = (double) (end - start) / 1000;
        Schedule schedule = ga.getBestOfBest();
//
//        lbCrossover.setText("Crossover           : " + ga.getCrossoverProbability() + " %");
//        lbMutation.setText("Mutation              : " + ga.getMutationProbability() + " %");
//        lbPopSize.setText("Population Size  : " + ga.getPopSize());
//
//        tfFitness.setText(Double.toString(ga.getBestOfBest().getFitness()));
//        tfGeneration.setText(Integer.toString(generation));
//        tfTime.setText(Double.toString(time));
//        cbPhong.setEnabled(true);
        ViewExcel excel = new ViewExcel(schedule);
        excel.writeFileExcel();

        //call service and save schedule
        com.linkdoan.backend.model.Schedule schedule1 = new com.linkdoan.backend.model.Schedule();
        schedule1.setTermId(termId);
        schedule1.setCreatedDate(LocalDate.now());
        schedule1.setIsActive(0);
        com.linkdoan.backend.model.Schedule savedSchedule = scheduleRepository.save(schedule1);
        //get all slots of schedule from GA
        Vector<ArrayList<CourseClass>> slots = schedule.getSlots();
        ArrayList<com.linkdoan.backend.GAScheduleModel.Room> roomArrayList = inputFromFile.getRoomList();
        for (int i = 0; i < roomArrayList.size(); i++) {
            System.out.println("Room name: " + roomArrayList.get(i).getName());
        }
        for (int i = 0; i < 250; i++) {
            if (slots.get(i).size() > 0) {
                System.out.println("Slot: " + i + slots.get(i).get(0).getCourse().getName() + "\n");
            }
        }
        for (int i = 0; i < ROOM_NUM; i++) {
            for (int j = 0; j < DAY_NUM; j++) {
                for (int k = 0; k < 10; k++) {
                    int currentVal = i * DAY_HOURS + k + j * DAY_HOURS * ROOM_NUM;
                    if (slots.get(currentVal).size() > 0) {
                        ScheduleSubjectClass scheduleSubjectClass = new ScheduleSubjectClass();
                        String roomId = roomArrayList.get(j).getName();
                        String subjectClassId = slots.get(currentVal).get(0).getSubjectClassId();
                        Integer dayOfWeek = i + 1;
                        Integer hourOfDay = k + 1;
                        scheduleSubjectClass.setScheduleId(savedSchedule.getId());
                        scheduleSubjectClass.setRoomId(roomId);
                        scheduleSubjectClass.setSubjectClassId(subjectClassId);
                        scheduleSubjectClass.setDayOfWeek(dayOfWeek);
                        scheduleSubjectClass.setHourOfDay(hourOfDay);
                        scheduleSubjectClass.setTermId(termId);
                        scheduleSubjectClass.setMaxOfSubmittingNumber(slots.get(currentVal).get(0).getNumberOfSeats());
                        scheduleSubjectClass.setCurrentOfSubmittingNumber(0);
                        scheduleSubjectClass.setStatus(0);
                        scheduleSubjectClass.setSubjectId(slots.get(currentVal).get(0).getCourse().getId());
                        //kiem tra xem truoc do co la null hoặc cùng subject class k
                        if (currentVal < 1) {
                            scheduleSubjectClassRepository.save(scheduleSubjectClass);
                        } else {
                            if (slots.get(currentVal - 1).size() < 1) {
//                          for(int s = 0 ; s < slots.get(currentVal).size() ; s++){
                                System.out.println("Room: " + roomArrayList.get(j).getName() + " Day: " + i + " Hour: " + (k + 1));
                                System.out.println("Class id: " + slots.get(currentVal).get(0).getSubjectClassId());
                                System.out.println("Course name: " + slots.get(currentVal).get(0).getCourse().getName());
                                System.out.println("Course id: " + slots.get(currentVal).get(0).getCourse().getId());
                                scheduleSubjectClassRepository.save(scheduleSubjectClass);
                            } else {
                                if (slots.get(currentVal).get(0).getSubjectClassId().equals(slots.get(currentVal - 1).get(0).getSubjectClassId()) == false) {
                                    System.out.println("Room: " + roomArrayList.get(j).getName() + " Day: " + i + " Hour: " + (k + 1));
                                    System.out.println("Class id: " + slots.get(currentVal).get(0).getSubjectClassId());
                                    System.out.println("Course name: " + slots.get(currentVal).get(0).getCourse().getName());
                                    System.out.println("Course id: " + slots.get(currentVal).get(0).getCourse().getId());
                                    scheduleSubjectClassRepository.save(scheduleSubjectClass);
                                }
                            }
                        }
                    }
                }
            }
        }
        return excel.getFileName();
    }

    @Override
    public int update(String termId, Long scheduleId) {
        return 0;
    }

    @Override
    public int delete(String termId, Long scheduleId) {
        int count = 0;
        Optional<com.linkdoan.backend.model.Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        List<ScheduleSubjectClass> scheduleSubjectClassList = scheduleSubjectClassRepository.findAllByScheduleId(scheduleId);
        for (ScheduleSubjectClass scheduleSubjectClass : scheduleSubjectClassList) {
            scheduleSubjectClassRepository.delete(scheduleSubjectClass);
        }
        if (scheduleOptional.isPresent()) {
            com.linkdoan.backend.model.Schedule schedule = scheduleOptional.get();
            scheduleRepository.delete(schedule);
            count++;
        }
        return count;
    }
}
