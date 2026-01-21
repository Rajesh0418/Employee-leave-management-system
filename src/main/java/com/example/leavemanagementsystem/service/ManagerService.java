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

    public void findManager(Manager userdata) {
        currentManager = repo.findAll().stream()
                .filter(user -> (user.getEmail().equalsIgnoreCase(userdata.getEmail()) &&
                        user.getPassword().equalsIgnoreCase(userdata.getPassword())
                ))
                .findFirst()
                .orElse(null);
        System.out.println(currentManager);
    }

    public void addManager(Manager userdata) {
        repo.save(userdata);
    }


    public void setNewPassword(String password) {
        currentManager.setPassword(password);
        repo.save(currentManager);
    }

    public List<Manager> getManagers() {
        return repo.findAll();
    }

    public Manager getManagerById(int id) {
        return repo.findById(id).orElse(null);
    }
}
