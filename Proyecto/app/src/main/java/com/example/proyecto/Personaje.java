package com.example.proyecto;

public class Personaje {
    String nombre,edad,descripcion,idPersonaje;

    public Personaje() {
    }

    public Personaje(String idPersonaje, String nombre, String edad, String descripcion) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.edad = edad;
        this.descripcion = descripcion;
    }
    public String getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(String idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
