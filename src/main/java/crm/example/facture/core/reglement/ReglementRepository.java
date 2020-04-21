package crm.example.facture.core.reglement;

import crm.example.facture.core.facture.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReglementRepository extends JpaRepository<Reglement,Integer> {
    List<Reglement> deleteAllByFactures(Facture f);
    List<Reglement> findAllByFactures(Facture f);
}
