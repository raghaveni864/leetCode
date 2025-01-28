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
public class Merge_k_Sorted_Lists {

    public static void main(String[] args) {

        Merge_k_Sorted_Lists out = new Merge_k_Sorted_Lists();
        Solution s = out.new Solution();

        ListNode l1 = null;
        ListNode l2 = new ListNode(1);

        s.mergeKLists(new ListNode[]{l1, l2});

    }

    public class Solution {
        public ListNode mergeKLists(ListNode[] lists) {

            if (lists == null || lists.length == 0) {
                return null;
            }

            // same as merge sort array
            return merge(lists, 0, lists.length - 1);
        }

        public ListNode merge(ListNode[] lists, int start, int end) {

            // single list
            if (start == end) {
                return lists[start];
            }

            int mid = (end - start) / 2 + start;
            ListNode leftHalf = merge(lists, start, mid);
            ListNode rightHalf = merge(lists, mid + 1, end);

            return mergeTwoLists(leftHalf, rightHalf);
        }

        // from previous question: 21 Merge Two Sorted Lists
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            while (l1 != null || l2 != null) {
                int v1 = (l1 == null ? Integer.MAX_VALUE : l1.val);
                int v2 = (l2 == null ? Integer.MAX_VALUE : l2.val);

                if (v1 < v2) {
                    current.next = l1;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    l2 = l2.next;
                }

                current = current.next; // now current is the new end node, but still pointing to next node
                current.next = null; // @note: key, cut this node from l1 or l2
            }

            return dummy.next;
        }
    }

}

//////

class Solution_Heap {
	public ListNode mergeKLists(ListNode[] lists) {

		if (lists == null || lists.length == 0) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		ListNode current = dummy;

		// put 1st of each list to heap
		PriorityQueue<ListNode> heap = new PriorityQueue<>(
			(a,b) -> a.val - b.val
		);

		//
		Arrays.stream(lists).filter(Objects::nonNull).forEach(heap::offer);

		while (heap.size() != 0) {
			ListNode polled = heap.poll();

			current.next = polled;
			current = current.next;

			if (polled.next != null) {
				heap.offer(polled.next); // @note: heap.offer()参数不能是null
			}
		}

		return dummy.next;
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
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }
        for (int i = 0; i < n - 1; ++i) {
            lists[i + 1] = mergeLists(lists[i], lists[i + 1]);
        }
        return lists[n - 1];
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}