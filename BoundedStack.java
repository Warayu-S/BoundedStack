/**
 * BoundedStack - ADT แทนโครงสร้างข้อมูลเเบบสเเตก Stack
 * ที่เก็บข้อมูลเป็นรูปแบบของ int โดยจำกัดความจุสูงสุด
 * <p>
 * <b>ตัวอย่างการใช้งาน:</b>
 * 
 * <pre>
 * <code>
 * BoundedStack stack = new BoundedStack(5);
 * stack.push(10);
 * stack.push(20);
 * System.out.println(stack.pop()); // 20
 * System.out.println(stack.size()); // 1
 * </code>
 * </pre>
 */
public class BoundedStack {

    // ===== Representation =====

    private final int[] elements;
    private int size;
    private final int capacity;
    // Abstraction Function (AF)
    // AF(elements, size, capacity) ซองกระสุน ที่จุลูกกระสุนเลขจำนวนเต็ม (int)
    // ได้สูงสุด capacity นัด โดยปัจจุบันมีกระสุนบรรจุอยู่จำนวน size นัด
    // โดยกระสุนนัดที่อยู่ด้านในสุด (ก้นแม็กกาซีน) คือ elements[0]
    // และกระสุนนัดบนสุดที่พร้อมจะถูกดึงออกมาใช้งานคือ elements[size-1]

    // Representation Invariant (RI)
    // 1. ความจุต้องไม่ติดลบ capacity >= 0
    // 2. ต้องมีการสร้างพื้นที่อาร์เรย์ไว้จริง elements != null
    // 3. ขนาดอาร์เรย์ต้องเท่ากับความจุ elements.length == capacity
    // 4. จำนวนข้อมูลต้องอยู่ในช่วงที่กำหนด 0 <= size <= capacity!
    // 5. ข้อมูลที่ใช้งานจริงต้องเรียงชิดกัน ข้อมูลในอาร์เรย์ช่วงตั้งแต่ index 0 ถึง
    // size-1 ถูกใช้งานและจัดเก็บอย่างต่อเนื่องไม่มีช่องว่าง

    private void checkRep() {
        assert capacity >= 0 : "Capacity must be non-negative";
        assert elements != null : "Element array must not be null";
        assert elements.length == capacity : "elements.length must equal capacity";
        assert size >= 0 && size <= capacity : "Size must be in the range [0, capacity]";
        // ข้้อ 5 ไม่ต้องเขียน assert เพราะถูกรับประกันโดยการทำงานของ size อยู่เเล้ว
    }
    
    // ===== Creator =====

    /** 
     *  สร้าง stack ว่างที่มีความจุสูงสุดตามที่กำหนด
     * 
     * @param capacity ความจุสูงสุดของ stack
     * @throws IllegalArgumentException ถ้าความจุที่ส่งเข้ามาน้อยกว่า 0
     */
    public BoundedStack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = new int[capacity];

        checkRep();
    }
    
    // ===== Mutators =====
    /**
     * 
     * 
     * @param e 
     * @return 
     * 
     */
    
    public void push(int e) {
        
    }

    /**
     * 
     * @return
     */
    public int pop() {
        return 0;
    }

    // ===== Observers =====

    /**
     * 
     * @return
     */
    public int peek() {
        return 0;
    }

    /**
     * 
     * @return
     */
    public int size() {
        return 0;
    }

    /**
     * 
     * @return
     */
    public int capacity() {
        return 0;
    }

    /**
     * 
     * @return
     */
    public boolean isEmpty(){
        return true;
    }

    /**
     * 
     * @return
     */
    public boolean isFull() {
        return false;
    }

    // ===== Producer =====

    /**
     * 
     * @return
     */
    public BoundedStack copy() {
        return null;
    }
}