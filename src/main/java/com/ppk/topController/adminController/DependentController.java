package com.ppk.topController.adminController;

import com.ppk.topEntity.formsEntity.DependentEntity;
import com.ppk.topService.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class DependentController {

    @Autowired
    private DependentService dependentService;

    @GetMapping("/dependents")
    public String getAllDependents(Model model) {
        // Get the current date and time for display
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | EEEE, hh:mm a");
        String formattedDate = now.format(formatter);
        model.addAttribute("currentDate", formattedDate);
        
        // Get all dependents
        List<DependentEntity> dependents = dependentService.getAllDependents();
        model.addAttribute("dependents", dependents);
        
        return "admin/dependent-dashboard";
    }

    @GetMapping("/dependents/{id}")
    public String getDependentById(@PathVariable Integer id, Model model) {
        Optional<DependentEntity> dependent = dependentService.getDependentById(id);
        
        if (dependent.isPresent()) {
            model.addAttribute("dependent", dependent.get());
            return "admin/dependent-details";
        } else {
            return "redirect:/admin/dependents";
        }
    }

    @GetMapping("/dependents/login/{loginId}")
    public String getDependentsByLoginId(@PathVariable String loginId, Model model) {
        List<DependentEntity> dependents = dependentService.getDependentsByLoginId(loginId);
        model.addAttribute("dependents", dependents);
        model.addAttribute("loginId", loginId);
        return "admin/dependent-by-login";
    }

    @GetMapping("/dependents/user/{idPengguna}")
    public String getDependentsByIdPengguna(@PathVariable String idPengguna, Model model) {
        List<DependentEntity> dependents = dependentService.getDependentsByIdPengguna(idPengguna);
        model.addAttribute("dependents", dependents);
        model.addAttribute("idPengguna", idPengguna);
        return "admin/dependent-by-user";
    }

    // REST API endpoint for getting dependent by ID
    @GetMapping("/api/dependents/{id}")
    @ResponseBody
    public DependentEntity getDependentByIdApi(@PathVariable Integer id) {
        return dependentService.getDependentById(id).orElse(null);
    }

    // REST API endpoint for getting all dependents
    @GetMapping("/api/dependents")
    @ResponseBody
    public List<DependentEntity> getAllDependentsApi() {
        return dependentService.getAllDependents();
    }

    // REST API endpoint for getting dependents by loginId
    @GetMapping("/api/dependents/login/{loginId}")
    @ResponseBody
    public List<DependentEntity> getDependentsByLoginIdApi(@PathVariable String loginId) {
        return dependentService.getDependentsByLoginId(loginId);
    }

    // REST API endpoint for getting dependents by idPengguna
    @GetMapping("/api/dependents/user/{idPengguna}")
    @ResponseBody
    public List<DependentEntity> getDependentsByIdPenggunaApi(@PathVariable String idPengguna) {
        return dependentService.getDependentsByIdPengguna(idPengguna);
    }
} 