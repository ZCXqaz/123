# 第二次作业报告

姓名：张晨旭

班级：计科2205

## 006. 和的平方与平方的和之间的差值

题目链接：[006. 和的平方与平方的和之间的差值](https://www.luogu.com.cn/problem/U265022#submit)

时间复杂度：$O( n )$

空间复杂度：$O(1)$

#### 思路

非常简单的从头到尾跑一遍求结果，线性复杂度。

#### 代码

```java
// zcx 练习代码

import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e6 + 5;

    public static void main(String[] args) {
        long n;
        n = sc.nextLong();

        long a1 = 0, a2 = 0;
        for (long i = 1; i <= n; i++) {
            a1 += i * i;
            a2 += i;
        }
        a2 = a2 * a2;
        System.out.println(a2 - a1);

    }
}
```





## 008. 序列中的最大乘积

题目链接：[008. 序列中的最大乘积](https://www.luogu.com.cn/problem/U265129)

时间复杂度：$O( n^2 )$

空间复杂度：$O(n)$

#### 思路

循环嵌套求解，用第一个指针枚举位置，第二个指针遍历应选择的数，并记录最大值。

#### 代码

```java
// zcx 练习代码

import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e6 + 5;

    public static void main(String[] args) {
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        int m = sc.nextInt();
        s = "?" + s;

        long max1 = 0;
        for (int i = 1; i <= n - m; i++) {
            long step = 1;
            for (int j = 0; j < m; j++) {
                step *= s.charAt(i + j) - '0';
            }
            max1 = Math.max(max1, step);
        }
        System.out.println(max1);

    }
}
```





## 015. 格子路径

题目链接：[015. 格子路径](https://www.luogu.com.cn/problem/U265594)

时间复杂度：$O( N\times M )$

空间复杂度：$O(N \times M)$

#### 思路

通过简单的分析可得：该位置只能由他左边的位置和上面的位置转移而来，因此可以得到状态转移方程：$dp[i][j] = dp[i - 1][j] + dp[i][j - 1]$.初始值$dp[1][1]=1$，还有一个细节就是走的是格点，因此一直要走到$dp[n+1][m+1]$才对。

#### 代码

```java
// zcx 练习代码


import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e6 + 5;

    public static void main(String[] args) {

        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        long[][] dp = new long[n + 5][m + 5];
        dp[1][1] = 1;
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= m + 1; j++) {
                if (i == 1 && j == 1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(dp[n + 1][m + 1]);

    }
}
```





## 020. 阶乘各位数的和

题目链接：[020. 阶乘各位数的和](https://www.luogu.com.cn/problem/U273769)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

高精问题，用$BigInteger$控制，线性执行一遍阶乘运算，转成字符串计算所求各位数之和。

#### 代码

```java
// zcx 练习代码

import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e6 + 5;

    public static void main(String[] args) {

        int n = sc.nextInt();
        BigInteger a = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            a = a.multiply(BigInteger.valueOf(i));
        }
        String s = a.toString();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += s.charAt(i) - '0';
        }
        System.out.println(ans);

    }
}
```





## 024. 字典排列

题目链接：[024. 字典排列](https://www.luogu.com.cn/problem/U273777)

时间复杂度：$O( 1 )$

空间复杂度：$O(1)$

#### 思路

从最高位开始向低位循环，判断这一位上应该是什么数，利用预处理出来的阶乘数组进行快捷判断，程序结束时间与 n 值大小无关，达到$O(1)$级别的快速判断，但是代码细节较多。

感觉可以用递归写呢，代码量应该会少一点，不过需要开几个public static 数组来处理中间量，为了我的头发着想，还是不折磨我自己了哈哈。

（吐槽：这题难度跟之前几题都不是一个世界的了吧！）

#### 代码

```java
// zcx 练习代码


import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e6 + 5;

    public static void main(String[] args) {

        long[] jie = new long[12];
        jie[0] = 1;
        for (int i = 1; i <= 10; i++) {
            jie[i] = jie[i - 1] * i;
        }
        int tt;
        tt = sc.nextInt();
        while (tt > 0) {
            long n = sc.nextLong();
            n--;
            if (n >= jie[10]) {
                System.out.println("WustJavaClub");
            } else {
                StringBuilder s = new StringBuilder("");
                long sum = n;
                int[] pan = new int[11];
                for (int i = 1; i <= 10; i++) {
                    long tot = sum / jie[10 - i];
                    sum -= tot * jie[10 - i];
                    tot++;
                    for (int j = 0; j <= 9; j++) {
                        if (pan[j] == 0) {
                            tot--;
                            if (tot == 0) {
                                pan[j] = 1;
                                s.append(j);
                                break;
                            }
                        }
                    }
                }
                System.out.println(s);
            }

            tt--;
        }


    }
}
```



·

## 452. 用最少数量的箭引爆气球

题目链接：[452. 用最少数量的箭引爆气球](https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/)

时间复杂度：$O( n \times log_2 n )$

空间复杂度：$O(n)$

#### 思路

非常经典的贪心题，尽可能地让刀向右侧逼近的同时覆盖到每个气球，因此对其用右顶点进行排序，每次判断用不用更新最新的最右侧刀片位置，更新的话，就记录该气球的右侧作为新刀片位置。

思路很简单，就是 java 的这个 sort 函数让我调了一个小时（离谱）

#### 代码

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        int ans = 0;
        //特判没有气球
        if (points.length == 0) return 0;
//        Arrays.sort(points, new Comparator<int[]>() {
//            public int compare(int[] o1, int[] o2) {
//                return Integer.compare(o1[1], o2[1]);
//            }
//        });
        //重写sort compare，下面是上面的优化
        // lambda表达式 + 三目运算符，更快
        Arrays.sort(points, (aa, bb) -> (aa[1] <= bb[1] ? -1 : 1));
        ans++;
        int max1 = points[0][1];//记录目前最右边的刀位置
        for (int i = 1; i < points.length; i++) {
            if (max1 < points[i][0]) {
                ans++;
                max1 = points[i][1];
            }
        }
        return ans;
    }
}
```





## 455. 分发饼干

题目链接：[455. 分发饼干](https://leetcode.cn/problems/assign-cookies/)

时间复杂度：$O( n \times \log n + m \times \log m)$

空间复杂度：$O(n+m)$

#### 思路

经典的双指针匹配问题。

先排序，再从小到大枚举拥有的值，对每个拥有的值，尽量找到一个允许范围的需求值进行匹配。经分析得：进行贪心思想优化，可以得到在枚举过程中，另一匹配的值同样具有递增性质。

因此在枚举 i 时，j 只需要遍历一遍对应数组。

#### 代码

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int n = g.length;   //需求
        int m = s.length;   //拥有
        int ans = 0;
        int j = 0;
        for (int i = 0; i < m; i++) {
            if (j < n && s[i] >= g[j]) {
                ans++;
                j++;
            }
        }
        return ans;
    }
}
```





## 11. 盛最多水的容器

题目链接：[11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

普通思路：对两个边进行枚举，但时间复杂度$O(n^2)$，无法通过，考虑优化。

考虑贪心思想，如何在枚举的过程中尽量让状态增大？

从最极端的特殊情况考虑：两边取到两端。此时将容器的面积增大，考虑双指针指向两边，（接下来用 i , j 表示），如果将两边向内移动，如果是短板移动，则可能增大；如果是长版移动，则必定减小。

因此不断向内移动较短版位置，贪心的想让结果变大，直到两版相遇，得到最大值。

#### 代码

```java
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}
```



