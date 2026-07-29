/**
 * 
 * TestRunner สำหรับ BoundedStack
 * จัดโครงสร้างแบบแยกเมธอดตามกลุ่มการทดสอบ
 */

public class TestRunner {
    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }

    public static void main(String[] args) {

        // ตรวจสอบการเปิด -ea (Assertions) เพื่อให้แน่ใจว่า checkRep() ได้ทำงาน
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled" + " - re-run with: java -ea TestRunner\n");
        }

        // รันชุดทดสอบทีละหมวดหมู่
        testCreators();
        testMutatorsAndObservers();
        testExceptions();
        testBoundaries();
        testProducer();

        // สรุปผลคะแนนรวมตอนท้าย
        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println(" Total: " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) System.exit(1);
    }


    // 1. หมวด Creators: ทดสอบสถานะเริ่มต้นหลังจากใช้คำสั่ง new สร้าง Object
    private static void testCreators() {
        System.out.println("-- 1. Creators & Initial States --");

    }

    // 2. หมวด Mutators & Observers: ทดสอบการเปลี่ยนแปลงข้อมูล (push/pop) 
    // และสังเกตผลลัพธ์ว่าตรงตามหลักการ LIFO (เข้าหลังสุด ออกก่อนสุด) หรือไม่
    private static void testMutatorsAndObservers() {
        System.out.println("\n-- 2. Mutators & Observers --");
    }

    // 3. หมวด Exceptions: ทดสอบการป้องกันข้อผิดพลาด เมื่อโปรแกรมถูกสั่งให้ทำสิ่งที่ผิดกฎหมายของ Stack (ล้น หรือ ว่างเปล่า)
    private static void testExceptions() {
        System.out.println("\n-- 3. Exception Handling --");
        
    }

    // 4. หมวด Boundaries: ทดสอบขอบเขตสุดโต่งของ Capacity (0 และ ติดลบ) 
    // เพื่อดูว่าโปรแกรมรับมือกับเคสแปลกๆ ได้อย่างรัดกุมหรือไม่
    private static void testBoundaries() {
        System.out.println("\n-- 4. Capacity Boundaries --");
    }

    // 5. หมวด Producer: ทดสอบการทำโคลนนิ่ง (Defensive Copy) 
    // ตัวใหม่ที่ถูกก๊อปปี้ออกมา ต้องเป็นอิสระต่อกัน (แก้ตัวหนึ่ง ต้องไม่กระทบอีกตัว)
    private static void testProducer() {
        System.out.println("\n-- 5. Producer (Defensive Copy) --");
    }
} 

