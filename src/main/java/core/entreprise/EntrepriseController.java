package core.entreprise;

import core.personnel.Personnel;
import core.personnel.PersonnelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntrepriseController {
    @Autowired
    EntrepriseServices entrepriseServices;

    @PostMapping("/entreprise")
    public ResponseEntity<?> creatEntreprise(@RequestBody Entreprise entreprise){
        return entrepriseServices.creatEntreprise(entreprise);
    }

    @GetMapping("/entreprises")
    public List<Entreprise> getEntreprises(){
        return entrepriseServices.getEntreprises();
    }


    @GetMapping("entreprise/{id}")
    public ResponseEntity<?>   getOneEntreprise(@PathVariable int id ){

        return entrepriseServices.getOneEntreprise(id);
    }


    @DeleteMapping("/entreprise/{id}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable int id)
    {
        return entrepriseServices.deleteEntreprise(id);
    }
}
