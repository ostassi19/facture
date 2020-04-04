package crm.example.facture.core.commnde;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Integer> {
}
