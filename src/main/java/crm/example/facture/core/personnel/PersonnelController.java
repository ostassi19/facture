package crm.example.facture.core.personnel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/personnel")
public class PersonnelController {
    @Autowired
    PersonnelServices personnelServices;

    @PostMapping("/add")
    public ResponseEntity<?> creatPersonnel(@RequestBody Personnel personnel){
        return personnelServices.creatPersonnel(personnel);
    }

    @GetMapping("/all")
    public List<Personnel> getPersonnels(){
        return personnelServices.getPersonnels();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>   getOnePersonnel(@PathVariable int id ){

        return personnelServices.getOnePersonnel(id);
    }



    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deletePersonnel(@PathVariable int id)
    {
        return personnelServices.deletePersonnel(id);
    }
}
