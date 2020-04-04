package crm.example.facture.core.commnde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommandeController {

    @Autowired
    CommandeServices commandeServices;

    @PostMapping("/commande")
    public ResponseEntity<?> creatCommande(@RequestBody Commande commande){
        return commandeServices.creatCommande(commande);
    }

    @GetMapping("/commandes")
    public List<Commande> getCommandes(){
        return commandeServices.getCommandes();
    }


    @GetMapping("commande/{id}")
    public ResponseEntity<?>   getOneCommande(@PathVariable int id ){

        return commandeServices.getOneCommande(id);
    }


    @DeleteMapping("/commande/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable int id)
    {
        return commandeServices.deleteCommande(id);
    }





}
