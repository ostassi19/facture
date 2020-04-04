package crm.example.facture.core.facture;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Integer> {
}
