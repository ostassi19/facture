package core.commnde;

import core.facture.Facture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String refCommande;
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "commande_facture",
            joinColumns = {
                    @JoinColumn(name = "ref_commande", referencedColumnName = "ref_commande",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ref_facture", referencedColumnName = "ref_facture",
                            nullable = false, updatable = false)})
    private Facture facture;
}
