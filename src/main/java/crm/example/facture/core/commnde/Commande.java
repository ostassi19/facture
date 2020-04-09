package crm.example.facture.core.commnde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private float prix_unitaire;
    private  float reduction;
    private String ref_produit;
    private String Designation_produit;
    private float tva;
    private int quantité;
    private float montant;





}
