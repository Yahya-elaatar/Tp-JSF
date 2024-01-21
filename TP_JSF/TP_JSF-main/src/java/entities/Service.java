/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author yaxay
 */

@Entity
public class Service implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    @OneToMany(mappedBy = "service",fetch = FetchType.LAZY)
    private List<Employee> employes;

    public Service(String nom, List<Employee> employes) {
        this.nom = nom;
        this.employes = employes;
    }

    public Service(int id, String nom, List<Employee> employes) {
        this.id = id;
        this.nom = nom;
        this.employes = employes;
    }

    public List<Employee> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employee> employes) {
        this.employes = employes;
    }

    public Service(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Service() {
    }

    public Service(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nom=" + nom + '}';
    }
    

}
