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

import java.time.LocalDate;
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

        facture.setDateEmission(new Date());


        float montant = 0;
        if (!facture.getCommandes().isEmpty()) {//calculer le montant du reglement s'il contient une seule facture qui
            final float[] m = {0};
            facture.getCommandes().forEach(
                    commande -> {
                        Optional<Commande> commande1 = commandeRepository.findById(commande.getId());
                        Commande f = commande1.get();
                        m[0] += f.getMontant();
                        commandeRepository.save(f);
                    });
            montant = m[0];
        }
        //System.out.println(montant);
        facture.setMontant(montant);

        facture.setPayed(false);// facture initialement non payé
        facture.setIsregled(false); //facture initialement non reglé
        facture.setEtat_reglement(false); // initialement facture  sera réglé tolalement
        facture.setNbrelancement(0);

        facture = factureRepository.save(facture);
        return new ResponseEntity<>(facture, HttpStatus.OK);

    }

    public List<Facture> getFactures() {

        return factureRepository.findAll();
    }
    public List<Facture> findByPersonne(int id) {

        Optional<Personnel> personnel=personnelRepository.findById(id);
        Personnel per = personnel.get();

        return  factureRepository.findAllByPersonnels(per);
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
        Facture f = factureOptional.get();
        // trouver le reglement correspendant à la facture qu'on va supprimer
        List<Reglement> r = reglementRepository.findAllByFactures(f);
        //Reglement rr = r.get();
        System.out.println("r :"+r);
        // boucle for pour supprimer tous les reglement correspendant à cette facture
        r.forEach(reglement -> {
            System.out.println(reglement.getId());
            reglementRepository.deleteById(reglement.getId());
        });

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

        if(!facture.getCommandes().isEmpty())// fonction permettant d'ajouter des commandes lors de la modification d'une
            //facture
        {
            List<Commande> comm = new ArrayList<Commande>();
            float montant = 0;
            final float[] m = {0};
            facture.getCommandes().forEach(
                    commande -> {
                        Optional<Commande> c = commandeRepository.findById(commande.getId());
                        Commande cc = c.get();
                        m[0] += cc.getMontant();
                        comm.add(cc);
                        System.out.println("mon" + m[0]);
                    });
            montant = m[0];
            System.out.println("c" + comm);
            if (!comm.isEmpty()){
                dataBaseFacture.setCommandes(comm);
                System.out.println("montant" + montant);
                dataBaseFacture.setMontant(montant);
            }
        }
        if(facture.getMontant_relance() != 0){
            dataBaseFacture.setMontant_relance(facture.getMontant_relance());
        }
        if(facture.getRefFacture() != null){
            dataBaseFacture.setRefFacture(facture.getRefFacture());
        }
        if(facture.getDateFacture() != null){
            dataBaseFacture.setDateFacture(facture.getDateFacture());
        }
        if(facture.getNbrelancement() != 0){
            if(!dataBaseFacture.isPayed()) {
                int nb_relance = dataBaseFacture.getNbrelancement() + 1 ; //facture.getNbrelancement();// !!
                // dataBaseFacture.getNbrelancement() +1
                dataBaseFacture.setNbrelancement(nb_relance);
                //dataBaseFacture.setMontant(montant);
            }
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
        Date f = new Date();
        System.out.println("to day is "+f);



        if(facture.getNbrelancement() != 0){
            if(!dataBaseFacture.isPayed() && f.compareTo(dataBaseFacture.getDateFacture()) > 0) {
                int nb_relance = dataBaseFacture.getNbrelancement() + facture.getNbrelancement();// !!
                // dataBaseFacture.getNbrelancement() +1
                dataBaseFacture.setNbrelancement(nb_relance);
                //dataBaseFacture.setMontant(montant);
            }
        }
        factureRepository.save(dataBaseFacture);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
