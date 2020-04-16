package crm.example.facture.core.analyse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/analyse")
@RestController
public class AnalyseController {

    @Autowired
    AnalyseServices analyseServices;

    @PostMapping("/add")
    public ResponseEntity<?> creatAnalyse(@RequestBody Analyse analyse){
        return analyseServices.creatAnalyse(analyse);
    }

    @GetMapping("/all")
    public List<Analyse> getAnalyses(){
        return analyseServices.getAnalyses();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>   getOneAnalyse(@PathVariable int id ){

        return analyseServices.getOneAnalyse(id);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteAnalyse(@PathVariable int id) {
        return analyseServices.deleteAnalyse(id);
    }
}
