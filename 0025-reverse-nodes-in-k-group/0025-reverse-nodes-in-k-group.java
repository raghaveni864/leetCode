public class Reverse_Nodes_in_k_Group {

    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode prev = dummy;

            // count total nodes
            ListNode tmp = head;
            int count = 0;
            while (tmp != null) {
                count++;
                tmp = tmp.next;
            }

            // 1->2->3->4->5 , k=3
            // 2,1,3,4,5
            // 3,2,1,4,5
            // => always getting 1's next for prev's next => current (below) not changing in one-batch-swap

            // if only one node left, then no swap
            while (count >= k) {

                ListNode originalFirst = prev.next;

                int kcopy = k - 1; // @note: since current node is already counted as 1
                while (kcopy > 0) { // both prev and current, not changed in while loop

                    ListNode nextNextCopy = originalFirst.next.next;
                    ListNode firstInGroup = prev.next;

                    prev.next = originalFirst.next;
                    prev.next.next = firstInGroup;
                    originalFirst.next = nextNextCopy;

                    kcopy--;
                }

                // @note: update previous AND current. I forgot current...
                prev = originalFirst; // now current is the last one of this group
                count -= k;
            }

            return dummy.next;
        }
    }
}

//////

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = dummy;
        while (cur.next != null) {
            for (int i = 0; i < k && cur != null; ++i) {
                cur = cur.next;
            }
            if (cur == null) {
                return dummy.next;
            }
            ListNode t = cur.next;
            cur.next = null;
            ListNode start = pre.next;
            pre.next = reverseList(start);
            start.next = t;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }
}