package crm.example.facture.core.commnde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    CommandeServices commandeServices;

    @PostMapping("/add")
    public ResponseEntity<?> creatCommande(@RequestBody Commande commande){
        return commandeServices.creatCommande(commande);
    }

    @GetMapping("/all")
    public List<Commande> getCommandes(){
        return commandeServices.getCommandes();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>   getOneCommande(@PathVariable int id ){

        return commandeServices.getOneCommande(id);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteCommande(@PathVariable int id)
    {
        return commandeServices.deleteCommande(id);
    }





}
