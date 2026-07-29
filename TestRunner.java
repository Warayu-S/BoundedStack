import java.util.Stack;

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
            System.out.println("WARNING: assertions disabled" + " - re-run with: java -ea TestRunner\n");
        }

        System.out.println("\n=== BoundedStack Test Suite ===");
        // รันชุดทดสอบหมวดหมู่
        testCreators();
        testMutatorsAndObservers();
        testExceptions();
        testBoundaries();
        testProducer();

        // สรุปผลคะแนนรวมตอนท้าย
        System.out.println("=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println(" Total: " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) System.exit(1);
    }


    // ---------------------------------------------------------
    // 1. หมวด Creators & Initial States
    // ---------------------------------------------------------
    private static void testCreators() {
        System.out.println("\n-- 1. Creators & Initial States --");

        // Arrange & Act: สร้าง 
        BoundedStack stack = new BoundedStack(3);

        check("new(3) -> capacity is 3", stack.capacity() == 3);     
        check("new() -> size = 0", stack.size() == 0);     
        check("new() -> is empty", stack.isEmpty() == true);
        check("new() -> is not full", stack.isFull() == false);

    }

    // ---------------------------------------------------------
    // 2. หมวด Mutators & Observers
    // --------------------------------------------------------
    private static void testMutatorsAndObservers() {
        System.out.println("\n-- 2. Mutators & Observers --");

        BoundedStack stack = new BoundedStack(3);

        stack.push(10);
        check("push 1 item -> size is 1", stack.size() == 1);
        check("push 1 item -> isEmpty is false", stack.isEmpty() == false); 
        check("push 1 item -> peek is 10", stack.peek() == 10);

        stack.push(20);
        stack.push(30);

        check("push until full -> isFull is true", stack.isFull());
        check("stack is full -> size is capacity", stack.size() == stack.capacity());

        int topItem = stack.peek();
        check("peek -> no side effect", stack.size() == 3 && stack.peek() == topItem);

        int popped1 = stack.pop();
        check("pop -> gets last item", popped1 == 30);
        check("pop -> size decreases", stack.size() == 2);

        int poped2 = stack.pop();
        check("pop twice -> gets previous item", poped2 == 20);

        stack.pop();
        check("pop all -> isEmpty is true", stack.isEmpty());
    }

    // ---------------------------------------------------------
    // 3. หมวด Exception Handling
    // ---------------------------------------------------------
    private static void testExceptions() {
        System.out.println("\n-- 3. Exception Handling --");
        // TODO: รอเขียน
    }

    // ---------------------------------------------------------
    // 4. หมวด Capacity Boundaries
    // ---------------------------------------------------------
    private static void testBoundaries() {
        System.out.println("\n-- 4. Capacity Boundaries --");
        // TODO: รอเขียน
    }

    // ---------------------------------------------------------
    // 5. หมวด Producer (Defensive Copy)
    // ---------------------------------------------------------
    private static void testProducer() {
        System.out.println("\n-- 5. Producer (Defensive Copy) --");

        BoundedStack original = new BoundedStack(3);
        original.push(1);
        original.push(2);

        BoundedStack copy = original.copy();
        check("copy -> gets new instance", original != copy);
        check("copy -> size and capacity are same", copy.size() == original.size() && copy.capacity() == original.capacity());

        original.push(3);
        check("change original -> copy is unchanged", copy.size() == 2 && original.size() == 3);
    }
} 

