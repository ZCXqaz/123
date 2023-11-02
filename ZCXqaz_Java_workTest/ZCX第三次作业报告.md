

# JavaClub-HW3-y2023-计科2205-张晨旭



# 第三次作业报告

姓名：张晨旭

班级：计科2205



## T291125 Tower of Hanoi

题目链接：[Tower of Hanoi](https://www.luogu.com.cn/problem/T291125)

时间复杂度：$O( \log_2 n )$

空间复杂度：$O(1)$

#### 思路

首先是简单的数学问题，汉诺塔的移动步数是$2^n-1$，但本题 n 较大，采用快速幂加速。

#### 代码

```java
// ZCX作业训练

import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e3 + 5;

    public static void main(String[] args) {

        long n;
        n = sc.nextLong();
        long ans = Solution.quic_pow(2, n, 998244353) - 1;
        System.out.println(ans);
    }
}

class Solution {
    public static long quic_pow(long a, long b, long mod) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * a) % mod;
            }
            b >>= 1;
            a = a * a % mod;
        }
        return ans;
    }
}
```





## T291123 Barmecide Feast

题目链接：[Barmecide Feast ](https://www.luogu.com.cn/problem/T291123)

时间复杂度：$O( 1 )$

空间复杂度：$O(1)$

#### 思路

经过计算，易得：在第 i 刀，会对结果产生 $ans + i$ 的影响，因此符合等差数列的形式，使用公式 $O(1)$ 求解。

#### 代码

```java
// ZCX作业训练

import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e3 + 5;

    public static void main(String[] args) {

        long n;
        n = sc.nextLong();
        long ans = (n + 1) * n / 2 + 1;
        System.out.println(ans);
    }
}

```





## T291920 Countdown to Death

题目链接：[T291920 Countdown to Death](https://www.luogu.com.cn/problem/T291920)

时间复杂度：$O( n )$

空间复杂度：$O(1)$

#### 思路

每次出队一人，在约瑟夫环上的每个人都向前移动了 *k* 位。 那么如果要反着来，就是每次加上一个人，在约瑟夫环上的每个人都向后移动了 *k* 位。  以此类推， 那么可以得出递推公式：

$f(1)=0$

$f(n)=(f(n-1)+k) \% n$

最后再从0编号起始转为1起始就行。

#### 代码

```java
// ZCX作业训练

import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e3 + 5;

    public static void main(String[] args) {

        long n, k;
        n = sc.nextLong();
        k = sc.nextLong();
        System.out.println(Solution.ysf_fi(n, k) + 1);
    }
}

class Solution {
    public static long ysf_fi(long nn, long kk) {
        long ans = 0;
        for (int i = 2; i <= nn; i++) {
            ans = (ans + kk) % i;
        }
        return ans;
    }
}
```



## U375671 能被整除的数

题目链接：[U375671 能被整除的数](https://www.luogu.com.cn/problem/U375671)

时间复杂度：$O( m\times \log_2 m )$

空间复杂度：$O(m)$

#### 思路

容斥原理、二进制枚举

首先，1~n中能被p整除的数字的个数为n/p下取整，能被整除的数字分别为*p, 2 p, 3 p ... k p*。

根据容斥原理，答案=能被一个p整除的数字个数-能被两个p整除的数字个数+能被三个p整除的数字个数-...，即加上奇数个质数整除的数字个数，减去偶数个质数整除的数字个数。

这样的话思路就比较清晰了，直接二进制枚举每种情况下取的数，利用容斥原理的奇加偶减快速判断这种情况的数量对结果的影响。

#### 代码

```java
// ZCX作业训练

import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e3 + 5;

    public static long[] p = new long[20];

    public static long gcd(long aa, long bb) {
        return bb != 0 ? gcd(bb, aa % bb) : aa;
    }

    public static long lcm(long aa, long bb) {
        return aa * bb / gcd(aa, bb);
    }

    public static void main(String[] args) {

        long n, m;
        n = sc.nextLong();
        m = sc.nextLong();
        for (int i = 1; i <= m; i++) {
            p[i] = sc.nextLong();
        }
        long ans = 0;
        for (int i = 1; i <= (1 << m) - 1; i++) {
            long flag = 0;     //奇加偶减
            long mod = 1;       //某几个数的最小公倍数
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) == 0) {
                    continue;   //没有取这个数
                }
                flag++;
                mod = lcm(mod, p[j + 1]);
                if (mod > n) {
                    mod = 0;    //mod 太大，比 n 还大了，舍去
                    break;
                }
            }
            if (mod == 0) continue;
            if (flag % 2 == 1) {
                ans += n / mod;
            } else {
                ans -= n / mod;
            }
        }
        System.out.println(ans);
    }
}

```







## 01背包问题

题目链接：[2. 01背包问题 - AcWing题库](https://www.acwing.com/problem/content/description/2/)

时间复杂度：$O( n \times m )$

空间复杂度：$O(m)$

#### 思路

最初级的 01 背包模板题，动态规划思想解决，使用自我滚动数组将空间优化为$O(n)$。

#### 代码

```java
// ZCX作业训练

import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 1e3 + 5;

    public static int[] dp = new int[N];

    public static void main(String[] args) {

        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int v, w;
            v = sc.nextInt();
            w = sc.nextInt();
            for (int j = m; j >= v; j--) {
                dp[j] = Math.max(dp[j], dp[j - v] + w);
            }
        }
        System.out.println(dp[m]);

    }
}

```





## U375865 整数拆分

题目链接：[U375865 整数拆分 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/U375865)

时间复杂度：$O( n\times m )$

空间复杂度：$O(n \times m)$

#### 思路

(吐槽：这题的难度简直是跨越式的提升啊！)

采用动态规划递推每种情况的状态。

1. 当n==1或k == 1时，都只有一种拆分方案。

2. 当n==k时，根据拆分出来的数是否包含n，可以分成两种情况。

   * 拆分出来的整数包含n，那就只有一种情况，即{n}。

   - 拆分出来的整数不包含n，那么这些拆分出来的数中，一定比n小，即n的所有（n-1）拆分。因此 $dp[n][k]=1+dp[n][k-1]$

3. 当 n < k 时，因为拆分出来的最大数永远不可能达到k。所以等价于 k - 1 时的状态。因此 $dp[n][k]=dp[n][k-1]$

4. 当n>k时，根据拆分出来的整数中是否包含k，可以分为两种情况：

   - 拆分出来的整数中包含k，这种情况的拆分数是$dp[n-k][k]$。
   - 拆分出来的整数中不包含k的情况，则拆分出来的整数中所有值都比k小，则等价于$dp[n][k-1]$。

   所以$dp[n][k]=dp[n-k][k]+dp[n][k-1]$。

本来这题的空间复杂度不对的，按照计算，空间极限情况下应该为 800M 左右，超过题目限制8倍，因此尝试了很长一段时间五边形数定理。（真离谱，感觉严重超纲，太痛苦了），最后尝试重写 dp ，结果数组动态开点优化竟然能卡过？！！

#### 代码

```java
// ZCX作业训练

import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int N = (int) 5e3 + 5;
    public static int mod = (int) 1e9 + 7;

    //public static int[][] dp = new int[N][N];

    public static void main(String[] args) {
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] dp=new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 || j == 1) {
                    dp[i][j] = 1;
                } else if (i == j) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else if (i < j) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - j][j] + dp[i][j - 1];
                }
                dp[i][j] %= mod;
            }
        }
        System.out.println(dp[n][m]);
    }
}

```





## 编辑距离

题目链接：[72. 编辑距离 - 力扣（LeetCode）](https://leetcode.cn/problems/edit-distance/)

时间复杂度：$O( n \times m )$

空间复杂度：$O(n \times m)$

#### 思路

也是动态规划题。

1、i==0时，即a为空，那么对应的$f[0][j]$的值就为j：增加j个字符，使a转化为b

2、j==0时，即b为空，那么对应的$f[i][0]$的值就为i：减少i个字符，使a转化为b

3、然后考虑一般情况：

第①种情况，在最后将a[j]加上，总共就需要$dp[i][j-1]+1$次操作。

第②种情况，在最后将a[i]删除，总共需要$dp[i-1][j]+1$个操作。

第③种情况，在最后将a[i]替换为b[j]，需要$dp[i-1][j-1]+1$个操作。但如果a[i]刚好等于b[j]，就不用再替换了，那就只需要$dp[i-1][j-1]$个操作。

三种情况取最小即可。

#### 代码

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                int pan = 0;
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) pan = 1;
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + pan);
            }
        }
        return dp[n][m];
    }
}
```







## 162. 寻找峰值

题目链接：[162. 寻找峰值 - 力扣（LeetCode）](https://leetcode.cn/problems/find-peak-element/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china)

时间复杂度：$O( \log_2 n )$

空间复杂度：$O(1)$

#### 思路

大致思路为二分查找的优化查询。

根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m - 1，如果 m + 1 较大，则右侧存在峰值，l = m + 1

这题唯一麻烦就是边界处理有点麻烦，不太美观。

#### 代码

```java
class Solution {
    public static int INF = 0x3f3f3f3f;

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1, mid = (l + r) / 2;
        if (l == r) return 0;
        while (l <= r) {
            mid = (l + r) >> 1;
            int numL = nums[mid];
            int numR;
            if (mid + 1 >= n) numR = INF;
            else numR = nums[mid + 1];
            if (numL < numR) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (0 <= l && l <= n - 1) {
            if (0 <= r && r <= n - 1) {
                return nums[l] > nums[r] ? l : r;
            } else {
                return l;
            }
        } else {
            return r;
        }
    }
}
```





## 1901. Find a Peak Element II

题目链接：[Find a Peak Element II - LeetCode](https://leetcode.com/problems/find-a-peak-element-ii/)

时间复杂度：$O( m\log_2 n )$

空间复杂度：$O(m)$

#### 思路

利用一维情况下的 $O(log_2 n)$ 写法，只是在判断大小的步骤时遍历这一维数组求改行的最大值，因此时间复杂度再乘上一个求最值的$O(m)$即可。

#### 代码

```java
class Solution {
    public final int INF = 0x3f3f3f3f;

    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int l = 0, r = n - 1, mid;
        while (l <= r) {
            mid = (l + r) >> 1;
            int max1 = max(mat[mid]);
            int max2;
            if (mid + 1 >= n) max2 = -1;
            else max2 = max(mat[mid + 1]);
            if (max1 < max2) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if(0<=l&&l<=n-1){
            if(0<=r&&r<=m-1){
                return max(mat[l])>max(mat[r])?cha(l,mat[l]):cha(r,mat[r]);
            }else{
                return cha(l,mat[l]);
            }
        }else{
            return cha(r,mat[r]);
        }
    }

    public int max(int[] a) {
        int nn = a.length;
        int max1 = -INF;
        for (int i = 0; i <= nn - 1; i++) {
            max1 = Math.max(max1, a[i]);
        }
        return max1;
    }

    public int[] cha(int x, int[] a) {
        int wei = 0;
        int val = a[0];
        int nn = a.length;
        for (int j = 0; j <= nn - 1; j++) {
            if (a[j] > val) {
                val = a[j];
                wei = j;
            }
        }
        return new int[]{x, wei};
    }
}
```





