package crm.example.facture.core.reglement;

import crm.example.facture.core.entreprise.Entreprise;
import crm.example.facture.core.entreprise.EntrepriseRepository;
import crm.example.facture.core.facture.Facture;
import crm.example.facture.core.facture.FactureRepository;
import crm.example.facture.core.facture.FactureServices;
import crm.example.facture.core.personnel.Personnel;
import crm.example.facture.core.personnel.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crm.example.facture.utils.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ReglementServices {

    @Autowired
    ReglementRepository reglementRepository;

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    EntrepriseRepository entrepriseRepository;

    public void reglement (Facture f, boolean etat_reglemet){
        Facture ff = new Facture();
        ff.setIsregled(true);
        ff.setEtat_reglement(etat_reglemet);
        factureRepository.save(ff);
    }
    public ResponseEntity<?> creatReglement(Reglement reglement) {

        Date d;
        d= reglement.getDelai();
        reglement.setDate(new Date());
        reglement.setDelai(d);

        float montant = 0;
        if (!reglement.getFactures().isEmpty()) {//calculer le montant du reglement s'il contient une seule facture qui
            //va etre payer d'un seul coup
            if (reglement.getMonatant() != null && reglement.getFactures().size() == 1 && reglement.isType() == true){
                montant = reglement.getMonatant();
                //reglement(, reglement.isType());// c'est bon on va annoncé que c'est une facture réglé
                reglement.getFactures().forEach(
                        facture -> {
                            Optional<Facture> facture1 = factureRepository.findById(facture.getId());

                            Facture f = facture1.get();
                            f.setEtat_reglement(true);
                            f.setIsregled(true);
                            factureRepository.save(f);
                        });
            }
            else if (reglement.getMonatant() == null// calculer le montant pour les reglement qui ont plusieurs facture
                    && reglement.getFactures().size() >= 1
                    && reglement.isType() == false ) {
                final float[] m = {0};
                reglement.getFactures().forEach(
                        facture -> {
                           Optional<Facture> facture1 = factureRepository.findById(facture.getId());

                            Facture f = facture1.get();
                            m[0] += f.getMontant();
                            f.setEtat_reglement(false);
                            f.setIsregled(true);
                            factureRepository.save(f);
                        });
                montant = m[0];
                /*System.out.println("m : "+m);
                System.out.println("m[0] : "+m[0]);
                System.out.println("montant: "+montant);*/
            }
        }
        //System.out.println(montant);
        reglement.setMonatant(montant);

        reglement = reglementRepository.save(reglement);
        return new ResponseEntity<>(reglement, HttpStatus.OK);
    }

    public List<Reglement> getReglements() {

        return reglementRepository.findAll();
    }

    public ResponseEntity<?> getOneReglement(int id) {
        Optional<Reglement> reglementOptional=reglementRepository.findById(id);

        if(!reglementOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("reglement not found");
           return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Reglement reglement = reglementOptional.get();

        return new ResponseEntity<>(reglement, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteReglement(int id) {

        Optional<Reglement> reglementOptional = reglementRepository.findById(id);
        if(!reglementOptional.isPresent()) {
           ErrorResponseModel errorResponseModel = new ErrorResponseModel ("reglement not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        reglementRepository.deleteById(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    public ResponseEntity<?> updateReglement(int id, Reglement reglement) {
        Optional<Reglement> reglementOptional= reglementRepository.findById(id);

        if(!reglementOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("reglement not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Reglement dataBaseReglement= reglementOptional.get();



        if(reglement.getDelai() != null)
            dataBaseReglement.setDelai(reglement.getDelai());

        if(reglement.getMonatant() != null)
            dataBaseReglement.setMonatant(reglement.getMonatant());

        if(!reglement.getFactures().isEmpty())
        {
            List<Facture> fact = new ArrayList<Facture>();
            reglement.getFactures().forEach(
                    facture -> {
                        Optional<Facture> f = factureRepository.findById(facture.getId());
                        Facture ff = f.get();
                        fact.add(ff);
                    });
            System.out.println("fact" + fact);
            if(!fact.isEmpty())
                dataBaseReglement.setFactures(fact);
        }
        if( reglement.getPersonnels() != null)
        {
            Optional<Personnel> personnel = personnelRepository.findById(reglement.getPersonnels().getId());
            if (personnel != null){
                dataBaseReglement.setPersonnels(personnel.get());
            }
        }



        reglementRepository.save(dataBaseReglement);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}

