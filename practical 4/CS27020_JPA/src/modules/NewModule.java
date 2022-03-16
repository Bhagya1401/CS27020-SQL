package modules;

import controllers.ModuleJpaController;
import controllers.exceptions.PreexistingEntityException;
import entities.Module;

public class NewModule {

    public static void main(String[] args) {
        NewModule n = new NewModule();
        n.createModule();
    }

    public void createModule() {
        // Hard coded data in place of collection from a user
        String id = "CS23820";
        String title = "C and C++";

        Module newMod = new Module();
        newMod.setCode(id);

        newMod.setTitle(title);


        ModuleJpaController c = new ModuleJpaController();

        try{
           // c.destroy(id);
            c.create(newMod);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
