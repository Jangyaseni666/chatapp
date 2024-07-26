package com.jangyaseni.chatapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        repo.save(user);
    }

    public void disconnect(User user){
        var storedUser = repo.findById(user.getNickName())
                .orElse(null);
        if(storedUser!=null){
            user.setStatus(Status.OFFLINE);
            repo.save(user);
        }

    }

    public List<User> findConnectedUsers(){
        return repo.findAllByStatus(Status.ONLINE);
    }

}
