package com.springboot.datajpa.app.models.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

//Serializable: Para que se pueda convertir en bytes y se pueda enviar a través de la red
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty

    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    @Email
    private String email;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Serial
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

}
