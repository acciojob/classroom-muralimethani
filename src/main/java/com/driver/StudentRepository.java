package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private   HashMap<String, Student> studentMap = new HashMap<>();

    private   HashMap<String, Teacher> teacherMap = new HashMap<>();

    private   HashMap<String , List<String>> studentTeacherMap = new HashMap<String, List<String>>();


    public StudentRepository(HashMap<String, Student> studentMap, HashMap<String, Teacher> teacherMap, HashMap<String, List<String>> studentTeacherMap) {
        this.studentMap = studentMap;
        this.teacherMap = teacherMap;
        this.studentTeacherMap = studentTeacherMap;
    }

    //addStudent

    public void addStudent(Student student){
        String name = student.getName();
        studentMap.put(name, student);
    }


    //addTeacher
    public void addTeacher(Teacher teacher){
        String  name = teacher.getName();
        teacherMap.put(name, teacher);
    }


    //addStudentTeacherPair
    public  void addStudentTeacherPair(String student, String teacher){
        List<String> listOfStudents = new ArrayList<>();
        if (studentMap.containsKey(student) && teacherMap.containsKey(teacher)){

            if(studentTeacherMap.containsKey(teacher)){
                listOfStudents = studentTeacherMap.get(teacher);
            }
            listOfStudents.add(student);

            studentTeacherMap.put(teacher, listOfStudents);

        }
    }


    //getStudentByName
    public Student getStudentByName(String name){
//        Student  s = studentMap.get(name);
//        return s;

        Student student = null;
        if (studentMap.containsKey(name)){
            return studentMap.get(name);
        }
        return student;
    }

    // getTeacherByName

    public  Teacher getTeacherByName(String  name){
//        return teacherMap.get(name);

        Teacher teacher = null;
        if (teacherMap.containsKey(name)){
            return teacherMap.get(name);
        }
        return  teacher;

    }

//    getStudentsByTeacherName

    public  List<String> getStudentsByTeacherName(String  name){
        List<String> listOfStudents = new ArrayList<>();
        if (teacherMap.containsKey(name)){
            listOfStudents = studentTeacherMap.get(name);
//            return  listOfStudents;
        }

        return  listOfStudents;
    }


    //getAllStudents
    public   List<String>getAllStudents (){

        List<String> students=new ArrayList<>();
        for(String student: studentMap.keySet()){
            students.add(student);
        }
        return students;
    }


    //deleteTeacherByName
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


    //deleteAllTeachers

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
