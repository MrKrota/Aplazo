package com.caldelas.retoaplazo.entity;

import javax.persistence.*;

@Entity
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String input;

    @Lob
    private String output;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String firstName) {
        this.input = firstName;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String lastName) {
        this.output = lastName;
    }
}
