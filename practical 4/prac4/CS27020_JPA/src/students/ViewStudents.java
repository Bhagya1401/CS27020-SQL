/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students;

import entities.Student;
import controllers.StudentJpaController;

import java.util.List;

/**
 *
 * @author nwh
 */
public class ViewStudents {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewStudents s = new ViewStudents();
        s.printAll();
    }

    public void printAll() {
        StudentJpaController c = new StudentJpaController();
        List<Student> students = c.findStudentEntities();

        for (Student s : students) {
            System.out.print(s.getId() + "\t");
            System.out.print(s.getName() + "\t");
            System.out.println(s.getYear());
        }
    }
}
