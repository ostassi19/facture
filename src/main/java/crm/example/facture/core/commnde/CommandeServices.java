package crm.example.facture.core.commnde;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crm.example.facture.utils.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServices {


    @Autowired
    CommandeRepository commandeRepository;


    public  ResponseEntity<?> creatCommande(Commande commande) {

        float prix;

        prix = commande.getPrix_unitaire() * commande.getQuantité() *(1- commande.getReduction() ) * (1 + commande.getTva());
        commande.setMontant(prix);
        commande.setDate(new Date());
        commande = commandeRepository.save(commande);
        return new ResponseEntity<>(commande, HttpStatus.OK);

    }



    public List<Commande> getCommandes() {

        return commandeRepository.findAll();
    }

    public ResponseEntity<?> getOneCommande(int id) {
        Optional<Commande> commandeOptional=commandeRepository.findById(id);

        if(!commandeOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Commande not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Commande destination = commandeOptional.get();

        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCommande(int id) {
        Optional<Commande> commandeOptional = commandeRepository.findById(id);
        if(!commandeOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel ("Commande not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);

        }

        commandeRepository.deleteById(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }



}
