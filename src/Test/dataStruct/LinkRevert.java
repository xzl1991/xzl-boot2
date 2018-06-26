package dataStruct;

/**
 * @auther xzl on 17:42 2018/5/2
 * 链表翻转
 */
public class LinkRevert {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        ListNode pre = head.next;
//        head.next.next = head;
//        printList(pre);
//        reverseList(head);
        System.out.println("===========");
        printList(reverseList3(head));
    }
    public static void printList(ListNode node){
        while (node !=null){
            System.out.print(node.val+" ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 遍历，将当前节点的下一个节点缓存后更改当前节点指针
     * 方法1
     */
    public static  ListNode reverseList(ListNode head) {
        if (head==null)
            return head;
        ListNode pre = head;// 上一结点
        ListNode cur = head.next;// 当前结点
        ListNode tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
        while(cur != null){//当前为null 是尾节点
            tmp = cur.next;
            cur.next = pre;//翻转指针指向
            //指针往后走
            pre = cur;
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.next = null;
        return pre;
    }

    /**
     * 方法2
     * */
    public static ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        while(head!=null){
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }
    /**
     * 递归实现
     * */
    public static ListNode reverseList3(ListNode head) {
        if(head==null||head.next ==null)
            return head;
        ListNode prev = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }
}


class ListNode{
    int val;
    ListNode next;
    public ListNode(int x) {
        val = x;
    }
}
