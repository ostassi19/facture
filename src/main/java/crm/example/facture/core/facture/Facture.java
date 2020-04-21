package crm.example.facture.core.facture;
import crm.example.facture.core.commnde.Commande;
import crm.example.facture.core.entreprise.Entreprise;
import crm.example.facture.core.personnel.Personnel;
import crm.example.facture.core.reglement.Reglement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.persistence.*;
import java.util.*;


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
    private Date dateEmission;// wa9thech tsan3et
    private Date datePaiement;// wa9thech 5olset
    private Date dateFacture;// wa9thch bech to5les
    private float montant;
    private float montant_relance;
    private int nbrelancement;
    private boolean etat_reglement;/// type reglement : total, partiel
    private boolean Payed;
    private boolean isregled;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "commande_facture",
            joinColumns = @JoinColumn(name = "ref_facture"),
            inverseJoinColumns = @JoinColumn(name = "ref_commande"))
    private List<Commande> commandes = new ArrayList<Commande>();


    @ManyToOne
    @JoinColumn(name = "Personnel")
    private Personnel personnels;


}
