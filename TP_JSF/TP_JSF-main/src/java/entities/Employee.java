/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author yaxay
 */

@Entity
public class Employee implements Serializable{
    
        @Id
    @GeneratedValue
    public int id;
    private String nom;
    private String prenom;
    private Date datenaissance;
    private String photo;
    @ManyToOne
    private Service service;
    @ManyToOne
    private Employee manager;

    public Employee() {
    }

    public Employee(int id, String nom, String prenom, Date datenaissance, String photo, Service service, Employee manager) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.photo = photo;
        this.service = service;
        this.manager = manager;
    }

    public Employee(String nom, String prenom, Date datenaissance, Service service, Employee manager) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.service = service;
        this.manager = manager;
    }

    public Employee(String nom, String prenom, Date datenaissance, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

 

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", datenaissance=" + datenaissance + ", photo=" + photo + ", service=" + service + ", manager=" + manager + '}';
    }

   
    
    
    
}
