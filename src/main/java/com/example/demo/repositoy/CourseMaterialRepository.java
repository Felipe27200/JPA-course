package com.example.demo.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CourseMaterial;

@Repository
public interface CourseMaterialRepository extends JpaRepository <CourseMaterial, Long>
{

}
