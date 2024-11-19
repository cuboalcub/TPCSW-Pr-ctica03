/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/Repository.java to edit this template
 */
package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author abisai
 */
public interface RepositoryEmpleado extends JpaRepository<Empleado, Long> {

}

