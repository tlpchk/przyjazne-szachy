package com.ps.server.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Hibernate_sequence")
public class Hibernate_sequence {

    @Column(name = "next_val", nullable = true)
    private BigInteger next_val;
}