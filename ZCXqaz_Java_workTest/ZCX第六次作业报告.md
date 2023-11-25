# JavaClub-HW6-Y2023-计科2205-张晨旭



# 第六次作业报告

姓名：张晨旭

班级：计科2205



## 429. N 叉树的层序遍历

题目链接：[429. N 叉树的层序遍历](https://leetcode.cn/problems/n-ary-tree-level-order-traversal/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

题目要求输出该树的每一层节点，则直接利用 bfs 搜索结点并记录即可。

#### 代码

```java
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>>ans=new ArrayList<>();
        Deque<Node>q=new ArrayDeque<>();
        if(root==null)return ans;
        q.addLast(root);
        while(!q.isEmpty()){
            int m=q.size();
            List<Integer> addlast=new ArrayList<>();
            while(m-->0){
                Node u=q.pollFirst();
                for(Node v:u.children){
                    q.addLast(v);
                }
                addlast.add(u.val);
            }
            ans.add(addlast);
        }

        return ans;
    }
}
```





## 113. 路径总和 II

题目链接：[113. 路径总和 II](https://leetcode.cn/problems/path-sum-ii/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

dfs+回溯，对于每条到叶结点的路径进行判断是否满足条件，并在搜索的过程中进行回溯。

#### 代码

```java
class Solution {
    LinkedList<List<Integer>> ans = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root!=null) {
            dfs(root, targetSum);
        }
        return ans;
    }

    void dfs(TreeNode u, int sum) {
        path.add(u.val);
        sum -= u.val;
        if (sum == 0 && u.left == null && u.right == null) {
            ans.add(new LinkedList<Integer>(path));
        }
        if (u.left != null) {
            dfs(u.left, sum);
        }
        if (u.right != null) {
            dfs(u.right, sum);
        }
        path.removeLast();
    }
}
```





## 129. 求根节点到叶节点数字之和

题目链接：[129. 求根节点到叶节点数字之和](https://leetcode.cn/problems/sum-root-to-leaf-numbers/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

dfs向下搜索，在过程中更新此时的 x 值，在达到叶子节点时返回即可。

#### 代码

```java
class Solution {
    public int sumNumbers(TreeNode root) {
        int ans=0;
        if(root!=null){
            ans+=dfs(root,0);
        }
        return ans;
    }
    public int dfs(TreeNode u,int x){
        x=x*10+u.val;
        if(u.left==null&&u.right==null){
            return x;
        }
        int ans=0;
        if(u.left!=null){
            ans+=dfs(u.left,x);
        }
        if(u.right!=null){
            ans+=dfs(u.right,x);
        }
        return ans;
    }
}
```





## 迷宫问题

题目链接：[迷宫问题](https://www.nowcoder.com/practice/cf24906056f4488c9ddb132f317e03bc)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

相当经典的二维平面图寻路算法，只求一条可行解则 dfs 较优，不断走可行的路，直达走到尽头，如果不行，就回溯查询新的岔路，如果可以，则记录路径。

#### 代码

```java
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static int n, m;
    public static int[][] p;
    public static int[][] pan;
    public static int[] dx = new int[] {-1, 0, 1, 0};
    public static int[] dy = new int[] {0, 1, 0, -1};
    public static List<zcx> path = new ArrayList<>();
    public static List<zcx>ans = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            n = in.nextInt();
            m = in.nextInt();
            p = new int[n + 1][m + 1];
            pan = new int[n + 1][m + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    p[i][j] = in.nextInt();
                }
            }

            dfs(0, 0);
            for (zcx z : ans) {
                System.out.println("(" + z.x + "," + z.y + ")");
            }
        }
    }
    public static void dfs(int x, int y) {
        path.add(new zcx(x, y));
        pan[x][y] = 1;
        if (x == n - 1 && y == m - 1) {
            ans = new ArrayList<>(path);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx <= n - 1 && ny >= 0 && ny <= m - 1 && pan[nx][ny] == 0 && p[nx][ny] == 0) {
                dfs(nx, ny);
            }
        }
        path.remove(path.size() - 1);
        pan[x][y] = 0;
    }

    public static class zcx {
        int x, y;
        public zcx(int xx, int yy) {
            this.x = xx;
            this.y = yy;
        }
    }
}

```





## 2424. 最长上传前缀

题目链接：[2424. 最长上传前缀](https://leetcode.cn/problems/longest-uploaded-prefix/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

本题普通暴力查询是否插入了 1~x 个数的算法为$O(n^2)$，无法通过本题，我使用查询优化的并查集算法，将多次重复的查询优化至常数阶复杂度，可以通过。

#### 代码

```java
class LUPrefix {
    public static int n;
    public static int[] root;
    public static int fi(int x) {
        if (x != root[x]) {
            root[x] = fi(root[x]);
        }
        return root[x];
    }
    public LUPrefix(int n) {
        this.n = n;
        root = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }
    }
    public void upload(int video) {
        root[video - 1] = video;
    }
    public int longest() {
        int len = fi(0);
        return len;
    }
}
```





## 1722. 执行交换操作后的最小汉明距离

题目链接：[1722. 执行交换操作后的最小汉明距离](https://leetcode.cn/problems/minimize-hamming-distance-after-swap-operations/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

如果两个下标可以通过一条边或多条边相连，则两个下标在图中的同一个连通分量，一定可以经过一次或多次交换操作实现这两个下标处的元素的交换。如果两个下标在图中的不同连通分量，则无法通过交换操作实现这两个下标处的元素下标。

根据所有下标组成的连通分量，分别计算每个连通分量中的数组 a 和 b 的元素差异，根据元素差异计算最小汉明距离。对于每个 $0<=i<=n-1$，遍历数组a,b。

注意，在本题中，**允许集合中的元素出现重复（被坑了，痛苦调一晚上）**。

#### 代码

```java
class Solution {
    public static int[] a;
    public static int[] b;
    public static int[][] al;
    public static int[] root;

    public static int fi(int x) {
        if (x != root[x]) {
            root[x] = fi(root[x]);
        }
        return root[x];
    }

    public static int merge(int aa, int bb) {
        aa = fi(aa);
        bb = fi(bb);
        root[aa] = bb;
        return 1;
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        a = source.clone();
        b = target.clone();
        al = allowedSwaps.clone();
        return solve();
    }

    public static int solve() {
        int n = a.length;
        int ans = 0;
        root = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            root[i] = i;
        }
        for (int[] step : al) {
            merge(step[0], step[1]);
        }
        Map<Integer, Map<Integer,Integer>> ha = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //ha[fi(i)].insert(a[i]);
            Map<Integer,Integer> step = ha.getOrDefault(fi(i), new HashMap<>());
            //step.add(a[i]);
            int sum=step.getOrDefault(a[i],0)+1;
            step.put(a[i],sum);
            ha.put(fi(i), step);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer,Integer> step = ha.getOrDefault(fi(i), new HashMap<>());
            if (step.containsKey(b[i])) {
                //step.remove(b[i]);
                int sum=step.getOrDefault(b[i],0);
                sum--;
                if(sum!=0){
                    step.put(b[i],sum);
                }else{
                    step.remove(b[i]);
                }
            } else {
                res++;
            }
        }
        return res;
    }
```





## 1361. 验证二叉树

题目链接：[1361. 验证二叉树](https://leetcode.cn/problems/validate-binary-tree-nodes/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

首先判断每个点的入度，入度为零的唯一点就是根节点，不唯一则返回false。然后 dfs 从根节点开始遍历，判断是否有环。最后查询一便遍历节点，判断是否联通。三重判断结束，即可确认是否为树。

#### 代码

```java
class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] ru = new int[n];
        for (int i = 0; i < n; i++) {
            if (leftChild[i] >= 0)
                ru[leftChild[i]]++;
            if (rightChild[i] >= 0)
                ru[rightChild[i]]++;
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (ru[i] == 0) {
                if (root != -1) {
                    return false;
                }
                root = i;
            }
        }
        if (root == -1) return false;
        pan = new int[n];
        dfs(root, n, leftChild, rightChild);
        for(int i=0;i<n;i++){
            if(pan[i]==0)flag=0;
        }
        if (flag == 0) return false;
        else return true;
    }

    public int flag = 1;
    public int[] pan;

    public void dfs(int u, int n, int[] lc, int[] rc) {
        pan[u] = 1;
        if (flag == 0) return;
        if (lc[u] >= 0) {
            if (pan[lc[u]] != 0) {
                flag = 0;
            }
            dfs(lc[u], n, lc, rc);
        }
        if (rc[u] >= 0) {
            if (pan[rc[u]] != 0) {
                flag = 0;
            }
            dfs(rc[u], n, lc, rc);
        }
    }
}
```





## LCR 067. 数组中两个数的最大异或值

题目链接：[LCR 067. 数组中两个数的最大异或值 - 力扣（LeetCode）](https://leetcode.cn/problems/ms70jA/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

根据按位异或运算的性质，$x = a[i] \bigoplus a[j] $等价于$a[i]=x \bigoplus a[j]$.

按照位运算的思想，从最高位向最低位贪心遍历，对每一位判断如果该位取 1 ，是否可以取更大值。该判断时间复杂度通过 Hash 优化可以达到$O(1)$。



#### 代码

```java
class Solution {
    public int findMaximumXOR(int[] nums) {
        int x=0;
        for(int k=30;k>=0;k--){
            Set<Integer>sx=new HashSet<>();
            for(int num:nums){
                sx.add(num>>k);
            }
            int nx=x*2+1;
            int flag=0;
            for(int num:nums){
                int sy=(nx^(num>>k));
                if(sx.contains(sy)){
                    flag=1;
                    break;
                }
            }
            x=nx;
            if(flag==0){
                x--;
            }
        }
        return x;
    }
}
```





## 1061. 按字典序排列最小的等效字符串

题目链接：[1061. 按字典序排列最小的等效字符串](https://leetcode.cn/problems/lexicographically-smallest-equivalent-string/)

时间复杂度：$O( n )$

空间复杂度：$O(n)$

#### 思路

有向并查集将每一个字符指向可以转换成的字典序最小字符，再对结果字符串进行一次转换即可。

#### 代码

```java
class Solution {
    public int[] root;

    public int fi(int x) {
        if (x != root[x]) {
            root[x] = fi(root[x]);
        }
        return root[x];
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        root = new int[30];
        for (int i = 0; i < 30; i++) {
            root[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int u1 = s1.charAt(i) - 'a';
            int u2 = s2.charAt(i) - 'a';
            u1 = fi(u1);
            u2 = fi(u2);
            if (u1 > u2) {
                int temp = u1;
                u1 = u2;
                u2 = temp;
            }
            root[u2] = u1;
        }
        StringBuilder ans=new StringBuilder();
        int m=baseStr.length();
        for(int i=0;i<m;i++){
            int c=baseStr.charAt(i)-'a';
            c=fi(c);
            ans.append((char)('a'+c));
        }
        return ans.toString();
    }
}
```



