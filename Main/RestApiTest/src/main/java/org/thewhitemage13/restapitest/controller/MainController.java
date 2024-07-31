package org.thewhitemage13.restapitest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.restapitest.dto.CatDTO;
import org.thewhitemage13.restapitest.entity.Cat;
import org.thewhitemage13.restapitest.repository.CatRepository;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepository catRepository;

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {

        log.info("New row: " + catRepository.save(Cat.builder()
                        .age(catDTO.getAge())
                        .name(catDTO.getName())
                        .weight(catDTO.getWeight())
                .build()));
    }

    @GetMapping("/api/get")
    public Cat getCat(@RequestParam int id) {
        return catRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/api/delete")
    public void deleteCat(@RequestParam int id) {
        catRepository.deleteById(id);
    }

    @PutMapping("/api/update")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepository.save(cat).toString();
    }

}












