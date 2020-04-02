package core.reglement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReglementController {
    @Autowired
    ReglementServices reglementServices;

    @PostMapping("/reglement")
    public ResponseEntity<?> creatReglement(@RequestBody Reglement reglement){
        return reglementServices.creatReglement(reglement);
    }

    @GetMapping("/reglements")
    public List<Reglement> getReglements(){
        return reglementServices.getReglements();
    }


    @GetMapping("reglement/{id}")
    public ResponseEntity<?>   getOneReglement(@PathVariable int id ){

        return reglementServices.getOneReglement(id);
    }


    @DeleteMapping("/reglement/{id}")
    public ResponseEntity<?> deleteReglement(@PathVariable int id)
    {
        return reglementServices.deleteReglement(id);
    }
}
