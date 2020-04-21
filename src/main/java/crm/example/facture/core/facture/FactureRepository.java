package crm.example.facture.core.facture;

import crm.example.facture.core.personnel.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface
FactureRepository extends JpaRepository<Facture,Integer> {

    List<Facture> findAllByPersonnels(Personnel personnel);
}
