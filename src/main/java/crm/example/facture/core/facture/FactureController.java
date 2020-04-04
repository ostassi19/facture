package crm.example.facture.core.facture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FactureController {

    @Autowired
    FactureServices factureServices;

    @PostMapping("/facture")
    public ResponseEntity<?> creatFacture(@RequestBody Facture facture){
        return factureServices.creatFacture(facture);
    }

    @GetMapping("/factures")
    public List<Facture> getFactures(){
        return factureServices.getFactures();
    }


    @GetMapping("facture/{id}")
    public ResponseEntity<?>   getOneFacture(@PathVariable int id ){

        return factureServices.getOneFacture(id);
    }


    @DeleteMapping("/facture/{id}")
    public ResponseEntity<?> deleteFacture(@PathVariable int id)
    {
        return factureServices.deleteFacture(id);
    }

    @PutMapping("/facture/{id}")
    public ResponseEntity<?> updateFacture(@PathVariable int id, @RequestBody Facture facture){
        return  factureServices.updateFacture(id,facture);
    }

}
