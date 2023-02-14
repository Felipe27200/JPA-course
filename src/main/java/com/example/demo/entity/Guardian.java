package com.example.demo.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Embeddable: Define a una entidad cuyos datos están 
 * relacionados a otra tabla, pero aquí no se crea una 
 * nueva tabla para almacenar esos datos, por el contrario
 * la entidad representa a esas columnas de la tabla y así
 * se pueden modularizar y dar un procedimiento especial 
 * para estos datos.
 * */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

/*
 * Se establece(n) la(s) columna(s) a la que hará referencia
 * de la tabla y así la entidad insertará los datos en las 
 * columnas de esa tabla, en este caso Guardian inserta los 
 * datos en la tabla tbl_student.
 * */
@AttributeOverrides({
	@AttributeOverride(
			// <<< OBVIAMENTE DEBE HACER REFERENCIA A LAS COLUMNAS
			// 	    DE LA TABLA EN LA BD >>>
			/* Columna de la clase actual de la que se tomaran los datos*/
			name = "name",
			// Columna en Student en dónde se almacenarán esos datos.
			column = @Column(name = "guardian_name")
	),
	@AttributeOverride(
			name = "email",
			column = @Column(name = "guardian_email")
	),
	@AttributeOverride(
			name = "mobile",
			column = @Column(name = "guardian_mobile")
	)
})
public class Guardian {
	private String name;
	private String email;
	private String mobile;
}
