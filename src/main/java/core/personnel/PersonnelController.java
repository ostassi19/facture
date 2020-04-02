package core.personnel;

import core.reglement.Reglement;
import core.reglement.ReglementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonnelController {
    @Autowired
    PersonnelServices personnelServices;

    @PostMapping("/personnel")
    public ResponseEntity<?> creatPersonnel(@RequestBody Personnel personnel){
        return personnelServices.creatPersonnel(personnel);
    }

    @GetMapping("/personnels")
    public List<Personnel> getPersonnels(){
        return personnelServices.getPersonnels();
    }


    @GetMapping("personnel/{id}")
    public ResponseEntity<?>   getOnePersonnel(@PathVariable int id ){

        return personnelServices.getOnePersonnel(id);
    }


    @DeleteMapping("/personnel/{id}")
    public ResponseEntity<?> deletePersonnel(@PathVariable int id)
    {
        return personnelServices.deletePersonnel(id);
    }
}
