package core.personnel;

import core.facture.Facture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
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

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Facture facture;


}
