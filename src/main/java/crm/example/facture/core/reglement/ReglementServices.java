package crm.example.facture.core.reglement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crm.example.facture.utils.*;
import java.util.List;
import java.util.Optional;

@Service
public class ReglementServices {

    @Autowired
    ReglementRepository reglementRepository;


    public ResponseEntity<?> creatReglement(Reglement reglement) {

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

}

