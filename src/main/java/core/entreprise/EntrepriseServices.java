package core.entreprise;

import core.personnel.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utils.ErrorResponseModel;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseServices {

    @Autowired
    EntrepriseRepository entrepriseRepository;

    public ResponseEntity<?> creatEntreprise(Entreprise entreprise) {

         entreprise = entrepriseRepository.save(entreprise);
         return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }

    public List<Entreprise> getEntreprises() {

        return entrepriseRepository.findAll();
    }

    public ResponseEntity<?> getOneEntreprise(int id) {

        Optional<Entreprise> entrepriseOptional=entrepriseRepository.findById(id);

        if(!entrepriseOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("ENtreprise not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Entreprise entreprise = entrepriseOptional.get();

        return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteEntreprise(int id) {

        Optional<Entreprise> entrepriseOptional = entrepriseRepository.findById(id);
        if(!entrepriseOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Entreprise not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        entrepriseRepository.deleteById(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
