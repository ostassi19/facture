package crm.example.facture.core.facture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crm.example.facture.utils.*;

import java.util.List;
import java.util.Optional;

@Service
public class FactureServices {

    @Autowired
    FactureRepository factureRepository;

    public ResponseEntity<?> creatFacture(Facture facture) {

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

        if(facture.getMontant() != 0)
            dataBaseFacture.setMontant(facture.getMontant());

        factureRepository.save(dataBaseFacture);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
