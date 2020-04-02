package core.reglement;

import core.facture.Facture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reglement")
public class Reglement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String refReglement;
    private Float monatant;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "reglement_facture",
            joinColumns = {
                    @JoinColumn(name = "ref_reglement", referencedColumnName = "ref_reglement",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ref_facture", referencedColumnName = "ref_facture",
                            nullable = false, updatable = false)})
    private Facture facture;
}
