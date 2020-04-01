package core.facture;

import core.reglement.Reglement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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

    @ManyToMany(mappedBy = "reglement", fetch = FetchType.LAZY)
    private Reglement R;
}
