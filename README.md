# BIG LAB: BoundedStack
01418211 Software Construction
---

## ภาพรวมโปรเจกต์ (Overview)
โปรเจกต์นี้เป็นการพัฒนา Abstract Data type (ADT) คลาส ** BoundedStack ** : 
ซึ่งเป็นโครงสร้างข้อมูลแบบ Stack ที่เก็บข้อมูลชิด 'int' และมีการจำกัดความจุสูงสุด (Bounded Capacity)

---

## แนวคิดการออกแบบ (Design & Architecture Highlights)
** Representation & Encapsulation : เก็ชข้อมูลภายในด้วย 'int[]' และ 'size' โดยตั้งค่าเป็น 'private Final' ทั้งหมด Client ไม่สามารถเข้าถึงหรือแก้ไขข้อมูลภายในโดยตรงได้

**Representation Invarient (RI) & 'checkRep()' : มีการระบุ RI ที่ชัดเจนเหนือ private fields และเรียกใช้ 'checkRep()' หลังจากการเปลี่ยนแปลงสถานะทุกครั้งเพื่อการันตีความถูกต้องภายในวัตถุ

**Defensive Copying : เมธอด 'copy()' คัดลอกสร้าง Objectใหม่ที่มีตำแหน่งหน่วยความจำ แยกจากเดิมโดยสิ้นเชิง การแก้ไขต้นฉบับจะไม่มีผลกระทบต่อตัวที่คัดลอกออกไป

**Exception Handling : แยกแยะความผิดพลาดอย่างเป็นระบบ
  * ใช้ Exception ('IllegalStateException' , 'IllegalArgumentException') จัดการเงื่อนไขที่ผิดพลาดจาก Client
  * ใช้ Assertion ('assert') ตรวจสอบความถูกต้องภายในโครงสร้าง ('checkRep()') เท่านั้น

## Design Document : BoundedStack
1. Specification & Operation Roles
* **Creator:** `BoundedStack(int capacity)`
  * *Precondition:* `capacity >= 0`
  * *Postcondition:* สร้าง Stack ว่างที่มีความจุตามระบุ
  * *Exception:* โยน `IllegalArgumentException` ถ้า `capacity < 0`
* **Mutator:**
  * `push(int e)`: เพิ่มข้อมูลลงบนยอดสุด (โยน `IllegalStateException` ถ้า Stack เต็ม)
  * `pop()`: ดึงและลบข้อมูลบนสุดออก (โยน `IllegalStateException` ถ้า Stack ว่าง)
* **Observer:**
  * `peek()`: ดูข้อมูลบนสุดโดยไม่ลบออก (โยน `IllegalStateException` ถ้า Stack ว่าง)
  * `size()`, `capacity()`, `isEmpty()`, `isFull()`: คืนค่าสถานะของ Stack (ไม่มี Side-effect)
* **Producer:**
  * `copy()`: คืนค่า `BoundedStack` วัตถุใหม่ (Defensive Copy) โดยมีข้อมูลและขนาดเท่าเดิม แต่แยก Memory กันโดยสิ้นเชิง

## 2. Design Decisions & Trade-offs
* **Representation Choice:** เลือกใช้ `int[]` ร่วมกับ `size` เนื่องจากเข้าถึงข้อมูลดรรชนีได้รวดเร็ว $O(1)$ ใช้พื้นที่คงที่ ไม่สร้าง Overhead เหมือนวัตถุWrapper
* **Mutable vs Immutable:** เลือกแบบ **Mutable** เพราะเหมาะกับพฤติกรรมธรรมชาติของ Stack ที่ต้องมีการ push/pop ข้อมูลบ่อยๆ
* **Error Handling Strategy:**
  * ใช้ **Exception** (`IllegalStateException`, `IllegalArgumentException`) สำหรับจัดการข้อผิดพลาดจาก Client
  * ใช้ **Assertion** (`checkRep()`) ภายในคลาส เพื่อตรวจสอบความถูกต้องของ Representation Invariant (RI) เท่านั้น


## วิธี compile และรัน

**ตั้งค่าใน VSCode** ให้เปิด `-ea` อัตโนมัติ — เพิ่มใน `.vscode/settings.json`:

```json
{
  "java.debug.settings.vmArgs": "-ea"
}
```
## ผลลัพธ์การ Test 

=========================
      TEST SUMMARY
=========================
Passed : 23
Failed : 0
Total  : 23
=========================
  - ALL TESTS PASSED -

## สรุปชุดทดสอบอัตโนมัติ (Automated Test Suite)
  1.Creators & Initial States: ตรวจสอบความจุและสถานะเริ่มต้นหลังสร้างวัตถุ (capacity, size = 0, isEmpty = true, isFull = false)
  2.Mutators & Observers: ตรวจสอบการทำงานของ push, pop, peek ลำดับข้อมูลแบบ LIFO และการทำงานของ Observer ที่ต้องไม่มี Side-Effect
  3.Exception Handling: ตรวจสอบการพยายาม push ใส่ Stack ที่เต็มแล้ว หรือการ pop/peek จาก Stack ที่ว่างเปล่า
  4.Capacity Boundaries: ตรวจสอบสภาวะขอบเขต เช่น Stack ที่มีความจุเป็น 0 และการป้องกันการสร้าง Stack ด้วยความจุติดลบ
  5.Producer (Defensive Copy): ตรวจสอบว่า copy() สร้าง Instance ใหม่ที่มีข้อมูลเหมือนกัน แต่แยก Memory เป็นอิสระจากกันจริง


## Contributors

**Warayu Samutpriaw**
* Computer Science, Kasetsart University (Kamphaeng Saen Campus)
* Student ID: 6821651744
* GitHub: [@Warayu-S](https://github.com/Warayu-S)

**Supakorn Samartkij**
* Computer Science, Kasetsart University (Kamphaeng Saen Campus)
* Student ID: 6821651817
* GitHub: [@Supa-void](https://github.com/Supa-Void)