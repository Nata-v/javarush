package mvc;

import mvc.controller.Controller;
import mvc.model.FakeModel;
import mvc.model.MainModel;
import mvc.model.Model;
import mvc.model.ModelData;
import mvc.view.EditUserView;
import mvc.view.UsersView;

public class Main {    //Преимущества MVC - в любой момент можно заменить любую часть паттерна
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        editUserView.setController(controller);

        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);

        //emulate user events
        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126L);
        editUserView.fireEventUserDeleted(124L);
editUserView. fireEventUserChanged("NewName", 126L, 73);
        usersView.fireEventShowDeletedUsers();
    }
}
