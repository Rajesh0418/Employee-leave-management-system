package com.example.leavemanagementsystem.service;

import com.example.leavemanagementsystem.model.Manager;
import com.example.leavemanagementsystem.repository.ManagerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class ManagerService {

    @Autowired
    ManagerRepository repo;

    @Getter
    Manager currentManager;

    // getting the manager
    public void findManager(Manager userdata) {
        currentManager = repo.findAll().stream()
                .filter(user -> (user.getEmail().equalsIgnoreCase(userdata.getEmail()) &&
                        user.getPassword().equalsIgnoreCase(userdata.getPassword())
                ))
                .findFirst()
                .orElse(null);
        System.out.println(currentManager);
    }

    // saving the data of new manager
    public void addManager(Manager userdata) {
        repo.save(userdata);
    }

    // setting new password for manager
    public void setNewPassword(String password) {
        currentManager.setPassword(password);
        repo.save(currentManager);
    }

    // getting list of managers for showcasing to emp signup page
    public List<Manager> getManagers() {
        return repo.findAll();
    }

    //getting manager data by id for assigning to manager_id in emp class member
    public Manager getManagerById(int id) {
        return repo.findById(id).orElse(null);
    }
}
