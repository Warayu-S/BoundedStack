# Lab — สร้าง ADT: BoundedStack

**วิชา:** 01418211 Software Construction
**หัวข้อ:** Abstract Data Types (ADTs), Abstraction Function, Representation Invariant

---

## เป้าหมาย

สร้าง ADT ชื่อ `BoundedStack` ที่ทำหน้าที่เป็นโครงสร้างข้อมูลแบบสแตกซึ่งเก็บข้อมูลตัวเลข (`int`) โดยมีการจำกัดความจุสูงสุด โดยต้อง:

1. ระบุการดำเนินการให้ครบทั้ง 4 บทบาท (Creators / Producers / Observers / Mutators)
2. เขียน Abstraction Function (AF) และ Representation Invariant (RI)
3. เขียนเมธอด `checkRep()` ด้วย assertions
4. ป้องกัน Representation Exposure ทั้งขาเข้าและขาออก
5. ทดสอบให้ครอบคลุม รวมถึงเคส Boundary และ Exception

---

## ไฟล์ในโฟลเดอร์นี้

| ไฟล์ | คำอธิบาย |
|---|---|
| `BoundedStack.java` | โค้ดหลักของ ADT พร้อม JavaDoc อธิบายสเปก (D1)|
| `TestRunner.java` | ชุดทดสอบอัตโนมัติ 23 เคส เขียนด้วยสไตล์ AAA Pattern  (D2)|
| `README.md` | ไฟล์นี้ (D3)|

---

เมื่อทำเสร็จถูกต้องทั้งหมด และรันผ่านคำสั่ง `java -ea TestRunner` จะได้ผลลัพธ์แบบนี้

```text
=========================
      TEST SUMMARY
=========================
Passed : 23
Failed : 0
Total  : 23
=========================
  - ALL TESTS PASSED -
```

> ข้อความที่โปรแกรมพิมพ์ออกมาเป็นภาษาอังกฤษทั้งหมด เพื่อเลี่ยงปัญหา
> console บน Windows แสดงภาษาไทยเพี้ยน ส่วนคอมเมนต์ในโค้ดยังเป็นภาษาไทยตามเดิม

> **หมายเหตุเรื่องสไตล์โค้ด:** ชุดทดสอบเขียนด้วยไวยากรณ์ Java พื้นฐานเท่านั้น
> ห้ามใช้ Framework การตรวจ exception ใช้ `try` / `catch` ตรง ๆ 

---

## สเปคของ ADT

### ค่านามธรรม (A)

สแตกที่จุตัวเลขได้ตามจำนวนความจุสูงสุด โดยทำงานแบบ LIFO (Last-In, First-Out) — ข้อมูลที่ใส่เข้ามาล่าสุด จะถูกนำออกไปใช้งานเป็นตัวแรกเสมอ

### Representation (R)

```java
private final int[] elements;
private int size;
private final int capacity;
```

### กฎที่ BoundedStack ต้องรักษาไว้เสมอ

- ความจุต้องไม่ติดลบ (`capacity >= 0`)
- ต้องมีการจองพื้นที่อาเรย์ไว้ใช้งานจริง (`elements != null`)
- ขนาดของอาเรย์ที่จองไว้ต้องตรงกับความจุที่ตั้งไว้ (`elements.length == capacity`)
- จำนวนข้อมูลปัจจุบันต้องอยู่ในขอบเขตที่กำหนด (`0 <= size <= capacity`)
- ข้อมูลจัดเก็บเรียงชิดกันตั้งแต่ index 0 เป็นต้นไป (ไม่เว้นช่องว่าง)

---

## Contributors

**Warayu Samutpriaw**
* Computer Science, Kasetsart University (Kamphaeng Saen Campus)
* Student ID: 6821651744
* GitHub: [@Warayu-S](https://github.com/Warayu-S)

**Supakorn Samartkij**
* Computer Science, Kasetsart University (Kamphaeng Saen Campus)
* Student ID: 6821651817
* GitHub: [@Supa-void](https://github.com/Supa-Void)
