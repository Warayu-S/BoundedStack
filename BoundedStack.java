/**
 * BoundedStack - ADT แทนโครงสร้างข้อมูลเเบบสเเตก Stack ที่เก็บข้อมูลเป็นรูปแบบของ int โดยจำกัดความจุสูงสุด
 * <p>
 * หลักการทำงานคล้ายกับ "ซองใส่กระสุนปืน" หรือ "การซ้อนจาน" (LIFO: Last-In, First-Out)
 * ข้อมูลที่ใส่เข้าไปล่าสุด จะถูกดึงออกมาใช้งานเป็นตัวแรก
 * </p>
 * <p><b>คำสั่งพื้นฐานที่สำคัญ:</b></p>
 * <ul>
 *  <li><b>push(x)</b>: "ใส่ของ" ลงไปทับไว้บนสุดของ Stack (ระวัง: ถ้าใส่จนเกินความจุจะเกิด Error)</li>
 *  <li><b>pop()</b>: "หยิบของ" ที่อยู่บนสุดออกมาใช้งาน (ข้อมูลจะถูกลบออกไปจาก Stack ด้วย)</li>
 *  <li><b>peek()</b>:"แอบดู" ข้อมูลตัวบนสุด ว่าคือเลขอะไร (ข้อมูลยังอยู่ที่เดิม ไม่โดนลบ)</li>
 * </ul>
 * <p>
 * <b>ตัวอย่างการใช้งาน:</b>
 * <pre>
 * <code>
 * BoundedStack stack = new BoundedStack(5);
 * stack.push(10);
 * stack.push(20);
 * 
 * System.out.println(stack.peek());    // 20 พิมพ์ 20 (แค่ดูเฉยๆ ของยังอยู่ 2 ชิ้น)
 * System.out.println(stack.pop());     // 20 พิมพ์ 20 (หยิบ 20 ออกมาทิ้งไป ของเหลือ 1 ชิ้น)
 * System.out.println(stack.size());    // 1  พิมพ์ 1  (ตอนนี้เหลือแค่เลข 10 ตัวเดียว)
 * </code>
 * </pre>
 */

public class BoundedStack {

    // ===== Representation =====
    private final int[] elements;
    private int size;
    private final int capacity;
    // Abstraction Function (AF)
    // AF(elements, size, capacity) ซองกระสุน ที่จุลูกกระสุนเลขจำนวนเต็ม (int) ได้สูงสุด capacity นัด โดยปัจจุบันมีกระสุนบรรจุอยู่จำนวน size นัด
    // โดยกระสุนนัดที่อยู่ด้านในสุด (ก้นแม็กกาซีน) คือ elements[0] และ กระสุนนัดบนสุดที่พร้อมจะถูกดึงออกมาใช้งานคือ elements[size-1]

    // Representation Invariant (RI)
    // 1. ความจุต้องไม่ติดลบ capacity >= 0
    // 2. ต้องมีการสร้างพื้นที่อาร์เรย์ไว้จริง elements != null
    // 3. ขนาดอาร์เรย์ต้องเท่ากับความจุ elements.length == capacity
    // 4. จำนวนข้อมูลต้องอยู่ในช่วงที่กำหนด 0 <= size <= capacity
    // 5. ข้อมูลที่ใช้งานจริงต้องเรียงชิดกัน ข้อมูลในอาร์เรย์ช่วงตั้งแต่ index 0 ถึง size-1 ถูกใช้งานและจัดเก็บอย่างต่อเนื่องไม่มีช่องว่าง

    /**
     * ตรวจสอบความถูกต้องของ Representation Invariant (RI)
     * หากสถานะของ Stack ไม่ถูกต้องตามเงื่อนไข จะทำให้เกิด AssertionError
     */
    private void checkRep() {
        assert capacity >= 0 : "Capacity must be non-negative";
        assert elements != null : "Element array must not be null";
        assert elements.length == capacity : "elements.length must equal capacity";
        assert size >= 0 && size <= capacity : "Size must be in the range [0, capacity]";
        // ข้อ 5 ไม่ต้องเขียน assert เพราะถูกรับประกันโดยการทำงานของ size อยู่แล้ว
    }

    // ===== Creator =====

    /**
     * สร้าง stack ว่างที่มีความจุสูงสุดตามที่กำหนด
     * 
     * @param capacity ความจุสูงสุดของ stack
     * @throws IllegalArgumentException ถ้าความจุที่ส่งเข้ามาน้อยกว่า 0
     */
    public BoundedStack(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
        this.size = 0;
        this.elements = new int[capacity];
        checkRep();
    }

    // ===== Mutators =====

    /**
     * เพิ่มข้อมูลใหม่ลงบนยอดสุดของ stack
     * 
     * @param e ข้อมูลตัวเลขจำนวนเต็ม int ที่ต้องการเพิ่มลงใน stack
     * @throws IllegalStateException ถ้าพยายามเพิ่มข้อมูลในขณะที่ Stack เต็มความจุเเล้ว (เรียกใช้ตอน isFull() เป็น true)
     */
    public void push(int e) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        elements[size] = e;
        size++;
        checkRep();
    }

    /**
     * นำข้อมูลที่อยู่บนสุดออกจาก Stack เเละคืนค่าค่านั้น
     * 
     * @return ข้อมูลตัวเลขจำนวนเต็มที่ถูกดึงออกจากยอดสุดของ Stack
     * @throws IllegalStateException ถ้าพยายามดึงข้อมูลในขณะที่ Stack ว่างเปล่า (เรียกใช้ตอน isEmpty() เป็น true)
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = elements[size -1];
        size--;
        checkRep();
        return value;
    }

    // ===== Observers =====

    /**
     * ดูข้อมูลตัวที่อยู่บนสุดของ Stack โดยไม่มีการดึงข้อมูลนั้นออกจาก Stack
     * 
     * @return ข้อมูลตัวเลขจำนวนเต็มที่อยู่บนยอดสุดของ Stack
     * @throws IllegalStateException ถ้าพยายามดูข้อมูลในขณะที่ Stack ว่างเปล่า (เรียกใช้ตอน isEmpty() เป็น true)
     */
    public int peek() {
        if (isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return elements[size-1]; 
    }

    /**
     * คืนค่าจำนวนข้อมูลปัจจุบันที่ถูกเก็บใน Stack
     * 
     * @return จำนวนข้อมูลปัจจุบัน มีค่าตั้งเเต่ 0 ถึง capacity
     */
    public int size() {
        return size; 
    }

    /**
     * คืนค่าความจุสูงสุดที่ Stack นี้สามารถเก็บข้อมูลได้
     * 
     * @return ความจุสูงสุดของ Stack
     */
    public int capacity() {
        return capacity; 
    }

    /**
     * ตรวจสอบว่า Stack ว่างเปล่าหรือไม่
     * 
     * @return true ถ้าไม่มีข้อมูลอยู่เลยใน Stack, false ถ้ามีข้อมูลอยู่อย่างน้อย 1 ตัว
     */
    public boolean isEmpty() {
        return size == 0; 
    }

    /**
     * ตรวจสอบว่า Stack เต็มความจุแล้วหรือไม่
     * 
     * @return true ถ้าจำนวนข้อมูลเท่ากับความจุสูงสุดแล้ว, false ถ้ายังมีพื้นที่เหลือให้ push เพิ่มได้
     */
    public boolean isFull() {
        return size == capacity; 
    }

    // ===== Producer =====

    /**
     * สร้าง BoundedStack
     * ตัวใหม่ที่มีความจุและข้อมูลข้างในเหมือนกับตัวปัจจุบันทุกประการ โดยที่ Stack ตัวใหม่นี้จะเป็นอิสระจากตัวเดิม (Defensive Copy)
     * การแก้ไขตัวใดตัวหนึ่งจะไม่ส่งผลกระทบต่ออีกตัว
     *
     * @return BoundedStack ออบเจกต์ใหม่ที่คัดลอกข้อมูลมาจากตัวปัจจุบันอย่างสมบูรณ์
     */

    public BoundedStack copy() {
        BoundedStack newStack = new BoundedStack(capacity);
        for (int i = 0; i < this.size; i++) {
            newStack.elements[i] = this.elements[i];
    }
        newStack.size = this.size;
        newStack.checkRep();
        return newStack; 
    }
}