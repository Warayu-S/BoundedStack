/**
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
            System.out.println("\nWARNING: assertions disabled" + " - re-run with: java -ea TestRunner");
        }
        
        System.out.println("\n=== BoundedStack Test Suite ===");

        // รันชุดทดสอบหมวดหมู่
        testCreators();
        testMutatorsAndObservers();
        testExceptions();
        testBoundaries();
        testProducer();

        // สรุปผลคะแนนรวมตอนท้าย
        System.out.println("\n========================");
        System.out.println("      TEST SUMMARY      ");
        System.out.println("========================");
        System.out.println(" Passed : " + passed);
        System.out.println(" Failed : " + failed);
        System.out.println(" Total  : " + (passed + failed));
        System.out.println("========================");
        System.out.println(failed == 0 ? "  - ALL TESTS PASSED -  " : "  - SOME TESTS FAILED -  ");
        System.out.println();
        if (failed > 0) System.exit(1);
    }

    // ---------------------------------------------------------
    // 1. หมวด Creators & Initial States
    // ---------------------------------------------------------
    private static void testCreators() {
        System.out.println("\n-- 1. Creators & Initial States --");

        // Arrange & Act: สร้าง Stack ขึ้นมาใหม่
        BoundedStack stack = new BoundedStack(3);

        // Assert: ตรวจสอบสถานะเริ่มต้นต้องเป็นไปตามทฤษฎี
        check("new(3) -> capacity is 3", stack.capacity() == 3);    //  เช็กว่าค่า capacity() ถูกตั้งไว้ตรงตามค่าตอนสร้าง
        check("new() -> size = 0", stack.size() == 0);              //  เช็กว่า size() ของ Stack ใหม่จะต้องเริ่มที่ 0
        check("new() -> is empty", stack.isEmpty() == true);        //  เช็กสถานะเริ่มต้นว่า isEmpty() คืนค่า true
        check("new() -> is not full", stack.isFull() == false);     //  เช็กสถานะเริ่มต้้นว่า isFull() คืนค่า false
    }

    // ---------------------------------------------------------
    // 2. หมวด Mutators & Observers
    // --------------------------------------------------------
    private static void testMutatorsAndObservers() {
        System.out.println("\n-- 2. Mutators & Observers --");

        // Assert: เตรียม Stack สำหรับเทสต์
        BoundedStack stack = new BoundedStack(3);

        // Assert: เพิ่มข้อมูล 1 ตัว
        stack.push(10);

        // Aseert: ตรวจสอบผลลัพธ์หลังเพิ่มข้อมูล
        check("push 1 item -> size is 1", stack.size() == 1);                           //  เช็กว่าหลัง push เเล้ว size() จะต้องเพิ่มขึ้น
        check("push 1 item -> isEmpty is false", stack.isEmpty() == false);             //  เช็กว่าพอมีข้อมูลเเล้ว isEmpty() จะเปลี่ยนเป็น false
        check("push 1 item -> peek is 10", stack.peek() == 10);                         //  เช็กว่าดึงข้อมูล peek ออกมาได้ค่าล่าสุดที่เพิ่งใส่ลงไป

        // Act: เพิ่มข้อมูลจนเต็ม
        stack.push(20);
        stack.push(30);

        // Assert: ตรวจสอบสถานนะ Upper Boundary
        check("push until full -> isFull is true", stack.isFull());                     //  เช็กว่าเมื่อใส่ข้อมูลครบความจุเต็มแล้ว isFull() จะต้องเปลี่ยนเป็น true
        check("stack is full -> size is capacity", stack.size() == stack.capacity());   //  เช็กว่าตอน Stack เต็ม size ต้องมีค่าเท่ากับความจุ

        // Act & Assert: ทดสอบ Observer ที่ต้อง "ไม่มี Side effect"
        int topItem = stack.peek();
        boolean hasNoSideEffect = (stack.size() == 3 && stack.peek() == topItem);
        check("peek -> no side effect", hasNoSideEffect);           //  เช็กว่า peek แค่ดูข้อมูล แต่ไม่มี side effect (ขนาดต้องเท่าเดิม)

        // Act: ดึงข้อมูลออก
        int popped1 = stack.pop();

        // Assert: ดึงข้อมูลต้องเป็นแบบ LIFO และขนาดลดลง
        check("pop -> gets last item", popped1 == 30);              //  เช็กกลไกหลัก LIFO ว่า pop() ดึงข้อมูลตัวที่เพิ่งใส่ (บนสุด) ออกมาก่อน
        check("pop -> size decreases", stack.size() == 2);          //  เช็กว่าหลัง pop() ออกไป ข้อมูล (size) ต้องลดลงไป 1 ค่า

        // Act: ดึงข้อมูลออกอีกครั้ง
        int poped2 = stack.pop();
        check("pop twice -> gets previous item", poped2 == 20);     // เช็กว่าการ pop() ครั้งถัดไปจะดึงข้อมูลตัวที่ใส่ไว้ก่อนหน้าออกมา      

        // Act: ดึงออกตัวสุดท้าย
        stack.pop();
        
        // Assert: สถานะ Lower Boundary ต้้องกลับมาว่างเปล่า
        check("pop all -> isEmpty is true", stack.isEmpty());       // เช็กสถานะว่าเมื่อดึงออกจนเกลี้ยงแล้ว isEmpty() ต้องกลับมาเป็น true
    }

    // ---------------------------------------------------------
    // 3. หมวด Exception Handling
    // ---------------------------------------------------------
    private static void testExceptions() {
        System.out.println("\n-- 3. Exception Handling --");

        // Test push() on full stack = เทส การเพิ่มของ บน Stack ที่เต็มแล้ว
        // Arrange
        BoundedStack fullStack = new BoundedStack(1);
        fullStack.push(99);
        boolean threwPushFull = false;

        // Act
        try {
            fullStack.push(100);
        } catch (IllegalStateException e) {
            threwPushFull = true;
        }
        // Assert
        check("push when full -> throw exception", threwPushFull);  // เช็กการดักจับ exception เมื่อพยายาม push ใส่ Stack ที่เต็มแล้ว

        // Test pop() on empty stack , peek() on empty stack = เทส การลบของ บน Stack ที่ว่างเปล่า , เทส การอ่าน บน Stack ที่ว่างเปล่า
        // Arrange
        BoundedStack emptyStack = new BoundedStack(2);
        boolean threwPopEmpty = false;
        boolean threwPeekEmpty = false;

        // Act
        try {
            emptyStack.pop();
        } catch (IllegalStateException e) {
            threwPopEmpty = true;
        }

        try {
            emptyStack.peek();
            
        } catch (IllegalStateException e) {
            threwPeekEmpty = true;
        }

        // Assert
        check("pop on empty stack -> throw exception", threwPopEmpty);      // เช็กการดักจับ exception เมื่อพยายาม pop เอาของจาก Stack ที่ว่างเปล่า
        check("peek on empty stack -> throws Exception", threwPeekEmpty);   // เช็กการดักจับ exception เมื่อพยายาม peek ดูของจาก Stack ที่ว่างเปล่า
    }

    // ---------------------------------------------------------
    // 4. หมวด Capacity Boundaries
    // ---------------------------------------------------------
    private static void testBoundaries() {
        System.out.println("\n-- 4. Capacity Boundaries --");
        
        // Arrange & Act: สร้างด้วยค่าขอบเขตล่างสุด (0)
        BoundedStack zeroStack = new BoundedStack(0);

        // Assert
        check("new stack(0) -> capacity is 0", zeroStack.capacity() == 0);  // เช็กขอบเขตความจุต่ำสุด (0) ว่าสามารถสร้างได้และ capacity คือ 0
        check("new stack(0) -> isFull is true", zeroStack.isFull());        // เช็กว่า Stack ที่มีความจุเป็น 0 จะต้อง isFull() เต็มตั้งแต่แรก

        // Arrange: สร้างด้วยค่าผิดปกติ (ติดลบ)
        boolean threwNegativeCapacity = false;

        // Act
        try {
            new BoundedStack(-5);
        } catch (IllegalArgumentException e) {
            threwNegativeCapacity = true;
        }

        // Assert
        check("new stack(-5) -> throws exception", threwNegativeCapacity);  // เช็กการป้องกันการสร้าง Stack ด้วยความจุติดลบที่ผิดวิสัย

    }

    // ---------------------------------------------------------
    // 5. หมวด Producer (Defensive Copy)
    // ---------------------------------------------------------
    private static void testProducer() {
        System.out.println("\n-- 5. Producer (Defensive Copy) --");

        // Arrange: เตรียมต้นฉบับ
        BoundedStack original = new BoundedStack(3);
        original.push(1);
        original.push(2);

        // Act: สร้างตัว copy
        BoundedStack copy = original.copy();

        // Assert: ตรวจสอบความสมบูรณ์ของการ Copy
        boolean isDifferent = (original != copy);
        boolean hasSameProperties = (copy.size() == original.size() && copy.capacity() == original.capacity());

        check("copy -> gets new instance", isDifferent); // เช็กว่าเมธอด copy() คืนค่า reference ตัวใหม่ (memory ใหม่) ออกมาจริงๆ
        check("copy -> size and capacity are same", hasSameProperties); // เช็กว่าตัวก๊อปปี้มีข้อมูลภายในเหมือนต้นฉบับอย่างสมบูรณ์

        // Act: แกล้งแก้ไขต้นฉบับ
        original.push(3);

        // Assert: ตัว Copy ต้้องไม่ได้รับผลกระทบ
        boolean isIndependent = (copy.size() == 2 && original.size() == 3);
        check("change original -> copy is unchanged", isIndependent); // เช็กความอิสระต่อกัน เมื่อตัวต้นฉบับถูกแก้ไข ตัวก๊อปปี้จะต้องไม่ได้รับผลกระทบ
    }
} 