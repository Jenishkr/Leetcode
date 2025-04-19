class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long count = 0;

        for (int i = 0; i < nums.length; i++) {
            // Find the first index j > i such that nums[i] + nums[j] >= lower
            int left = lowerBound(nums, i + 1, lower - nums[i]);

            // Find the first index j > i such that nums[i] + nums[j] > upper
            int right = upperBound(nums, i + 1, upper - nums[i]);

            // All elements from left to right-1 are valid
            count += right - left;
        }
        return count;
    }

    private int lowerBound(int[] nums, int start, int target) {
        int low = start, high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    private int upperBound(int[] nums, int start, int target) {
        int low = start, high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}