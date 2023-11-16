# JavaClub-HW5-Y2023-计科2205-张晨旭



# 第五次作业报告

姓名：张晨旭

班级：计科2205



## 15. 三数之和

题目链接：[15. 三数之和 - 力扣（LeetCode）](https://leetcode.cn/problems/3sum/)

时间复杂度：$O( n^2 )$

空间复杂度：$O(1)$

#### 思路

在排序之后的数组中进行双指针维护三数和为零的状态，$O(n)$遍历第一个数，$l,r$分别指向另外的两数，左指针 $l=i+1$，右指针 $r=n-1$，执行循环$l<r$，唯一麻烦在去重。

#### 代码

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>>res=new ArrayList<>();
        int n=nums.length;
        if(n<3)return res;
        Arrays.sort(nums);
        for(int i=0;i<n;i++){
            if(nums[i]>0){
                break;
            }
            if(nums[i]==nums[i-1])continue;
            int l=i+1,r=n-1;
            while(l<r){
                int sum=nums[i]+nums[l]+nums[r];
                if(sum==0){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while(l<r&&nums[l]==nums[l+1]){
                        l++;
                    }
                    while(l<r&&nums[r]==nums[r-1]){
                        r--;
                    }
                    l++;
                    r--;
                }
                else if(sum<0){
                    l++;
                }else{
                    r--;
                }
            }
        }
        return res;
    }
}
```





## 475. 供暖器

题目链接：[475. 供暖器 - 力扣（LeetCode）](https://leetcode.cn/problems/heaters/description/)

时间复杂度：$O( n \times log_2 m )$

空间复杂度：$O(1)$

#### 思路

对加热器位置排序之后进行二分查找，对这个房子来说最近的加热器距离，只需遍历所有的房子进行二分查找即可。

#### 代码

```java
class Solution {
    public static int INF = 0x3f3f3f3f;

    public int getmin(int x, int[] heaters) {
        int l = 0, r = heaters.length - 1, mid;
        int n = r;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (heaters[mid] < x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        int ans = INF;
        if (r >= 0 && r <= n) {
            ans = Math.min(ans, x - heaters[r]);
        }
        if (l >= 0 && l <= n) {
            ans = Math.min(ans, heaters[l] - x);
        }
        return ans;
    }

    public int findRadius(int[] houses, int[] heaters) {
        int ans = 0;
        Arrays.sort(heaters);
        for (int i = 0; i < houses.length; i++) {
            ans = Math.max(ans, getmin(houses[i], heaters));
        }
        return ans;
    }
}
```





## 451. 根据字符出现频率排序

题目链接：[451. 根据字符出现频率排序 - 力扣（LeetCode）](https://leetcode.cn/problems/sort-characters-by-frequency/description/)

时间复杂度：$O( n \times log_2 n )$

空间复杂度：$O(n)$

#### 思路

先是遍历一遍统计每个字符出现的次数，然后以此为标志进行排序即可。

#### 代码

```java
class zcx {
    int sum;
    char key;
    zcx(char cc, int aa) {
        key = cc;
        sum = aa;
    }
}

class Solution {
    public String frequencySort(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<zcx> q = new PriorityQueue<>(new java.util.Comparator<zcx>() {
            @Override
            public int compare(zcx o1, zcx o2) {
                return -1 * (o1.sum - o2.sum);
            }
        });
        for (char c : map.keySet()) {
            q.add(new zcx(c, map.get(c)));
        }
        StringBuilder ans = new StringBuilder();
        while (!q.isEmpty()) {
            zcx z = q.peek();
            q.poll();
            char c = z.key;
            int sum = z.sum;
            while (sum-- > 0) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

没必要用优先队列，干脆直接放个数组里 sort 一下就行，优化一下代码。

```java
class zcx {
    int sum;
    char key;
    zcx(char cc, int aa) {
        key = cc;
        sum = aa;
    }
}

class Solution {
    public String frequencySort(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        ArrayList<zcx> z = new ArrayList<>();
        for (char c : map.keySet()) {
            int sum = map.get(c);
            z.add(new zcx(c, sum));
        }
        Collections.sort(z, (o1, o2) -> {
            if(o1.sum==o2.sum)return o1.key-o2.key;
            else return -1 * (o1.sum - o2.sum);
        });
        StringBuilder ans = new StringBuilder();
        for (zcx a : z) {
            int sum = a.sum;
            while (sum-- > 0) {
                ans.append(a.key);
            }
        }
        return ans.toString();
    }
}
```



## 2865. 美丽塔 I

题目链接：[2865. 美丽塔 I - 力扣（LeetCode）](https://leetcode.cn/problems/beautiful-towers-i/description/)

时间复杂度：$O( n^2 )$

空间复杂度：$O(1)$

#### 思路

本题的数据范围较小，因此可以使用最简单的 $O(n^2)$ 级的暴力算法，直接枚举哪个位置是波峰。

#### 代码

```java
class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long sum = maxHeights.get(i);
            long max1 = maxHeights.get(i);
            for (int j = i - 1; j >= 0; j--) {
                max1 = Math.min(max1, maxHeights.get(j));
                sum += max1;
            }
            max1 = maxHeights.get(i);
            for (int j = i + 1; j <= n - 1; j++) {
                max1 = Math.min(max1, maxHeights.get(j));
                sum += max1;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
```

**第二种做法：**

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

前后缀分解 + 单调栈

分别预处理出以每个节点作为山顶的前缀和后缀的和，那么，最佳方案就是 $pre[i]+suf[i]−maxHeight[i]$ 的最大值。

问题在于如何高效的求出$pre,suf$数组。

用$pre$举例，修改的步骤就是：寻找小于等于当前元素 xxx 的上一个元素，再将中间的元素削减为 $maxHeights[i]$。

寻找上一个更小或更大元素，正是单调栈的经典应用。

#### 代码

```java
class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long n = maxHeights.size();
        long[] l = new long[(int) n + 1];
        long[] r = new long[(int) n + 1];
        Stack<Integer> st = new Stack<>();
        for (long i = 0; i < n; i++) {
            while (!st.empty() && maxHeights.get(st.peek()) > maxHeights.get((int) i)) {
                st.pop();
            }
            long j = -1;
            if (!st.empty()) {
                j = st.peek();
            }
            st.push((int) i);
            if (j == -1) {
                l[(int) i] = (i - j) * maxHeights.get((int) i);
            } else {
                l[(int) i] = l[(int) j] + (i - j) * maxHeights.get((int) i);
            }
        }
        while (!st.empty()) st.pop();
        for (long i = n - 1; i >= 0; i--) {
            while (!st.empty() && maxHeights.get(st.peek()) > maxHeights.get((int) i)) {
                st.pop();
            }
            long j = n;
            if (!st.empty()) {
                j = st.peek();
            }
            st.push((int) i);
            if (j == n) {
                r[(int) i] = (j - i) * maxHeights.get((int) i);
            } else {
                r[(int) i] = r[(int) j] + (j - i) * maxHeights.get((int) i);
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, l[i] + r[i] - maxHeights.get(i));
        }
        return ans;
    }
}
```



## 1475. 商品折扣后的最终价格

题目链接：[1475. 商品折扣后的最终价格 - 力扣（LeetCode）](https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

问题简化为：求下一个比自己小的元素。这是单调栈的经典问题。

#### 代码

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        int n=prices.length;
        int[] ans=new int[n];
        Stack<Integer>st=new Stack<>();
        for(int i=n-1;i>=0;i--){
            while(!st.empty()&&st.peek()>prices[i]){
                st.pop();
            }
            if(st.empty()){
                ans[i]=prices[i];
            }else{
                ans[i]=prices[i]-st.peek();
            }
            st.push(prices[i]);
        }
        return ans;
    }
}
```





## 523. 连续的子数组和

题目链接：[523. 连续的子数组和 - 力扣（LeetCode）](https://leetcode.cn/problems/continuous-subarray-sum/description/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

同余定理 + 前缀和处理

快速判断一段数组值的和是不是某个数的倍数，在经过前缀和处理后只要看$sum[r]-sum[l-1]$是否为该数的倍数即可，同余定理处理后表现为$sum[r]$是否等于$sum[l-1]$，用 hashmap 处理某个数第一次出现的位置，当这个数再次出现时如果之间的间隔大于等于1，即为满足要求。

#### 代码

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] a = new int[n + 1];
        a[0] = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = (a[i - 1] + nums[i - 1]) % k;
        }
        HashMap<Integer, Integer> p = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            if (!p.containsKey(a[i])) {
                p.put(a[i], i);
            } else {
                if (i - p.get(a[i]) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
```





## 3. 无重复字符的最长子串

题目链接：[3. 无重复字符的最长子串 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/)

时间复杂度：$O( n )$

空间复杂度：$O(1)$

#### 思路

滑动窗口，遍历该字符串，在加入新字符时记录该字符出现的次数，如果大于1，则向右移动左边界，直到恢复至等于一的状态。一直维护该状态，找出队列出现最长的长度。

#### 代码

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashMap<Character, Integer> p = new HashMap<>();
        int ans = 0;
        int l = 0;
        for (int i = 0; i < n; i++) {
            p.put(s.charAt(i), p.getOrDefault(s.charAt(i), 0) + 1);
            while (p.get(s.charAt(i)) > 1) {
                p.put(s.charAt(l), p.get(s.charAt(l)) - 1);
                l++;
            }
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }
}
```



