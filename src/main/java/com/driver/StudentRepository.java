package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private  static HashMap<String, Student> studentMap = new HashMap<>();

    private  static HashMap<String, Teacher> teacherMap = new HashMap<>();

    private  static HashMap<String , List<String>> studentTeacherMap = new HashMap<String, List<String>>();


    public StudentRepository(HashMap<String, Student> studentMap, HashMap<String, Teacher> teacherMap, HashMap<String, List<String>> studentTeacherMap) {
        this.studentMap = studentMap;
        this.teacherMap = teacherMap;
        this.studentTeacherMap = studentTeacherMap;
    }

    public void addStudent(Student student){
        String name = student.getName();
        studentMap.put(name, student);
    }

    public void addTeacher(Teacher teacher){
        String  name = teacher.getName();
        teacherMap.put(name, teacher);
    }

    public  void addStudentTeacherPair(String student, String teacher){
        if (studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            List<String> listOfStudents = new ArrayList<>();
            if(studentTeacherMap.containsKey(teacher)){
                listOfStudents = studentTeacherMap.get(teacher);
            }
            listOfStudents.add(student);

            studentTeacherMap.put(teacher, listOfStudents);

        }
    }

    public Student getStudentByName(String name){
//        Student  s = studentMap.get(name);
//        return s;

        Student student = null;
        if (studentMap.containsKey(name)){
            return studentMap.get(name);
        }
        return student;
    }

    public  Teacher getTeacherByName(String  name){
//        return teacherMap.get(name);

        Teacher teacher = null;
        if (teacherMap.containsKey(name)){
            return teacherMap.get(name);
        }
        return  teacher;

    }

    public  List<String> getStudentsByTeacherName(String  name){
        List<String> listOfStudents = new ArrayList<>();
        if (teacherMap.containsKey(name)){
            listOfStudents = studentTeacherMap.get(name);
//            return  listOfStudents;
        }

        return  listOfStudents;
    }

    public   List<String>getAllStudents (){

        List<String> students=new ArrayList<>();
        for(String student: studentMap.keySet()){
            students.add(student);
        }
        return students;
    }

    public void deleteTeacherByName(String teacherName){
        List<String > studentsList = new ArrayList<>();
        if (studentTeacherMap.containsKey(teacherName)){
            studentsList = studentTeacherMap.get(teacherName);

            for (String student : studentsList){
                if (studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }
        }

        if (teacherMap.containsKey(teacherName)){
            teacherMap.remove(teacherName);
        }
    }


    public void deleteAllTeachers(){
        teacherMap.clear();
        for(String names:studentTeacherMap.keySet()){
            for(String students:studentTeacherMap.get(names)){
                studentMap.remove(students);
            }
        }
        studentTeacherMap.clear();
    }


}
