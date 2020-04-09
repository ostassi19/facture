package crm.example.facture.core.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

        @Autowired
        NotificationServices notificationServices;

//        @PostMapping("/notification")
//        public ResponseEntity<?> createNotification(@RequestBody Notification notification){
//            return notificationServices.createNotification(notification);
//        }
//
//        @GetMapping("/notifications")
//        public List<Notification> getNotifications(){
//            return notificationServices.getNotifications();
//        }
//
//
//        @GetMapping("facture/{id}")
//        public ResponseEntity<?>   getOneFacture(@PathVariable int id ){
//
//            return factureServices.getOneFacture(id);
//        }
//
//
//        @DeleteMapping("/facture/{id}")
//        public ResponseEntity<?> deleteFacture(@PathVariable int id)
//        {
//            return factureServices.deleteFacture(id);
//        }
//
//        @PutMapping("/facture/{id}")
//        public ResponseEntity<?> updateFacture(@PathVariable int id, @RequestBody Facture facture){
//            return  factureServices.updateFacture(id,facture);
//        }
}
