
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author Elitsoft83
 */
@Entity
@Table(name = "TBL_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usr_id;
    
    private String usr_rut;
    private String usr_nom;
    private String usr_ap_pat;
    private String usr_ap_mat;
    private String usr_email;
    private String usr_pass;
    private String usr_tel;
    private String usr_url_link;
    
    // Relación muchos a uno con la entidad Pais
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id") // Nombre de la columna que será clave foránea
    private Pais pais;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Herramienta> herramientas;
   

    public Usuario(){

    }

    public Long getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(Long usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsr_rut() {
        return usr_rut;
    }

    public void setUsr_rut(String usr_rut) {
        this.usr_rut = usr_rut;
    }

    public String getUsr_nom() {
        return usr_nom;
    }

    public void setUsr_nom(String usr_nom) {
        this.usr_nom = usr_nom;
    }

    public String getUsr_ap_pat() {
        return usr_ap_pat;
    }

    public void setUsr_ap_pat(String usr_ap_pat) {
        this.usr_ap_pat = usr_ap_pat;
    }

    public String getUsr_ap_mat() {
        return usr_ap_mat;
    }

    public void setUsr_ap_mat(String usr_ap_mat) {
        this.usr_ap_mat = usr_ap_mat;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_pass() {
        return usr_pass;
    }

    public void setUsr_pass(String usr_pass) {
        this.usr_pass = usr_pass;
    }

    public String getUsr_tel() {
        return usr_tel;
    }

    public void setUsr_tel(String usr_tel) {
        this.usr_tel = usr_tel;
    }

    public String getUsr_url_link() {
        return usr_url_link;
    }

    public void setUsr_url_link(String usr_url_link) {
        this.usr_url_link = usr_url_link;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Herramienta> getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(List<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    
}
