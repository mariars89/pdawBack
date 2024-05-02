package com.dawes.pdaw_Maria_Rosete.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity

@Table(name="roles")
public class RolVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrol;
	@NonNull
	@Column(unique=true)
	@Enumerated(EnumType.STRING)
	private NombreRol nombre;
	
	
	

    
}

