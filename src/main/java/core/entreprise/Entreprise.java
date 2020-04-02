package core.entreprise;

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
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String id_fiscal;
    private Date date_creation;
    private int nb_employer;
    private String forme_juridique;
    private String raison_social;
    private String capital;
    private String siege;
    private String code_postal;
    private String adresse_mail;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Facture facture;
}
