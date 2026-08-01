package com.example.coffeemenu.service;

import com.example.coffeemenu.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CoffeeService {

    private final List<Coffee> coffeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public CoffeeService() {
        // ข้อมูลตัวอย่างตั้งต้น 2 รายการ เมื่อเริ่มรันโปรแกรม
        addCoffee(new Coffee(null, "Espresso", 45.0));
        addCoffee(new Coffee(null, "Latte", 55.0));
    }

    // 1. ดูเมนูทั้งหมด
    public List<Coffee> getAllCoffees() {
        return coffeeList;
    }

    // 2. ดูเมนูตาม ID
    public Optional<Coffee> getCoffeeById(Long id) {
        return coffeeList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    // 3. เพิ่มเมนูใหม่ (Gen ID อัตโนมัติ)
    public Coffee addCoffee(Coffee coffee) {
        long newId = idCounter.incrementAndGet();
        coffee.setId(newId);
        coffeeList.add(coffee);
        return coffee;
    }

    // 4. แก้ไขเมนูเดิมตาม ID
    public Optional<Coffee> updateCoffee(Long id, Coffee updatedCoffee) {
        return getCoffeeById(id).map(existingCoffee -> {
            existingCoffee.setName(updatedCoffee.getName());
            existingCoffee.setPrice(updatedCoffee.getPrice());
            return existingCoffee;
        });
    }

    // 5. ลบเมนูตาม ID
    public boolean deleteCoffee(Long id) {
        return coffeeList.removeIf(coffee -> coffee.getId().equals(id));
    }

    // (Bonus) ค้นหาเมนูตามชื่อ
    public List<Coffee> searchCoffeesByName(String name) {
        return coffeeList.stream()
                .filter(coffee -> coffee.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}