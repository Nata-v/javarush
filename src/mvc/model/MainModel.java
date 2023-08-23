package mvc.model;

import mvc.Util;
import mvc.bean.User;
import mvc.model.service.UserService;
import mvc.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model{
    //use helpful services
    private UserService userService = new UserServiceImpl();

    //use special object to keep data for view rendering
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData(){
        return modelData;
    }
    @Override
    public void loadUsers(){
        List<User> users = userService.getUsersBetweenLevels(1, 100);
        //refresh model data
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(false);
    }
    @Override
    public void loadDeletedUsers(){
        List<User> users = userService.getAllDeletedUsers();
        //refresh model data
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(true);
    }
    @Override
    public void loadUserById(long userId){
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }
    @Override
    public void deleteUserById(long id){
        userService.deleteUser(id);
        List<User> users = getAllUsers();
        //refresh model data
        modelData.setUsers(users);
    }
    @Override
    public void changeUserData(String name, long id, int level){
        userService.createOrUpdateUser(name, id, level);
        List<User> users = getAllUsers();
        //refresh model data
        modelData.setUsers(users);
    }
    private List<User> getAllUsers(){
        // model should contain all business logic in the methods
        List<User> allUsers = userService.getUsersBetweenLevels(1, 100);
        allUsers = userService.filterOnlyActiveUsers(allUsers);
        return allUsers;
    }
}
