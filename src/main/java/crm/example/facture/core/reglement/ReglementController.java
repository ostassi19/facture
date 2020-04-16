package crm.example.facture.core.reglement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/reglement")
public class ReglementController {
    @Autowired
    ReglementServices reglementServices;

    @PostMapping("/add")
    public ResponseEntity<?> creatReglement(@RequestBody Reglement reglement){
        return reglementServices.creatReglement(reglement);
    }

    @GetMapping("/list")
    public List<Reglement> getReglements(){
        return reglementServices.getReglements();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>   getOneReglement(@PathVariable int id ){

        return reglementServices.getOneReglement(id);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteReglement(@PathVariable int id)
    {
        return reglementServices.deleteReglement(id);
    }

    @PutMapping("/{id}/edit")

    public ResponseEntity<?> updateReglement(@PathVariable int id,@RequestBody Reglement reglement){
        return reglementServices.updateReglement(id,reglement);
    }
}
