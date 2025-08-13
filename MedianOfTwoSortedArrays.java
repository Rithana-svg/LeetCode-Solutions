class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Make sure nums1 is the smaller array to minimize binary search space
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        int totalLeft = (m + n + 1) / 2; // Number of elements in the left half
        int left = 0;
        int right = m; // Binary search on nums1
        
        while (left <= right) {
            int i = (left + right) / 2; // Partition index for nums1
            int j = totalLeft - i;      // Partition index for nums2
            
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
            
            // Check if we found the correct partition
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // Odd total length → median is the max on the left side
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } 
                // Even total length → average of two middle values
                else {
                    return (Math.max(nums1LeftMax, nums2LeftMax) 
                          + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } 
            // Adjust binary search range
            else if (nums1LeftMax > nums2RightMin) {
                right = i - 1; // too many elements from nums1 in left half
            } else {
                left = i + 1; // too few elements from nums1 in left half
            }
        }
        
        throw new IllegalArgumentException("Input arrays not sorted or invalid");
    }
}