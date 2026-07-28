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
    // Abstraction Function (AF)
    // AF(elements, size, capacity) ซองกระสุน ที่จุลูกกระสุนเลขจำนวนเต็ม (int)
    // ได้สูงสุด capacity นัด โดยปัจจุบันมีกระสุนบรรจุอยู่จำนวน size นัด
    // โดยกระสุนนัดที่อยู่ด้านในสุด (ก้นแม็กกาซีน) คือ elements[0]
    // และกระสุนนัดบนสุดที่พร้อมจะถูกดึงออกมาใช้งานคือ elements[size-1]

    // Representation Invariant (RI)
    // 1. ความจุต้องไม่ติดลบ capacity >= 0
    // 2. ต้องมีการสร้างพื้นที่อาร์เรย์ไว้จริง elements != null
    // 3. ขนาดอาร์เรย์ต้องเท่ากับความจุ elements.length == capacity
    // 4. จำนวนข้อมูลต้องอยู่ในช่วงที่กำหนด 0 <= size <= capacity
    // 5. ข้อมูลที่ใช้งานจริงต้องเรียงชิดกัน ข้อมูลในอาร์เรย์ช่วงตั้งแต่ index 0 ถึง
    // size-1 ถูกใช้งานและจัดเก็บอย่างต่อเนื่องไม่มีช่องว่าง

    // ===== Creator =====

    // ===== Mutators =====

    // ===== Observers =====

    // ===== Producer =====
}