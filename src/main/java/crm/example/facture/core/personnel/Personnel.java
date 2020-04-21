package crm.example.facture.core.personnel;

import crm.example.facture.core.entreprise.Entreprise;
import crm.example.facture.core.facture.Facture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cin;
    private Date ddn;
    private int nb_enfant;
    private String situation_familiale;
    private String nom;
    private String prenom;
    private String adresse_personnel;
    private String adresse_professionel;

    @ManyToOne
    @JoinColumn(name = "Entreprise")
    private Entreprise entreprises;


    /*@OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    private List<Facture> factures;*/
    /*@OneToMany( fetch = FetchType.LAZY ,mappedBy = "personnels")
    private Collection<Facture> factures;*/


}
