package crm.example.facture.core.reglement;

import crm.example.facture.core.facture.Facture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reglement")
public class Reglement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String refReglement;
    private Float monatant;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "reglement_facture",
            joinColumns = {
                    @JoinColumn(name = "ref_reglement", referencedColumnName = "ref_reglement",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ref_facture", referencedColumnName = "ref_facture",
                            nullable = false, updatable = false)})
    //private Facture facture;
      private Set<Facture> factures = new HashSet<>();*/

    // aya bb hati @JoinTable(name = "reglement_facture" : isil il table ijdida
    // joinColumns : hathi il lil entity 1 (Reglement)
    //  inverseJoinColumns : hathiya il entity 2(Factutre) behi lina ? oui cv ama 3andi sou2el
    // oui  mten na3mlou joinColumns wa mten inverseJoinColumns ?
    // non les deux m3a b3athom inti rak tya3mil fi relation manytomay (deux table " bech ta5ou min kol table il id mta3ha)
    // watha7? oui bb sur ? sous hh behi
    // hatiya : @JoinColumn(name = "ref_reglement" hathki il id mta3 il entity reglement
    // hathi @JoinColumn(name = "ref_facture" : kif kif il id mta3 nil entity Facture watha7 ? wdou7 el 8amra awel echhar
    // hhh aya bei bien
    // hathom nullable = false, updatable = false / wath7in ? nonbehi
    // nullable = false : hathiya kif ta3mil insert lil table "reglement_facture" tnajim il attribut
    //hathi "ref_reglement" wala hathi "ref_facture" (puisque 7atitha) t5alihom null fhimtni ? fhetek kifma fhemna el mar7oum
    // chma3nah ? netboled wbarra ama rahou fhemt bel7a9 wa kenek 3ala mar7oum fa ben  ali hhhhh behi
    // a7na lina malazemhomch null lazimna ni7iwouhom aka wala ? oui
    // behi hatiya tawa updatable = false : normaleùment 7asib fehmi in basit 5ater ma 5dimtich beh 9bel
    // updatable = false : mat5alikich ta3mil update ili attrubut haka fhimtnin ? oui
    // zaydin normalent? ?Oui
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reglement_facture",
            joinColumns = @JoinColumn(name = "ref_reglement"),
            inverseJoinColumns = @JoinColumn(name = "ref_facture"))
    private Set<Facture> factures = new HashSet<>();

    // aya fhimt kol chay mlanyto many ? oui bb
    // behi kamil la5ra tawa  ok
    // amani fibeli maynto one raw non at9ay9a

}
