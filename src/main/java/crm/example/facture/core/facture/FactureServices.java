package crm.example.facture.core.facture;

import crm.example.facture.core.commnde.Commande;
import crm.example.facture.core.commnde.CommandeRepository;
import crm.example.facture.core.personnel.Personnel;
import crm.example.facture.core.personnel.PersonnelRepository;
import crm.example.facture.core.reglement.Reglement;
import crm.example.facture.core.reglement.ReglementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crm.example.facture.utils.*;

import java.util.*;

@Service
public class FactureServices {

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    ReglementRepository reglementRepository;

    public ResponseEntity<?> creatFacture(Facture facture) {

        facture.setNbrelancement(0);
        facture = factureRepository.save(facture);
        return new ResponseEntity<>(facture, HttpStatus.OK);

    }

    public List<Facture> getFactures() {

        return factureRepository.findAll();
    }

    public ResponseEntity<?> getOneFacture(int id) {
        Optional<Facture> factureOptional=factureRepository.findById(id);

        if(!factureOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Facture not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Facture facture = factureOptional.get();

        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteFacture(int id) {
        Optional<Facture> factureOptional = factureRepository.findById(id);
        if(!factureOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("facture not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        factureRepository.deleteById(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    public ResponseEntity<?> updateFacture(int id, Facture facture) {

        Optional<Facture> factureOptional= factureRepository.findById(id);

        if(!factureOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("facture not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Facture dataBaseFacture= factureOptional.get();
//
//        if(facture.getMontant() != 0)
//            dataBaseFacture.setMontant(facture.getMontant());

        if(facture.getDatePaiement() != null)
            dataBaseFacture.setDatePaiement(facture.getDatePaiement());

        if(!facture.getCommandes().isEmpty())
        {
            List<Commande> comm = new ArrayList<Commande>();
            facture.getCommandes().forEach(
                    commande -> {
                        Optional<Commande> c = commandeRepository.findById(commande.getId());
                        Commande cc = c.get();
                        comm.add(cc);
                    });
            System.out.println("c" + comm);
            if (!comm.isEmpty())
                dataBaseFacture.setCommandes(comm);
        }

        factureRepository.save(dataBaseFacture);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    public ResponseEntity<?> relenceFacture(int id, Facture facture) {

        Optional<Facture> factureOptional= factureRepository.findById(id);

        if(!factureOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("facture not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Facture dataBaseFacture= factureOptional.get();

        if(facture.getNbrelancement() != 0){
            if(!dataBaseFacture.isPayed()) {
                float montant = dataBaseFacture.getMontant() + dataBaseFacture.getMontant_relance();
                int nb_relance = dataBaseFacture.getNbrelancement() + facture.getNbrelancement();
                dataBaseFacture.setNbrelancement(nb_relance);
                dataBaseFacture.setMontant(montant);
            }
        }
        factureRepository.save(dataBaseFacture);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
