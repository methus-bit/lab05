# lab05
[PDF](Lab05_6733803002Sec1.pdf)

## วิธีรัน Project ให้เขียน command นี้ลง cmd
```
mvn spring-boot:run
```
## ตัวอย่างการเรียกใช้งาน API
1. ดูรายการเมนูกาแฟทั้งหมด (GET All)
```
curl -X GET http://localhost:8080/coffees
```

2. ดูรายการเมนูกาแฟตาม ID (GET by ID)
```
curl -X GET http://localhost:8080/coffees
```

3. เพิ่มเมนูกาแฟใหม่ (POST)
```
curl -X POST http://localhost:8080/coffees ^-H "Content-Type: application/json" ^-d "{\"name\": \"Cappuccino\", \"price\": 60.0}"
```
4. แก้ไขเมนูกาแฟตาม ID (PUT)
```
curl -X PUT http://localhost:8080/coffees/2 ^-H "Content-Type: application/json" ^-d "{\"name\": \"Latte\", \"price\": 50.0}"
```
5. ลบเมนูกาแฟตาม ID (DELETE)
```
curl -X DELETE http://localhost:8080/coffees/3
```
