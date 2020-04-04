package crm.example.facture.core.personnel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crm.example.facture.utils.*;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelServices {

    @Autowired
    PersonnelRepository personnelRepository;

    public ResponseEntity<?> creatPersonnel(Personnel personnel) {

        personnel=personnelRepository.save(personnel);
        return new ResponseEntity<>(personnel, HttpStatus.OK);
    }

    public List<Personnel> getPersonnels() {
        return personnelRepository.findAll();
    }

    public ResponseEntity<?> getOnePersonnel(int id) {
        Optional<Personnel> personnelOptional=personnelRepository.findById(id);

        if(!personnelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Personnel not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Personnel personnel = personnelOptional.get();

        return new ResponseEntity<>(personnel, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePersonnel(int id) {
        Optional<Personnel> personnelOptional = personnelRepository.findById(id);
        if(!personnelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Personnel not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        personnelRepository.deleteById(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
