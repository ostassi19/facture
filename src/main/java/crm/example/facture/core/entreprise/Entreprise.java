package crm.example.facture.core.entreprise;

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


    // hathiya tawa mappedBy = "entreprises": fhimtha ? oui deja haki ella 3melenalha declaration bekri aya behi bien
    // Collection<Facture> : fahmitou ou non ? chniya Collection behi aki kima il tableau juste bech yefhim il haki entity
    // 7aja kima hika moufid dima t7otha watha7 ? oui monsieur hhh behi cava tawa ?? oui cv //tnajim tkamil ? one to many
    // non les reste ? 7atta many to many non  amani pour la 1000 fois n9olik yehdik emchi pas  a pas ok mreguel
    // bekri tay7a tektib w ani nlaji bi souri la7tha o5ra w nasker il anydesk fahmik chnouwa t7eb t9oul chwaya saber
    // behi les commentaire hathom 5alihom w kamil les reste mta3 il mayto one w kol wa7da te5dimha run thbet faha kani cava
    // 9adim sinon non
    // pas a pas ok ? oki ntaba3 fik aa bahi kamila ya
    /*@OneToMany( fetch = FetchType.LAZY ,mappedBy = "entreprises")
    private Collection<Facture> factures;*/
}
