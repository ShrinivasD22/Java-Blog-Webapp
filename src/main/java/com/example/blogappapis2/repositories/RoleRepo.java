package com.example.blogappapis2.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blogappapis2.entities.Role;




@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
