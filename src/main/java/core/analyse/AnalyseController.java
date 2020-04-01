package core.analyse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnalyseController {

    @Autowired
    AnalyseServices analyseServices;

    @PostMapping("/analyse")
    public ResponseEntity<?> creatAnalyse(@RequestBody Analyse analyse){
        return analyseServices.creatAnalyse(analyse);
    }

    @GetMapping("/analyses")
    public List<Analyse> getAnalyses(){
        return analyseServices.getAnalyses();
    }


    @GetMapping("analyse/{id}")
    public ResponseEntity<?>   getOneAnalyse(@PathVariable int id ){

        return analyseServices.getOneAnalyse(id);
    }


    @DeleteMapping("/analyse/{id}")
    public ResponseEntity<?> deleteAnalyse(@PathVariable int id) {
        return analyseServices.deleteAnalyse(id);
    }
}
