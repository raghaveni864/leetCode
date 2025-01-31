import java.util.Arrays;

public class Next_Permutation {

    // time: O(N^2)
    // space: O(1)
    public class Solution {

        public void nextPermutation(int[] nums) {

            if (nums == null || nums.length == 0) {
                return;
            }

            // 总体目标是，高位的小数字，换低位的大数字，才能得到next
            for (int i = nums.length - 2; i >= 0; --i) { // 3, 4, 5, 2, 1 // 注意. i < Len - 1. 也就是停在倒数第二个
                if (nums[i] < nums[i + 1]) { // 第一个波峰波谷 => 4
                    for (int j = nums.length - 1; j > i; --j) {
                        if (nums[j] > nums[i]) {
                            // 找到第一个比nums-i大的数 => 5
                            swap(nums, i, j); // 3,5,4,2,1

                            // reverse 因为剩下部分肯定是从大到小
                            // 找到第一个比nums-i大的数的一步，相当于是排序，找insert position
                            reverse(nums, i + 1, nums.length - 1); // [4,2,1] reverse to [1,2,4] => 3, 5, 1, 2, 4
                            return;
                        }
                    }

                }
            }

            reverse(nums, 0, nums.length - 1); // for没有return，就整个翻转
        }

        private void swap(int[] nums, int i, int j) {

            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;

        }

        private void reverse(int[] nums, int i, int j) {

            while (i < j) {

                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;

                i++;
                j--;
            }
        }
    }
}

//////

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        for (; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i >= 0) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }

        for (int j = i + 1, k = n - 1; j < k; ++j, --k) {
            swap(nums, j, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}