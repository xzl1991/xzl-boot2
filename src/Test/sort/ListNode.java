package sort;

/**
 * @auther xzl on 18:48 2018/1/17
 */
public class ListNode {
      int val;
      ListNode next;
      public ListNode(int x) {
          val = x;
          next = null;
     }
     public void add(int val){
        ListNode node = new ListNode(val);
        while (next!=null){
            next = next.next;
            next = node;
        }

     }
}
