package crm.example.facture.core.facture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/facture")
public class FactureController {

    @Autowired
    FactureServices factureServices;

    @PostMapping("/add")
    public ResponseEntity<?> creatFacture(@RequestBody Facture facture){
        return factureServices.creatFacture(facture);
    }

    @GetMapping("/list")
    public List<Facture> getFactures(){
        return factureServices.getFactures();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>   getOneFacture(@PathVariable int id ){

        return factureServices.getOneFacture(id);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteFacture(@PathVariable int id)
    {
        return factureServices.deleteFacture(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> updateFacture(@PathVariable int id, @RequestBody Facture facture){
        return  factureServices.updateFacture(id,facture);
    }



}
