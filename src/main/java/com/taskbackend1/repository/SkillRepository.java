package com.taskbackend1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskbackend1.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {}
