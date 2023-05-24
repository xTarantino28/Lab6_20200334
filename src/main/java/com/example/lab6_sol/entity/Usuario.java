package com.example.lab6_sol.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @NotBlank(message = "Es un campo obligatorio")
    @Size(min = 3,max = 100, message = "El nombre debe ser entre 3 y 100 caracteres")
    @Column(nullable = false)
    private String nombres;

    @NotBlank(message = "Es un campo obligatorio")
    @Size(min = 3,max = 100, message = "El apellido debe ser entre 3 y 100 caracteres")
    @Column(nullable = false)
    private String apellidos;

    @NotBlank(message = "Es un campo obligatorio")
    @Size(min = 8,max = 8, message = "El dni debe ser de 8 digitos")
    @Column(nullable = false)
    private String dni;

    @NotNull(message = "Es un campo obligatorio")
    @Digits(integer= 3, fraction= 0, message = "La edad debe ser un numero entero positivo")
    @Max(value = 120,message = "La edad debe ser un numero entero positivo no mayor a 120")
    @Min(value = 0, message = "La edad debe ser un numero entero positivo no mayor a 120")
    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private int rolid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public int getRolid() {
        return rolid;
    }

    public void setRolid(int rolid) {
        this.rolid = rolid;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}