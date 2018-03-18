package application.service;

import application.model.User;
import application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // add (register)
    public boolean addUser(User user)
    {
        try{
            userRepo.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //delete (manage my account)
    public boolean deleteUser(String username)
    {
        User user = null;
        try{
            user = userRepo.findByUsername(username);
            userRepo.delete(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //update (manage my account)
    public boolean updateUser(String username, User updated)
    {
        User user = null;
        try{
            user = userRepo.findByUsername(username);
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
            user.setPhone(updated.getPhone());
            userRepo.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //view (manage my account) -> sa vada detaliile contului
    public User viewUser(String username)
    {
        User user = null;
        try{
            user = userRepo.findByUsername(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
