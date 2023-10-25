# 第一次作业报告

姓名：张晨旭

班级：计科2205

## 001. 3 或 5 的倍数

题目链接：[U264430 001. 3 或 5 的倍数](https://www.luogu.com.cn/problem/U264430)

时间复杂度：$O( 1 )$

空间复杂度：$O(1)$

#### 思路

用等差数列求和公式分别求3、5、15的数列和，结果就是3和5的数列和减去多加的一次15的和。

#### 代码

```java
// zcx 练习代码

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Main {
    final int N = (int)2e6+5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n;
        n=sc.nextLong();
        n--;
        long n3=n-n%3;
        long n5=n-n%5;
        long n15=n-n%15;

        long a=(n3+3)*(n3/3)/2;
        long b=(n5+5)*(n5/5)/2;
        long c=(n15+15)*(n15/15)/2;
        if(n3<3)n3=0;
        if(n5<5)n5=0;
        if(n15<15)n15=0;

        long sum=a+b-c;
        System.out.println(sum);

    }
}
```



## 002. 偶数斐波那契数

题目链接：[U264934 002. 偶数斐波那契数 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/U264934)

时间复杂度：$O( n )$

空间复杂度：$O(1)$

#### 思路

斐波那契数列的惯用求法，线性求一边所有范围内的斐波那契数，符合条件的直接加，$O(n)$实现

#### 代码

```java
// zcx 练习代码

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Main {
    final int N = (int)2e6+5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        n=sc.nextInt();
        int[] a=new int[5];
        a[0]=a[1]=1;
        a[2]=2;
        long sum=0;
        while(a[2]<=n){
            if(a[2]%2==0){
                sum+=a[2];
            }
            a[0]=a[1];
            a[1]=a[2];
            a[2]=a[0]+a[1];
        }
        System.out.println(sum);

    }
}

```





## 003. 最大质因数

题目链接：[U264937 003. 最大质因数 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/U264937))

时间复杂度：$O( \sqrt n )$

空间复杂度：$O(1)$

#### 思路

改造判断质数的方法，在判断过程中不断记录最新的因数，同时不断将该因数从中除去，维持之后的因数全为质数的状态。

最优情况$O(\log_2(n))$，最差情况下也保持$O(\sqrt n)$的优秀复杂度。

#### 代码

```java
// zcx 练习代码

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Main {
    final int N = (int)2e6+5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n;
        n=sc.nextLong();

        long z=2;
        for(long i=2;i*i<=n;i++){
            while(n%i==0){
                n/=i;
                z=Math.max(z,i);
            }
        }
        z=Math.max(z,n);
        System.out.println(z);
    }
}

```



## 004. 最大回文数乘积

题目链接：[U264963 004. 最大回文数乘积 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/U264963)

时间复杂度：$O( 10^{2n} )$，n小于等于4，即不超过 1e8

空间复杂度：$O(1)$

#### 思路

非常暴力的枚举所有的n位数，比较所有情况的最大值（不过在赛场上这题一眼打表解决（乐））

#### 代码

```java
// zcx 练习代码

import java.util.*;
import java.lang.*;

public class Main {
    final int N = (int)2e6+5;
    static int reverse(int aa){
        int bb=0;
        while(aa>0){
            bb=bb*10+aa%10;
            aa/=10;
        }
        return bb;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        n=sc.nextInt();
        if(n==1){
            System.out.println(9);
        }
        else if(n==2){
            System.out.println(9009);
        }
        else if(n==3){
            int ans=0;
            for(int i=100;i<=999;i++){
                for(int j=i;j<=999;j++){
                    int sum=i*j;
                    if(sum==reverse(sum)){
                        ans=Math.max(ans,sum);
                    }
                }
            }
            System.out.println(ans);
        }
        else{
            int ans=0;
            for(int i=1000;i<=9999;i++){
                for(int j=i;j<=9999;j++){
                    int sum=i*j;
                    if(sum==reverse(sum)){
                        ans=Math.max(ans,sum);
                    }
                }
            }
            System.out.println(ans);
        }


    }
}

```



## 005. 最小公倍数

题目链接：

时间复杂度：$O( n*log(n) )$

空间复杂度：$O(1)$

#### 思路

两个数的最小公倍数=$a*b/gcd(a,b)$，同理，此公式可以用来线性求解1-n，（易证：此算法具有传递性），最快的$gcd(a,b)$采用递归写法，约为$log(n)$层级，因此总复杂度为$O(n*log(n))$级别，只是这一题的数值增长迅速，差点爆了 long 。（离谱）



#### 代码

```java
// zcx 练习代码

import java.util.*;
import java.lang.*;

public class Main {
    final int N = (int)2e6+5;
    static long gcd(long aa,long bb){
        return bb!=0l?gcd(bb,aa%bb):aa;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n;
        n=sc.nextInt();
        long ans=1;
        for(long i=1;i<=n;i++){
            ans=ans*i/gcd(ans,i);
        }
        System.out.println(ans);

    }
}

```



## 011. 网格中的最大乘积

题目链接：[U265346 011. 网格中的最大乘积 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/U265346)

时间复杂度：$O( n*m*q )$

空间复杂度：$O(n*m)$

#### 思路

非常经典的暴力题，直接$O(n*m)$的枚举每个位置，再线性取q个所需的数，记录几种不同情况下的最大值即可。

#### 代码

```java
// zcx 练习代码

import java.util.*;
import java.lang.*;

public class Main {
    static int N = (int)1e2+5;
    static long[][] a=new long[N][N];
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {

        long n,m,q;
        n=sc.nextInt();
        m=sc.nextInt();
        q=sc.nextInt();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                a[i][j]=sc.nextInt();
            }
        }
        long max1=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                long[] ans=new long[5];
                ans[1]=ans[2]=ans[3]=ans[4]=1;
                for(int k=0;k<q;k++){
                    if(i+k<=n){
                        ans[1]*=a[i+k][j];
                    }else{
                        ans[1]=0;
                    }
                    if(j+k<=m){
                        ans[2]*=a[i][j+k];
                    }else{
                        ans[2]=0;
                    }
                    if(i+k<=n&&j+k<=m){
                        ans[3]*=a[i+k][j+k];
                    }else{
                        ans[3]=0;
                    }
                    if(i+k<=n&&j-k>=1){
                        ans[4]*=a[i+k][j-k];
                    }else{
                        ans[4]=0;
                    }
                }
                max1=Math.max(max1,ans[1]);
                max1=Math.max(max1,ans[2]);
                max1=Math.max(max1,ans[3]);
                max1=Math.max(max1,ans[4]);
            }
        }
        System.out.println(max1);


    }
}

```



## 012. 高度可除的三角数

题目链接：[U265394 012. 高度可除的三角数 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/U265394)

时间复杂度：$O( n*\sqrt n )$

空间复杂度：$O(1)$

#### 思路

从小到大枚举此时为第几个三角数，用等差和公式求出该值，然后对该值的因子进行枚举，该枚举为$\sqrt n$ 复杂度，找到最小的结果后输出该值。

#### 代码

```java
// zcx 练习代码

import java.util.*;
import java.lang.*;

public class Main {
    static int N = (int)1e2+5;
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {

        int n;
        n=sc.nextInt();

        for(int i=1;i<=1e9;i++){
            int ans=(1+i)*i/2;
            int cnt=0;
            for(int j=1;j*j<=ans;j++){
                if(ans%j==0){
                    if(j*j==ans)cnt++;
                    else cnt+=2;
                }
                if(cnt>n){
                    System.out.println(ans);
                    return;
                }
            }
        }


    }
}
```



## 014. 最长的考拉兹序列

题目链接：[U265423 014. 最长的考拉兹序列 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/U265423)

时间复杂度：$O( n*log(n) )$      ?  疑似，考拉兹序列的具体求算不能确定。

空间复杂度：$O(n)$

#### 思路

正常的递归求解，为了防止超时进行了记忆化操作。

#### 代码

```java
// zcx 练习代码

import java.util.*;
import java.lang.*;

public class Main {
    static Scanner sc=new Scanner(System.in);
    static int N = (int)1e6+5;
    static long[] ans=new long[N];

    static long find(long x){
        if(x<=N-5&&ans[(int)x]!=0)return ans[(int)x];
        if(x<=N-5) {
            if (x % 2 == 1) {
                ans[(int)x] = find(x * 3 + 1) + 1;
            }
            else{
                ans[(int)x] = find(x / 2) + 1;
            }
            return ans[(int)x];
        }
        else{
            if(x%2==1)return find(x*3+1)+1;
            else return find(x/2)+1;
        }
    }

    public static void main(String[] args) {

        int n;
        n=sc.nextInt();

        ans[1]=1;
        long max1=1;
        long cnt=1;
        for(int i=1;i<=n;i++){
            long z=find(i);
            if(z>cnt){
                max1=i;
                cnt=z;
            }
        }
        System.out.println(max1+" "+cnt);


    }
}
```



