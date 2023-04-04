package be.vdab.hateoas.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "consoles")
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal gewicht;

    public Console(String naam, BigDecimal gewicht) {
        this.naam = naam;
        this.gewicht = gewicht;
    }
    protected Console() {

    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getGewicht() {
        return gewicht;
    }
}
