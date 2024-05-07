package expeditors.backend.controller;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.adoption.FilterDTO;
import expeditors.backend.service.AdopterService;
import expeditors.backend.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/adopter")
public class AdopterController {
    private AdopterService adopterService;
    private UriCreator uriCreator;

    public AdopterController(AdopterService adopterService, UriCreator uriCreator) {
        this.adopterService = adopterService;
        this.uriCreator = uriCreator;
    }

    @GetMapping(path = "/holamundo")
    public String getHolaMundo() {
        return "Hola mundo!!!";
    }

    @PostMapping
    public ResponseEntity<?> addAdopter(@RequestBody Adopter adopter) {
        Adopter addNewAdopter = adopterService.addAdopter(adopter);

        URI uri = uriCreator.getUriFor(addNewAdopter.getId());

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{idAdopter:\\d+}")
    public ResponseEntity<?> deleteAdopter(@PathVariable("idAdopter") int idAdopter) {
        boolean result = adopterService.deleteAdopter(idAdopter);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No exist Adopter: " + idAdopter);
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdopter(@RequestBody Adopter adopter) {
        boolean result = adopterService.updateAdopter(adopter);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No exist adopter id: " + adopter.getId());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("getAdopterById/{idAdopter:\\d+}")
    public ResponseEntity<?> getAdopterById(@PathVariable("idAdopter") int idAdopter) {
        Adopter adopter =  adopterService.getAdopterById(idAdopter);
        if(adopter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No found adopter id: " + idAdopter);
        }
        return ResponseEntity.ok(adopter);
    }

    @GetMapping(path = "/getAllAdopters")
    public ResponseEntity<?> getAllAdopters() {
        List<Adopter> adopters =  adopterService.getAllAdopters();
        return ResponseEntity.ok(adopters);
    }

    @GetMapping("getAdopterFilterByName/{nameAdopter}")
    public ResponseEntity<?> getAdopterFilterByName(@PathVariable("nameAdopter")  String nameAdopter){
        List<Adopter> adopters = adopterService.getAdopterFilterByName(nameAdopter);
        return ResponseEntity.ok(adopters);
    }

    @GetMapping("getAdopterByName")
    public ResponseEntity<?> getAdopterByName(){
        List<String> adopters = adopterService.getAdopterByName();
        return ResponseEntity.ok(adopters);
    }

    @GetMapping("getOrderByName")
    public ResponseEntity<?> getOrderByName(){
        List<Adopter> adopters = adopterService.getOrderByName();
        return ResponseEntity.ok(adopters);
    }

    @GetMapping("getOrderByDate")
    public ResponseEntity<?>  getOrderByDate(){
        List<Adopter> adopters = adopterService.getOrderByDate();
        return ResponseEntity.ok(adopters);
    }

    @PostMapping("findBy")
    public ResponseEntity<?>findBy(@RequestBody List<FilterDTO> filterDTO){
        List<Adopter> adopters =  adopterService.findBy(filterDTO);
        return ResponseEntity.ok(adopters);
    }
}
