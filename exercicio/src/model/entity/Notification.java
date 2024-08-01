package model.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Notification {

    private static AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private String title;
    private String description;

    public Notification(String title, String description){
        this.id = idCounter.getAndIncrement();
        this.title = title;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return "Titulo=" + title + ", descricao=" + description;
    }
}
