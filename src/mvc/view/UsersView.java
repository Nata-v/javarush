package mvc.view;

import mvc.bean.User;
import mvc.controller.Controller;
import mvc.model.ModelData;

public class UsersView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData){
        System.out.println("All users:");
        for (User user : modelData.getUsers()){
            System.out.println("\t" + user);
        }
        System.out.println("=================================");
    }
    @Override
    public void setController(Controller controller){
        this.controller = controller;
    }
    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }
    public void fireEventShowDeletedUsers(){
        controller.onShowAllDeletedUsers();
    }
    public void fireEventOpenUserEditForm(long id){
        controller.onOpenUserEditForm(id);
    }
}
