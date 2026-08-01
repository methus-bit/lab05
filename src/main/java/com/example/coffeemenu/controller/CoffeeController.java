package com.example.coffeemenu.controller;

import com.example.coffeemenu.model.Coffee;
import com.example.coffeemenu.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    // Dependency Injection ผ่าน Constructor
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    // 1. GET /coffees - ดูเมนูทั้งหมด
    @GetMapping
    public ResponseEntity<List<Coffee>> getAllCoffees() {
        return ResponseEntity.ok(coffeeService.getAllCoffees());
    }

    // 2. GET /coffees/{id} - ดูเมนู 1 รายการตาม ID (คืน 404 ถ้าไม่พบ)
    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        return coffeeService.getCoffeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 3. POST /coffees - เพิ่มเมนูใหม่
    @PostMapping
    public ResponseEntity<Coffee> addCoffee(@RequestBody Coffee coffee) {
        Coffee createdCoffee = coffeeService.addCoffee(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoffee);
    }

    // 4. PUT /coffees/{id} - แก้ไขเมนูเดิมตาม ID (คืน 404 ถ้าไม่พบ)
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        return coffeeService.updateCoffee(id, coffee)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 5. DELETE /coffees/{id} - ลบเมนูตาม ID (คืน 404 ถ้าไม่พบ)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        boolean deleted = coffeeService.deleteCoffee(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // GET /coffees/search?name=... - ค้นหาตามชื่อ
    @GetMapping("/search")
    public ResponseEntity<List<Coffee>> searchCoffees(@RequestParam String name) {
        return ResponseEntity.ok(coffeeService.searchCoffeesByName(name));
    }
}