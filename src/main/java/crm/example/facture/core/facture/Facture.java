package crm.example.facture.core.facture;

import crm.example.facture.core.commnde.Commande;
import crm.example.facture.core.entreprise.Entreprise;
import crm.example.facture.core.personnel.Personnel;
import crm.example.facture.core.reglement.Reglement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "facture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String refFacture;
    private Date dateEmission;
    private Date datePaiement;
    private float montant;
    private int nbrelancement;

   /* @ManyToMany(mappedBy = "reglement", fetch = FetchType.LAZY)
    private Set<Reglement> reglements = new HashSet<>();
    //private Reglement reglement;*/

    /*@ManyToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    //private Commande commande;
    private Set<Commande> commandes = new HashSet<>();*/

    /*@ManyToOne(cascade = {CascadeType.PERSIST})
    private Personnel personnels;
*/

    // lina a7na n7ibou njibou les Entreprise il kol (selecet all) : @ManyToOne
    // behi
    // @JoinColumn(name = "Entreprise") : haki bech ta3mil jointure m3a Entreprise lazimha tkoun isim il class
    // kima ili declarer : private Entreprise
    // watha7a hathi ? oui wadha
    // ritha "entreprises" haki ili bech norbtou beha lil table facture "mappedBy" watha7 ?yes behi
    @ManyToOne
    @JoinColumn(name = "Entreprise")
    private Entreprise entreprises;

    @ManyToOne
    @JoinColumn(name = "Personnel")
    private Personnel personnels;//suiv many to many kahaw??one to may? yesab3thli il conception




}
