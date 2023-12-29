package eu.planlos.javaspringwebutilities.common;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/thing")
public class ThingController {

    private final ThingRepository repository;

    public ThingController(ThingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Thing> get(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Thing> post(@Valid @RequestBody Thing thing) {
        return ResponseEntity.of(Optional.of(repository.save(thing)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
