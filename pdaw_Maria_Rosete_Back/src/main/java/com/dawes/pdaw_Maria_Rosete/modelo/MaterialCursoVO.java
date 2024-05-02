package com.dawes.pdaw_Maria_Rosete.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "materialcursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MaterialCursoVO {

    public enum TipoMaterial {
        PDF(0),
        VIDEO(1),
        PRESENTACION(2),
        ENLACE(3),
        OTRO(4);

        private final int value;

        TipoMaterial(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmaterialCurso;

    @NonNull
    @Column(unique = true)
    private String nombre;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private TipoMaterial tipoMaterial;

    @NonNull
    private String url;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "curso_idcurso") // Nombre de la columna que referencia al curso en materialcursos
    private CursoVO curso;

    public MaterialCursoVO(@NonNull String nombre, @NonNull TipoMaterial tipoMaterial, @NonNull String url) {
        this.nombre = nombre;
        this.tipoMaterial = tipoMaterial;
        this.url = url;
    }
}
