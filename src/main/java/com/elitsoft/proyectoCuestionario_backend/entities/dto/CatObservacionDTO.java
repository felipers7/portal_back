package com.elitsoft.proyectoCuestionario_backend.entities.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


public class CatObservacionDTO {

    private  String usr2_email;
    private  String usr2_ap_pat;
    private String usr2_nom;
    private  Long usr2_id;

    private Long cat_obs_id;

    private String cat_obs_desc;
    @Id
    private Long obs_id;

    private String apr_ger;
    private String apr_tec;

    private String apr_oper;
    private String obs_desc;
    private Date obs_fec_cre;
    private Date obs_fec_mod;
    private Long usr_id_obs;      // ID del Usuario que hizo la observación
    private Long usr_id_obs_mod;   // ID del Usuario que modificó la observación

    // Campos de la entidad Usuario
    private Long usr_id;
    private  Long usr1_id;
    private String usr_nom;
    private String usr_ap_pat;
    private String usr_email;

    // Constructor que incluye todos los campos


    public CatObservacionDTO() {
    }

    public CatObservacionDTO(Long cat_obs_id, String cat_obs_desc, Long obs_id,Long usr_id, String apr_ger, String apr_tec, String apr_oper, String obs_desc,
                          Date obs_fec_cre, Date obs_fec_mod,
                          Long usr_id_obs, Long usr_id_obs_mod,
                          Long usr1_id, String usr1_nom, String usr1_ap_pat, String usr1_email,
                          Long usr2_id, String usr2_nom, String usr2_ap_pat, String usr2_email) {
        this.cat_obs_id = cat_obs_id;
        this.cat_obs_desc = cat_obs_desc;
        this.obs_id = obs_id;
        this.apr_ger = apr_ger;
        this.apr_tec = apr_tec;
        this.apr_oper = apr_oper;
        this.obs_desc = obs_desc;
        this.obs_fec_cre = obs_fec_cre;
        this.obs_fec_mod = obs_fec_mod;
        this.usr_id_obs = usr_id_obs;
        this.usr_id_obs_mod = usr_id_obs_mod;
        this.usr_id = usr_id;
        this.usr1_id = usr1_id;
        this.usr_nom = usr1_nom;
        this.usr_ap_pat = usr1_ap_pat;
        this.usr_email = usr1_email;
        this.usr2_id = usr2_id;
        this.usr2_nom = usr2_nom;
        this.usr2_ap_pat = usr2_ap_pat;
        this.usr2_email = usr2_email;
    }

    public String getUsr2_email() {
        return usr2_email;
    }

    public void setUsr2_email(String usr2_email) {
        this.usr2_email = usr2_email;
    }

    public String getUsr2_ap_pat() {
        return usr2_ap_pat;
    }

    public void setUsr2_ap_pat(String usr2_ap_pat) {
        this.usr2_ap_pat = usr2_ap_pat;
    }

    public String getUsr2_nom() {
        return usr2_nom;
    }

    public void setUsr2_nom(String usr2_nom) {
        this.usr2_nom = usr2_nom;
    }

    public Long getUsr2_id() {
        return usr2_id;
    }

    public void setUsr2_id(Long usr2_id) {
        this.usr2_id = usr2_id;
    }

    public Long getObs_id() {
        return obs_id;
    }

    public void setObs_id(Long obs_id) {
        this.obs_id = obs_id;
    }

    public String getApr_ger() {
        return apr_ger;
    }

    public void setApr_ger(String apr_ger) {
        this.apr_ger = apr_ger;
    }

    public String getApr_tec() {
        return apr_tec;
    }

    public void setApr_tec(String apr_tec) {
        this.apr_tec = apr_tec;
    }

    public String getObs_desc() {
        return obs_desc;
    }

    public void setObs_desc(String obs_desc) {
        this.obs_desc = obs_desc;
    }

    public Date getObs_fec_cre() {
        return obs_fec_cre;
    }

    public void setObs_fec_cre(Date obs_fec_cre) {
        this.obs_fec_cre = obs_fec_cre;
    }

    public Date getObs_fec_mod() {
        return obs_fec_mod;
    }

    public void setObs_fec_mod(Date obs_fec_mod) {
        this.obs_fec_mod = obs_fec_mod;
    }

    public Long getUsr_id_obs() {
        return usr_id_obs;
    }

    public void setUsr_id_obs(Long usr_id_obs) {
        this.usr_id_obs = usr_id_obs;
    }

    public Long getUsr_id_obs_mod() {
        return usr_id_obs_mod;
    }

    public void setUsr_id_obs_mod(Long usr_id_obs_mod) {
        this.usr_id_obs_mod = usr_id_obs_mod;
    }

    public Long getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(Long usr_id) {
        this.usr_id = usr_id;
    }

    public Long getUsr1_id() {
        return usr1_id;
    }

    public void setUsr1_id(Long usr1_id) {
        this.usr1_id = usr1_id;
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

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public Long getCat_obs_id() {
        return cat_obs_id;
    }

    public void setCat_obs_id(Long cat_obs_id) {
        this.cat_obs_id = cat_obs_id;
    }

    public String getCat_obs_desc() {
        return cat_obs_desc;
    }

    public void setCat_obs_desc(String cat_obs_desc) {
        this.cat_obs_desc = cat_obs_desc;
    }

    public String getApr_oper() {
        return apr_oper;
    }

    public void setApr_oper(String apr_oper) {
        this.apr_oper = apr_oper;
    }
}
