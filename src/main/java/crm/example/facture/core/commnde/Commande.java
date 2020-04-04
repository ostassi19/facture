package crm.example.facture.core.commnde;

import crm.example.facture.core.facture.Facture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String refCommande;
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "commande_facture",
            joinColumns = @JoinColumn(name = "ref_commande"),
            inverseJoinColumns = @JoinColumn(name = "ref_facture"))
    private Set<Facture> factures = new HashSet<>();
}
