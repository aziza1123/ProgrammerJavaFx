/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

/**
 *
 * @author USER
 */
public class Ticket {
   int id;
   String name , description , type , qte , prix;
   int  event_id;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Ticket(String name, String description, String type, String qte, String prix, int event_id) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.qte = qte;
        this.prix = prix;
        this.event_id = event_id;
    }
   
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Ticket(String name, String description, String type, String qte, String prix) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.qte = qte;
        this.prix = prix;
    }

    public Ticket() {
    }

//    public Ticket(int id, String name, String description, String type, String qte, String prix, Event id_event) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.type = type;
//        this.qte = qte;
//        this.prix = prix;
//        this.id_event = id_event;
//    }
//
//    public Event getId_event() {
//        return id_event;
//    }
//
//    public void setId_event(Event id_event) {
//        this.id_event = id_event;
//    }
//    

    @Override
    public String toString() {
        return "Ticket{" + "name=" + name + ", description=" + description + ", type=" + type + ", qte=" + qte + ", prix=" + prix + '}';
    }
    
    
    
}
