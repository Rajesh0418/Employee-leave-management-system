package com.example.leavemanagementsystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class Logout {

    // logout for manager
    @GetMapping("/logout")
    public String logout(SessionStatus status, Model model, HttpSession session) {
        status.setComplete();
        session.invalidate();
        return "redirect:/managerLogin.html";
    }
}
