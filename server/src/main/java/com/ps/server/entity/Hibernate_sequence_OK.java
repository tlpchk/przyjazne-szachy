package com.ps.server.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Hibernate_sequence_ok")
public class Hibernate_sequence_OK {

    @Id
    @Column(name = "ID", nullable = false)
    private BigInteger ID;

    @Column(name = "next_val", nullable = true)
    private BigInteger next_val;

    public BigInteger getID() {
        return ID;
    }

    public void setID(BigInteger ID) {
        this.ID = ID;
    }

    public BigInteger getNext_val() {
        return next_val;
    }

    public void setNext_val(BigInteger next_val) {
        this.next_val = next_val;
    }
}