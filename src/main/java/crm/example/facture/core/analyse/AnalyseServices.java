package crm.example.facture.core.analyse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crm.example.facture.utils.*;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyseServices {

    @Autowired
    AnalyseRepository analyseRepository;

    public ResponseEntity<?> creatAnalyse(Analyse analyse) {

        analyse= analyseRepository.save(analyse);
        return new ResponseEntity<>(analyse, HttpStatus.OK);
    }

    public List<Analyse> getAnalyses() {
        return analyseRepository.findAll();
    }

    public ResponseEntity<?> getOneAnalyse(int id) {
        Optional<Analyse> analyseOptional=analyseRepository.findById(id);

        if(!analyseOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Analyse not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Analyse analyse = analyseOptional.get();

        return new ResponseEntity<>(analyse, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAnalyse(int id) {

        Optional<Analyse> analyseOptional = analyseRepository.findById(id);
        if(!analyseOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Analyse not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        analyseRepository.deleteById(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
