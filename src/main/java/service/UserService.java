package service;

import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    /* хранилище данных */
    private static UserService userService;

    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());

    public static synchronized UserService getUserService(){ // Singlon реализация
        if (userService == null){
            userService = new UserService();
        }
        return userService;
    }

    private UserService(){ // запрещаем создавать объекты класса

    }


    public List<User> getAllUsers() {
        return new ArrayList<User>(dataBase.values());
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }

    public boolean addUser(User user) {
        if (isExistsThisUser(user)) {
            return false;
        } else {
        dataBase.put(maxId.get(), new User(maxId.getAndAdd(1), user.getEmail(), user.getPassword()));
            System.out.println(maxId.get());
            System.out.println(dataBase.toString());
        return true;
        }
    }

    public void deleteAllUser() {
        dataBase.clear();
    }

    public boolean isExistsThisUser(User user) {
        return dataBase.containsValue(user);
    }

    public List<User> getAllAuth() {
        return new ArrayList<User>(authMap.values());
    }

    public boolean authUser(User user) {
        System.out.println(dataBase.toString());
        if (!isExistsThisUser(user)) {
            System.out.println("polzovat-net");
            return false;
        } else {
            ArrayList<User> list = (ArrayList<User>) userService.getAllUsers();

            for (User use: list) {
                if (use.equals(user)){
                    authMap.put(use.getId(), use);
                    //System.out.println(true);4
                  //System.out.println(user.getId());
                    System.out.println(authMap.toString());
                    return true;

                }
            }
            System.out.println(false);
            return false;

        }
    }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {
        System.out.println("isUserAuthById -----" +  id);
        System.out.println(authMap.toString());
        return authMap.containsKey(id);
    }

}
