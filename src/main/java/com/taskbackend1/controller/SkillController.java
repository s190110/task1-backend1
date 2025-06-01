package com.taskbackend1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taskbackend1.model.Skill;
import com.taskbackend1.repository.SkillRepository;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

    @Autowired
    private SkillRepository skillRepo;

    @GetMapping
    public List<Skill> getSkills() {
        return skillRepo.findAll();
    }

    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {
        return skillRepo.save(skill);
    }
}
