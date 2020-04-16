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
    private Date dateEmission;
    private Date datePaiement;
    private float montant;
    private int nbrelancement;
    private boolean etat;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "commande_facture",
            joinColumns = @JoinColumn(name = "ref_facture"),
            inverseJoinColumns = @JoinColumn(name = "ref_commande"))
    private List<Commande> commandes = new ArrayList<Commande>();


    /*@Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("id",this.id);
        JSONArray subArray = new JSONArray();
        this.commandes.forEach(sub->{
            JSONObject subJson = new JSONObject();
            subJson.put("id", sub.getId());
            subArray.appendElement(subJson);
        });
        jsonInfo.put("Commande", subArray);
        info = jsonInfo.toString();
        return info;
    }*/

}
