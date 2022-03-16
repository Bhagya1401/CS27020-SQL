/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import controllers.StudentJpaController;

/**
 *
 * @author nwh
 */
public class ViewStudent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewStudent s = new ViewStudent();
        s.moduleList("1012345678");
    }

    public void moduleList(String uid) {
        StudentJpaController m = new StudentJpaController();

    }
}
