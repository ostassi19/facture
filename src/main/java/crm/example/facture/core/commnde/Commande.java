package crm.example.facture.core.commnde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.persistence.*;
import java.util.Date;


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
    private float prixUnitaire;
        private  float reduction;
    private String refProduit;
    private String designationProduit;
    private float tva;
    private int quantité;
    private float montant;


}
