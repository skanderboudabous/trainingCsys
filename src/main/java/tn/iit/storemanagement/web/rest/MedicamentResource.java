package tn.iit.storemanagement.web.rest;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tn.iit.storemanagement.dto.MedicamentDto;
import tn.iit.storemanagement.services.MedicamentService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/api/medicaments")
@RestController()
public class MedicamentResource {
    private final MedicamentService medicamentService;

    public MedicamentResource(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping("/{id}")
    public MedicamentDto findOne(@PathVariable("id") long id) {
        return this.medicamentService.findOne (id);
    }

    @GetMapping()
    public Collection<MedicamentDto> findAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String pageSort
    ) {
        return this.medicamentService.findAll (PageRequest.of(pageNo,pageSize, Sort.by (pageSort).ascending ()));
    }

    @PostMapping
    public ResponseEntity<MedicamentDto> add(@Valid @RequestBody MedicamentDto medicamentDto, BindingResult bindingResults) throws URISyntaxException, MethodArgumentNotValidException {
        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        if (medicamentDto.getId() != null) {
            bindingResults.addError(new FieldError("Medicament", "id", "Post does not allow a medicament with id"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        MedicamentDto result = medicamentService.save(medicamentDto);
        return ResponseEntity.created(new URI("/api/medicaments/" + result.getId())).body(result);
    }

    @PostMapping("/searches")
    public Collection<MedicamentDto> searches(@Valid @RequestBody List<Long> ids){
        return this.medicamentService.findAllByIds(ids);
    }

    @PutMapping()
    public MedicamentDto update(@Valid @RequestBody MedicamentDto medicamentDto) {
        return this.medicamentService.save (medicamentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        this.medicamentService.deleteById (id);
    }
}
