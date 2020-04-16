package crm.example.facture.core.entreprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {
    @Autowired
    EntrepriseServices entrepriseServices;

    @PostMapping("/add")
    public ResponseEntity<?> creatEntreprise(@RequestBody Entreprise entreprise){
        return entrepriseServices.creatEntreprise(entreprise);
    }

    @GetMapping("/all")
    public List<Entreprise> getEntreprises(){
        return entrepriseServices.getEntreprises();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>   getOneEntreprise(@PathVariable int id ){

        return entrepriseServices.getOneEntreprise(id);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEntreprise(@PathVariable int id)
    {
        return entrepriseServices.deleteEntreprise(id);
    }
}
