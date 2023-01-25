package com.example.demo.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

/*
 * Esto provee los métodos necesarios para el manejo de los datos
 * en la BD, los parámetros de tipo, encerrados en <>, hacen 
 * referencia al tipo de la entidad (Student) y al tipo del id de esa entidad (id),
 * respectivamente.
 * 
 * Como se recordará no es necesario implementar está interfaz en ninguna
 * clase externa para hacer uso de ella.
 * */
@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

}
