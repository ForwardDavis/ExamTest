package niukewang.JianZhiOffer;


import java.util.*;

/**
 * @author forward
 */
public class Solution {
    /**
     * 1
     * 题目：
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 该题已通过，采用最简单的遍历二维数组的方法
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int [][] array) {
        boolean contains = false;
        outter:
        for (int i[] :array){
            for (int j:i){
                if (j == target){
                    contains = true;
                    break outter;
                }
            }
        }
        return contains;
    }

    /**
     * 2
     * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * @param str
     * @return
     *
     * 该题已通过，调用String api中的replaceAll方法即可实现这个功能，还有一种是将String转换成char数组，来替换
     */
    public static String replaceSpace(StringBuffer str) {
        String string = new String(str);
        /*char[] ch = string.toCharArray();*/
        String returnString = string.replaceAll(" ","%20");
        return returnString;
    }

    /**
     * 3
     * 输入一个链表，从尾到头打印链表每个节点的值。
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        /*
        * 方法1：
        * 通过数组保存所有的值，再逆向输出
        * 该方法可以实现功能，但是存在内存溢出或超时
        * */
        /*
        * 方法2通过用栈的方式通过全部测试用例
        * */
        Stack<Integer> stack = new Stack<>();
        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.empty()){
            list.add(stack.pop());
        }
        System.out.println(list);
        return list;
    }


    /**
     * 4：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     * @param pre
     * @param in
     * @return
     * 该题已通过，利用递归的思想来解决
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        TreeNode root = new TreeNode(pre[0]);
        int rootIndex = -1;
        for (int i = 0;i<in.length;i++){
            if (in[i] == root.val){
                rootIndex = i;
            }
        }
        int[] leftIn = new int[rootIndex];
        int[] rightIn = new int[in.length-rootIndex-1];
        for (int left=0;left<rootIndex;left++){
            leftIn[left] = in[left];
        }
        for (int right=rootIndex+1;right<in.length;right++){
            rightIn[right-(rootIndex+1)] = in[right];
        }
        int[] leftPre = getNextPre(leftIn,pre);
        int[] rightPre = getNextPre(rightIn,pre);

        if (leftIn.length==0){
            root.left = null;
        }else {
            root.left = reConstructBinaryTree(leftPre,leftIn);
        }
        if (rightIn.length ==0){
            root.right = null;
        }else {
            root.right = reConstructBinaryTree(rightPre,rightIn);
        }
        return root;
    }


    /**
     * 通过下一级的中序遍历数组和上一级的前序遍历数组，得到下一级的前序遍历数组
     * @param nextIn
     * @param pre
     * @return
     */
    public int[] getNextPre(int[] nextIn,int[] pre){
        int[] nextPre = new int[nextIn.length];
        int index = 0;
        for (int preNum : pre){
            for (int nextInNum : nextIn){
                if (preNum == nextInNum){
                    nextPre[index] = nextInNum;
                    index++;
                    break;
                }
            }
        }
        return nextPre;
    }

    /**
     * 第6题：
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * @param array
     * @return
     * 不是很明白这个题目的精髓，因为一个数组，无论有没有旋转，最小值是一样的，所以这个题目就是一个求数组最小值的算法。
     */
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0){
            return 0;
        }
        int min = array[0];
        for (int arrNum : array){
            if (arrNum < min){
                min = arrNum;
            }
        }
        return min;
    }

    /**
     * 第7题
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
     * n<=39
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        List<Integer> fibnacci = new ArrayList<>();
        //初始化最初的两个值
        fibnacci.add(1);
        fibnacci.add(1);
        int fibnacciN = 0;
        if (n == 1){
            fibnacciN = 1;
        }else if (n == 2){
            fibnacciN = 1;
        }else {
            //大于2的情况
            for (int i = 3;i<=n;i++){
                fibnacci.add(fibnacci.get(i-3)+fibnacci.get(i-2));
                fibnacciN = fibnacci.get(fibnacci.size()-1);
            }
        }
        return fibnacciN;
    }

    /**
     * 题8
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * @param target
     * @return
     * 已通过
     * 解题思路：递归
     * 1级的时候只有一次，2级的时候只有两次
     * 青蛙的第一步有两种情况，跳1级和跳2级，那么n级台阶的跳法就可以分解为 n-1级数量+n-2级的数量
     */
    public int jumpFloor(int target) {
        if (target == 1){
            return 1;
        }else if (target == 2){
            return 2;
        }else {
            return jumpFloor(target-1)+jumpFloor(target-2);
        }
    }

    /**
     * 题9：变态青蛙跳
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * 已通过
     * 解题思路，递归
     * @param target
     * @return
     */
    public int jumpFloor2(int target) {
        if (target == 0){
            return 1;
        }else if (target == 1){
            return 1;
        }else {
            int sum = 0;
            for (int i=0;i<target;i++){
                sum += jumpFloor2(i);
            }
            return sum;
        }
    }

    /**
     * 题10：矩形覆盖
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * 分析题目可得，这个题实际上就是普通青蛙跳
     * @param target
     * @return
     */
    public int RectCover(int target) {
        if (target == 1){
            return 1;
        }else if (target == 2){
            return 2;
        }else if (target == 0){
            return 0;
        }else{
            return RectCover(target-1)+RectCover(target-2);
        }
    }

    /**
     * 题11：
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 已完成，使用Integer的toBinaryString转换整形为二进制字符串
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        String binary = Integer.toBinaryString(n);
        char[] binaryChar = binary.toCharArray();
        int count = 0;
        for (char bit : binaryChar){
            if (bit == '1'){
                count++;
            }
        }
        return count;
    }

    /**
     * 题12：
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 已通过，分两种情况，指数为正和指数为负
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (exponent>=0) {
            double result = 1.0;
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            return result;
        }else {
            double reBase = 1.0/base;
            int reExp = -exponent;
            double result = reBase;
            for (int i = 1; i < reExp; i++) {
                result *= reBase;
            }
            return result;
        }
    }

    /**
     * 题13：
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * 已解决
     * @param array
     */
    public void reOrderArray(int [] array) {
        if (array.length != 0){
            int[] tempArr = new int[array.length];
            //遍历两遍数组,第一遍奇数、第二遍偶数
            int index = 0;
            for (int num: array){
                if (num % 2 == 1){
                    tempArr[index] = num;
                    index++;
                }
            }
            for (int num:array){
                if (num % 2 == 0){
                    tempArr[index] = num;
                    index++;
                }
            }
            for (int i=0;i<tempArr.length;i++){
                array[i] = tempArr[i];
            }
        }
    }

    /**
     * 题14
     * 输入一个链表，输出该链表中倒数第k个结点。
     * 已完成
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        ArrayList<ListNode> listNodes = new ArrayList<>();
        ListNode node = head;
        //将所有的链表节点放入一个顺序表中
        while (node != null){
            listNodes.add(node);
            node = node.next;
        }
        ListNode listNode = null;
        //分为两种情况来区分,如果包含倒数第k个节点，则返回这个节点，如果不包含，则返回空
        if (listNodes.size() >= k && k != 0){
            listNode = listNodes.get(listNodes.size()-k);
        }
        return listNode;
    }

    /**
     * 题15
     * 输入一个链表，反转链表后，输出链表的所有元素。
     * 已完成
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        if (head.next.next == null){
            ListNode reverHead = head.next;
            reverHead.next = head;
            head.next = null;
            return reverHead;
        }
        ListNode first = head;
        ListNode second = first.next;
        ListNode third = second.next;
        //第一步将头指针置为空
        first.next = null;
        do {
            second.next = first;
            first = second;
            second = third;
            third = third.next;
        }while (third != null);
        second.next = first;
        return second;
    }

    /**
     * 题16
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * 已完成
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode mergeHead = new ListNode(1);
        ListNode tempList1 = list1;
        ListNode tempList2 = list2;
        //确定合并链表头
        if (list1 != null && list2 != null){
            if (list1.val>list2.val){
                mergeHead = list2;
                tempList2 = list2.next;
            }else {
                mergeHead = list1;
                tempList1 = list1.next;
            }
            //如果参数中的其中一个链表是null，则返回另一个链表，如果两个都是null，返回null
        }else if (list2 == null){
            return list1;
        }else if (list1 == null){
            return list2;
        }
        ListNode merge = mergeHead;
        //循环穿线
        while (tempList1!=null && tempList2!=null){
            if (tempList1.val < tempList2.val){
                merge.next = tempList1;
                tempList1 = tempList1.next;
            }else {
                merge.next = tempList2;
                tempList2 = tempList2.next;
            }
            merge = merge.next;
        }
        //结尾阶段，有一个表已经遍历完了
        if (tempList1 == null){
            merge.next = tempList2;
        }else if (tempList2 == null){
            merge.next = tempList1;
        }
        return mergeHead;
    }

    /**
     * 题17
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     * root2是否是root1的子结构，如果是返回true，不是返回false
     * 完成
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root2 == null){
            return false;
        }
        if (root1 == null){
            return false;
        }
        //两个二叉树都不为空
        return confirm(root1,root2) || confirm(root1.left,root2) || confirm(root1.right,root2);
    }

    /**
     * confirm方法的作用就是查看root1是否包含root2
     * @param tree1
     * @param tree2
     * @return
     */
    public boolean confirm(TreeNode tree1, TreeNode tree2){
        //如果tree2是空，则说明比较完了，是true
        if (tree2 == null){
            return true;
        }
        //tree2不为空，tree1为空，返回false
        if (tree1 == null){
            return false;
        }
        //都不为空，比较两者的值
        if (tree1.val == tree2.val){
            return confirm(tree1.left,tree2.left) && confirm(tree1.right,tree2.right);
        }else {
            return false;
        }
    }

    /**
     * 题目18
     操作给定的二叉树，将其变换为源二叉树的镜像。
     输入描述:
     二叉树的镜像定义：源二叉树
          8
        /   \
       6    10
      / \  / \
     5  7 9  11
     镜像二叉树
          8
        /  \
       10   6
      / \  / \
     11 9 7  5

     完成，递归思想
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root != null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            Mirror(root.left);
            Mirror(root.right);
        }
    }

    /**
     * 题19
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * 通过，贼尼玛难
     * 四个方向，逐个分析
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> layout = new ArrayList<>();
        //首先数组为空，返回空表
        if (matrix.length == 0){
            return layout;
        }
        //每次输出的范围
        int rowLeft = 0;
        int rowRight = matrix[0].length -1;
        int columnUp = 0;
        int columnDown = matrix.length -1;
        //direction方向，方向顺序为 右下左上 循环，每次循环direction值加1，判断时做取余判断，余数为0 1 2 3 分别为右下左上
        int direction = 0;
        //两个index值表示目前输出的位置
        int rowIndex = 0;
        int columnIndex = 0;
        while (rowLeft <= rowRight && columnUp <= columnDown){
            //方向为右
            if (direction % 4 == 0){
                for (int i=rowLeft;i<rowRight+1;i++){
                    layout.add(matrix[rowIndex][i]);
                    columnIndex++;
                }
                columnIndex--;
                rowIndex++;
                columnUp++;
            } else
            //方向为下
            if (direction % 4 == 1){
                for (int i=columnUp;i<columnDown+1;i++){
                    layout.add(matrix[i][columnIndex]);
                    rowIndex++;
                }
                rowIndex--;
                columnIndex--;
                rowRight--;
            } else
            //方向为左
            if (direction % 4 == 2){
                for (int i=rowRight;i>rowLeft-1;i--){
                    layout.add(matrix[rowIndex][i]);
                    columnIndex--;
                }
                columnIndex++;
                rowIndex--;
                columnDown--;
            } else
            //方向为上
            if (direction % 4 == 3){
                for (int i=columnDown;i>columnUp-1;i--){
                    layout.add(matrix[i][columnIndex]);
                    rowIndex--;
                }
                rowIndex++;
                columnIndex++;
                rowLeft++;
            }
            //更换方向
            direction++;
        }
        return layout;
    }

    /**
     *
     * 题20
     输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     完成
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        ArrayList<Integer> allowList = new ArrayList<>();
        int index = 0;
        for (int num : pushA){
            allowList.add(num);
        }
        boolean isPopOrder = true;
        for (int i=0;i<popA.length;i++){
            boolean contain = false;
            for (int j=index;j<allowList.size();j++){
                if (allowList.get(j) == popA[i]){
                    index = j;
                    contain = true;
                    break;
                }
            }
            if (contain){
                allowList.remove(index);
                if (index != 0){
                    index--;
                }
            }else {
                isPopOrder = false;
            }


        }
        return isPopOrder;
    }

    /**
     * 题21
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     * 完成
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        //在队列中添加根节点
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode tempRoot = queue.element();
            TreeNode left = tempRoot.left;
            TreeNode right = tempRoot.right;
            if (left != null){
                queue.offer(left);
            }
            if (right != null){
                queue.offer(right);
            }
            list.add(tempRoot.val);
            queue.poll();
        }
        return list;
    }

    /**
     * 题22
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {

        if (sequence.length == 1){
            return true;
        }else if (sequence.length == 0){
            return false;
        }
        //index值用于表示在序列中第一个比last节点大的节点的位置
        int index = 0;
        int last = sequence[sequence.length-1];
        boolean result = true;
        for (int i=0;i<sequence.length;i++){
            if (sequence[i]>=last){
                index = i;
                break;
            }
        }
        int[] smaller = new int[index];
        int[] bigger = new int[sequence.length - index-1];

        for (int i=0;i<index;i++){
            smaller[i] = sequence[i];
        }
        for (int i=0;i<bigger.length;i++){
            bigger[i] = sequence[i+index];
            if (bigger[i]<last){
                result = false;
                break;
            }
        }
        if (result){
            //判断切分后数组的大小
            if (smaller.length>0){
                if (bigger.length>0){
                    return VerifySquenceOfBST(smaller) && VerifySquenceOfBST(bigger);
                }else {
                    return VerifySquenceOfBST(smaller);
                }
            }else{
                if (bigger.length>0){
                    return VerifySquenceOfBST(bigger);
                }else {
                    return true;
                }
            }
        }else {
            return result;
        }
    }

    /**题23
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        char[] strChars = str.toCharArray();
        bubble(strChars);
        System.out.println(strChars);
        ArrayList<String> result = recursive("",strChars);
        return result;
    }

    /**
     * 递归程序
     * @param str
     * @param leftChars
     * @return
     */
    public ArrayList<String> recursive(String str, char[] leftChars){
        char[] strChars = str.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        if (leftChars.length == 1){
            char[] resultChars = new char[strChars.length+1];
            for (int i=0;i<strChars.length;i++){
                resultChars[i] = strChars[i];
            }
            resultChars[strChars.length] = leftChars[0];
            String resultString  = new String(resultChars);
            result.add(resultString);
            return result;
        }

        ArrayList<Character> unduplicateList = unDuplicate(leftChars);
        for (Character c : unduplicateList){
            char[] newStrChars = new char[strChars.length+1];
            for (int i=0;i<strChars.length;i++){
                newStrChars[i] = strChars[i];
            }
            newStrChars[strChars.length] = c;
            String newStr = new String(newStrChars);
            char[] newLeftChars = new char[leftChars.length-1];
            boolean deleted = false;
            for (int i=0;i<leftChars.length;i++){
                if (deleted){
                    newLeftChars[i-1] = leftChars[i];
                }else {
                    if (leftChars[i] == c){
                        deleted = true;
                    }else {
                        newLeftChars[i] = leftChars[i];
                    }
                }
            }
            ArrayList<String> nextRound = recursive(newStr,newLeftChars);
            result.addAll(nextRound);
        }
        return result;
    }

    /**
     * 去除重复的字符
     * @param chars
     * @return
     */
    public ArrayList<Character> unDuplicate(char[] chars){
        ArrayList<Character> unDuplicateList = new ArrayList<>();
        for (char c : chars){
            if (!unDuplicateList.contains(c)){
                unDuplicateList.add(c);
            }
        }
        return unDuplicateList;
    }

    /**
     * 字符冒泡排序
     * @param arr
     * @return
     */
    public void bubble(char[] arr){
        //这里i表示的是已经确定位置的个数，j表示本次要比较的数的位置，比较j和j+1位置上的数
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                //判断这两个是否是反序
                if (arr[j]>arr[j+1]){
                    exchange(arr,j,j+1);
                }
            }
        }
    }

    public char[] exchange(char[] arr,int index1 ,int index2){
        char temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
        return arr;
    }

    /**
     * 题24
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     * 已完成
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0){
            return 0;
        }
        if (array.length == 1){
            return array[0];
        }
        int index = 0;
        int divisonArray = upperDivision(array.length);
        int[][] numTime = new int[divisonArray][2];
        boolean hasEqualNum;
        boolean boom = false;
        for (int a : array){
            hasEqualNum = false;
            for (int i = 0;i < index;i++){
                //在二维数组中找到相等的值
                if (a == numTime[i][0]) {
                    numTime[i][1]++;
                    hasEqualNum = true;
                    break;
                }
            }
            //在二维数组中没有找到相等的值
            if (!hasEqualNum){
                if (index >= divisonArray){
                    boom = true;
                    break;
                }
                numTime[index][0] = a;
                numTime[index][1] = 1;
                index++;
            }
        }
        if (boom){
            return 0;
        }else {
            int returnNum = 0;
            for (int i=0;i<divisonArray;i++){
                if (numTime[i][1]*2 > array.length){
                    returnNum = numTime[i][0];
                }
            }
            return returnNum;
        }
    }

    /**
     * 该方法为一个整数除以二向上取整
     * @param a
     * @return
     */
    public int upperDivision(int a){
        if (a%2 == 0){
            return a/2;
        }else {
            return (a+1)/2;
        }
    }

    /**
     * 题25
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     * 已完成
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> minNbrs = new ArrayList<>();
        if (k>input.length){
            return minNbrs;
        }
        bubble(input);
        for (int i=0;i<k;i++){
            minNbrs.add(input[i]);
        }
        return minNbrs;
    }

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public void bubble(int[] arr){
        //这里i表示的是已经确定位置的个数，j表示本次要比较的数的位置，比较j和j+1位置上的数
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                //判断这两个是否是反序
                if (arr[j]>arr[j+1]){
                    exchange(arr,j,j+1);
                }
            }
        }
    }

    public int[] exchange(int[] arr,int index1 ,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
        return arr;
    }

    /**
     *
     * 题26
     HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
     今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
     但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
     给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     * 动态规划法解题，受到评论区指导
     * 解题思路：
     * F(x) 表示在下标为x的位置上，可以取到的最大值
     * F(x) = (F(x-1) + array[i]) 和  array[i] 之间的较大值
     * {6,-3,-2,7,-15,1,2,2} 以此为例
     * F(0) = 6;//初始化
     * F(1) = 6-3 或者 -3 ，结果是3
     * F(2) = 3-2 或者是 -2 ,结果是1
     * …………
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int[][] max = new int[array.length][2];
        int largest = array[0];
        for (int i=0;i<array.length;i++){
            max[i][0] = array[i];
            if (i == 0){
                max[i][1] = max[i][0];
            }else {
                max[i][1] = (max[i-1][1]+max[i][0])> max[i][0] ? (max[i-1][1]+max[i][0]):max[i][0];
            }
            if (max[i][1]>largest){
                largest = max[i][1];
            }
        }
        return largest;
    }

    /**
     * 题27
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
     * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int sum = 0;
        for (int i=1;i<=n;i++){
            char[] numToString = Integer.toString(i).toCharArray();
            for (char c :numToString){
                if (c == '1'){
                    sum ++;
                }
            }
        }
        return sum;
    }

    /**
     * 题28
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int [] numbers) {
        List<String> numString = new ArrayList<>();
        for (int i=0;i<numbers.length;i++){
            numString.add(Integer.toString(numbers[i]));
        }
        stringBubble(numString);
        String result = "";
        for (String s : numString){
            result += s;
        }
        return result;
    }

    /**
     * 该方法是用于String数组的排序，排序规则按该题需求定制
     * @param arr
     */
    public static void stringBubble(List<String> arr){
        //这里i表示的是已经确定位置的个数，j表示本次要比较的数的位置，比较j和j+1位置上的数
        for (int i=0;i<arr.size();i++){
            for (int j=0;j<arr.size()-1-i;j++){
                //判断这两个是否是反序
                String a = arr.get(j)+arr.get(j+1);
                String b = arr.get(j+1)+arr.get(j);
                if (a.compareTo(b) > 0){
                    exchange(arr,j,j+1);
                }
            }
        }
    }

    public static List<String> exchange(List<String> arr,int index1 ,int index2){
        String temp = arr.get(index1);
        arr.set(index1,arr.get(index2));
        arr.set(index2,temp);
        return arr;
    }

    /**
     * 题29
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index == 0){
            return 0;
        }
        if (index == 1){
            return 1;
        }
        int[] uglyNbrs = new int[index];
        int[] compareNbr = new int[3];
        int[] compareIndex = new int[3];
        uglyNbrs[0] = 1;
        compareNbr[0] = 2;
        compareNbr[1] = 3;
        compareNbr[2] = 5;
        int i = 1;
        while (i < index){
            int minIndex = minNum(compareNbr[0],compareNbr[1],compareNbr[2]);
            if (!contain(uglyNbrs,compareNbr[minIndex])){
                uglyNbrs[i] = compareNbr[minIndex];
                i++;
            }
            compareIndex[minIndex]++;
            int factor;
            if (minIndex == 0){
                factor = 2;
            }else if (minIndex == 1){
                factor = 3;
            }else {
                factor = 5;
            }
            compareNbr[minIndex] = factor * uglyNbrs[compareIndex[minIndex]];

        }
        return uglyNbrs[index-1];
    }

    public int minNum(int a, int b, int c){
        if (a > b){
            if (b > c){
                return 2;
            }else {
                return 1;
            }
        }else {
            if (a > c){
                return 2;
            }else {
                return 0;
            }
        }
    }

    public boolean contain(int[] arr, int a) {
        for (int i=arr.length-1;i>-1;i--){
            if (arr[i] == a){
                return true;
            }
        }
        return false;
    }

    /**
     * 题30
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     *
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        char[] strChars = str.toCharArray();
        //LinkedHashMap可以保存插入顺序
        Map<Character,Integer> strMap = new LinkedHashMap<>();
        for (char c : strChars){
            Integer cNum = strMap.get(c);
            if(cNum == null){
                strMap.put(c,1);
            }else {
                strMap.put(c,++cNum);
            }
        }
        char firstOnceChar = '0';
        for (char c : strMap.keySet()){
            if (strMap.get(c) == 1){
                firstOnceChar = c;
                break;
            }
        }
        if (firstOnceChar == '0'){
            return -1;
        }else {
            String result = Character.toString(firstOnceChar);
            return str.indexOf(result);
        }
    }

    /**
     * 题31
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
     * 该题已完成，采用归并排序的思想来解题，时间复杂度是nlogn。直接遍历的时间复杂度是n2，不能通过全部用例。
     * @param array
     * @return
     */
    public int InversePairs(int [] array) {
        //这种做法时间复杂度太高
        /*int sum = 0;
        for (int i=0;i<array.length-1;i++){
            for (int j=i+1;j<array.length;j++){
                if (array[i] > array[j]){
                    sum++;
                }
            }
        }
        return sum % 1000000007;*/
        //这种做法还是超时了
        /*int sum = 0;
        int largest = 0;
        while (array.length>1){
            largest = largest(array);
            sum = sum + array.length - 1 -largest;
            array = arrayDelete(array,largest);
        }
        return sum % 1000000007;*/
        //归并排序法
        return mergeOrder(array).result % 1000000007;
    }

    public class MergeReturn {
        int[] orderedArr;
        int result;

        MergeReturn(int[] orderedArr, int result){
            this.orderedArr = orderedArr;
            this.result = result;
        }
    }
    /**
     * 归并排序递归
     * @param arr
     * @return
     */
    public MergeReturn mergeOrder(int[] arr){
        if (arr.length == 2){
            MergeReturn mergeReturn;
            if (arr[0] < arr[1]){
                mergeReturn = new MergeReturn(arr,0);
            }else {
                int[] newArr = new int[2];
                newArr[0] = arr[1];
                newArr[1] = arr[0];
                mergeReturn = new MergeReturn(newArr,1);
            }
            return mergeReturn;
        }
        if (arr.length == 1){
            return new MergeReturn(arr,0);
        }
        int[] headArr = new int[arr.length/2];
        int[] tileArr = new int[arr.length-arr.length/2];
        for (int i=0;i<headArr.length;i++){
            headArr[i] = arr[i];
        }
        for (int i=0;i<tileArr.length;i++){
            tileArr[i] = arr[arr.length/2 +i];
        }
        MergeReturn headReturn = mergeOrder(headArr);
        MergeReturn tileReturn = mergeOrder(tileArr);
        int sum = headReturn.result + tileReturn.result;
        //下面是归并的方法
        MergeReturn merge = merge(headReturn.orderedArr,tileReturn.orderedArr);
        sum = sum + merge.result;
        if (sum > 1000000007){
            sum = sum % 1000000007;
        }
        MergeReturn result = new MergeReturn(merge.orderedArr,sum);
        return result;
    }

    /**
     * 归并两个数组的算法，并计算逆序的个数
     * @param headArr
     * @param tileArr
     * @return
     */
    public MergeReturn merge(int[] headArr, int[] tileArr){
        //增序排列
        int headIndex = 0;
        int tileIndex = 0;
        int result = 0;
        int[] mergeArr = new int[headArr.length+tileArr.length];
        int mergeIndex = 0;
        while (mergeIndex < mergeArr.length){
            if (headIndex >= headArr.length){
                mergeArr[mergeIndex] = tileArr[tileIndex];
                tileIndex++;
            }else if (tileIndex >= tileArr.length){
                mergeArr[mergeIndex] = headArr[headIndex];
                headIndex++;
            } else {
                if (headArr[headIndex] > tileArr[tileIndex]) {
                    mergeArr[mergeIndex] = tileArr[tileIndex];
                    tileIndex++;
                    result += (headArr.length - headIndex);
                } else {
                    mergeArr[mergeIndex] = headArr[headIndex];
                    headIndex++;
                }
            }
            if (result > 1000000007){
                result = result % 1000000007;
            }
            mergeIndex++;
        }
        MergeReturn mergeReturn = new MergeReturn(mergeArr,result);
        return mergeReturn;
    }

    /**
     * 题32
     * 输入两个链表，找出它们的第一个公共结点。
     * 已完成
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode line1 = pHead1;
        ListNode line2 = pHead2;

        while(line1 != null ){
            while (line2 != null){
                if (line1 == line2){
                    return line1;
                }else {
                    line2 = line2.next;
                }
            }
            line2 = pHead2;
            line1 = line1.next;
        }
        return null;
    }

    /**
     * 题33
     * 统计一个数字在排序数组中出现的次数。
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {
        boolean equals = false;
        int sum = 0;
        for (int a : array){
            if (equals){
                if (a == k){
                    sum++;
                }else {
                    break;
                }
            }else {
                if (a == k){
                    sum++;
                    equals = true;
                }
            }
        }
        return sum;
    }

    /**
     * 题34
     * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }else if (root.left == null){
            int right = TreeDepth(root.right);
            return right+1;
        }else if (root.right == null){
            int left = TreeDepth(root.left);
            return left+1;
        }else {
            int left = TreeDepth(root.left);
            int right = TreeDepth(root.right);
            int max = left > right ? left : right;
            return max + 1;
        }
    }


    /**
     * 题35
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        //root是空
        if (root == null){
            return true;
            //root没有孩子节点的情况
        }
        if (root.left == null && root.right == null){
            return true;
            //root有一个孩子节点的情况
        }else if (root.left == null && root.right != null){
            if (root.right.left == null && root.right.right == null){
                return true;
            }else {
                return false;
            }
        }else if (root.left != null && root.right == null){
            if (root.left.left == null && root.left.right == null){
                return true;
            }else {
                return false;
            }
        }else {
            //root有两个孩子节点的情况
            boolean left = IsBalanced_Solution(root.left);
            boolean right = IsBalanced_Solution(root.right);
            if (left && right){
                int leftDepth = TreeDepth(root.left);
                int rightDepth = TreeDepth(root.right);
                int gap = leftDepth - rightDepth;
                if (gap >= -1 && gap <=1){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    /**
     * 题36
     * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
     * //num1,num2分别为长度为1的数组。传出参数
     * //将num1[0],num2[0]设置为返回结果
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer,Integer> arrayNum = new HashMap<>();
        Integer num = null;
        for (int a : array){
            num = arrayNum.get(a);
            if (num == null){
                arrayNum.put(a,1);
            }else {
                arrayNum.put(a,arrayNum.get(a)+1);
            }
        }
        int num1Full = 0;
        for (Map.Entry<Integer,Integer> entry : arrayNum.entrySet()){
            if (entry.getValue() == 1){
                if (num1Full == 0){
                    num1[0] = entry.getKey();
                }else if (num1Full == 1){
                    num2[0] = entry.getKey();
                }else {
                    break;
                }
                num1Full++;
            }
        }
    }

    /**
     * 题37
     * 题目描述
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     * 输出描述:
     * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        int begin = 1;
        int end = 2;
        int currentSum = 3;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while (begin < (sum+1)/2){
            if (currentSum < sum){
                end++;
                currentSum += end;
            }else if (currentSum > sum){
                currentSum = currentSum - begin;
                begin++;
            }else {
                ArrayList<Integer> answer = new ArrayList<>();
                for (int i=begin;i<=end;i++){
                    answer.add(i);
                }
                result.add(answer);
                currentSum = currentSum - begin;
                begin++;
            }
        }
        return result;
    }

    /**
     * 题38
     * 题目描述
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
     * 输出描述:
     * 对应每个测试案例，输出两个数，小的先输出。
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<ArrayList<Integer>> welldone = new ArrayList<>();
        for (int i=0;i<array.length;i++){
            for (int j=i+1;j<array.length;j++){
                if (array[i]+array[j] == sum){
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(array[i]);
                    arrayList.add(array[j]);
                    welldone.add(arrayList);
                }
                if (array[i]+array[j] >= sum){
                    break;
                }
            }
        }
        if (welldone.size()>0) {
            ArrayList<Integer> min = welldone.get(0);
            for (ArrayList<Integer> list : welldone) {
                if (list.get(0) * list.get(1) < min.get(0) * min.get(1)) {
                    min = list;
                }
            }
            return min;
        }else {
            return new ArrayList<>();
        }
    }

    /**
     * 题39
     * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if (n == 0){
            return str;
        }
        if (str == null || "".equals(str)){
            return "";
        }
        n = n % str.length();
        String front = str.substring(0,n);
        String end = str.substring(n);
        return end+front;
    }

    /**
     * 题40
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if (str == null || "".equals(str)){
            return "";
        }
        String[] strings = str.split(" ");
        if (strings.length == 1 || strings.length == 0){
            return str;
        }
        String result = "";
        for (int i=strings.length-1;i>-1;i--) {
            result = result + strings[i] ;
            if (i != 0){
                result += " ";
            }
        }
        return result;
    }

    /**
     * 题41
     * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        //蛇皮输入集。。。竟然还有这种输入，跟题目意思不符
        if (numbers.length == 0){
            return false;
        }
        //解题思路，两种情况下是不幸运的，1、除0之外有重复；2、除0之外max-min>4。
        Set<Integer> numSet = new HashSet<>();
        boolean result = true;
        //第一类判断
        for (int i : numbers) {
            if (i != 0){
                if (numSet.contains(i)){
                    result = false;
                    break;
                }else {
                    numSet.add(i);
                }
            }
        }
        if (!result){
            return false;
        }else {
            int max = -1;
            int min = 14;
            for (int i : numbers){
                if (i != 0){
                    if (i > max){
                        max = i;
                    }
                    if (i < min){
                        min = i;
                    }
                }
            }
            if (max - min < 5){
                return true;
            }else {
                return false;
            }
        }
    }


    /**
     * 题42
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0){
            return -1;
        }
        List<Integer> childs = new ArrayList<>();
        for (int i=0;i<n;i++){
            childs.add(i);
        }
        int begin = 0;
        int move;
        int deleteChild;
        while (childs.size()>1){
            move = (m-1) % childs.size();
            deleteChild = (begin + move) % childs.size();
            if (deleteChild == childs.size()){
                begin = 0;
            }else {
                begin = deleteChild;
            }
            childs.remove(deleteChild);
        }
        return childs.get(0);
    }

    /**
     * 题43
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        if (n == 1){
            return 1;
        }
        return n+Sum_Solution(n-1);
    }

    /**
     * 题44
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     * @param num1
     * @param num2
     * @return
     * 该题使用到的位运算，异或运算用于得出不进位加法结果，与运算+左移用于得出进位的结果，两者相加得出最后结果
     * 详见这边博客
     * https://blog.csdn.net/derrantcm/article/details/46798763
     */
    public int Add(int num1,int num2) {
        int sum = num1 ^ num2;
        int carry = (num1 & num2) << 1;
        if (carry !=0){
            return Add(sum,carry);
        }else {
            return sum;
        }
    }

    /**
     * 题45
     * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
     * 输入描述:
     * 输入一个字符串,包括数字字母符号,可以为空
     * 输出描述:
     * 如果是合法的数值表达则返回该数字，否则返回0
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        if (str == null || "".equals(str)){
            return 0;
        }
        char[] strChars = str.toCharArray();
        if (strChars.length == 1){
            if (strChars[0] == '+' || strChars[0] == '-'){
                return 0;
            }
        }
        boolean isNumber = true;
        for (int i=0;i<strChars.length;i++){
            if (strChars[i] < '0' || strChars[i] > '9'){
                if (i == 0){
                    if (strChars[0] != '+' && strChars[0] != '-'){
                        isNumber = false;
                        break;
                    }
                }else {
                    isNumber = false;
                    break;
                }
            }
        }
        if (isNumber){
            return Integer.valueOf(str);
        }else {
            return 0;
        }
    }

    /**
     * 题50  前面题号可能漏了。。
     *
     在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        Set<Integer> numberSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (numberSet.contains(numbers[i])){
                duplication[0] = numbers[i];
                return true;
            }else {
                numberSet.add(numbers[i]);
            }
        }
        return false;
    }


    /**
     * 题51
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        int front = 1;
        int end = 1;
        for (int i = 0; i < A.length; i++) {
            if (i != 0){
                front *= A[i-1];
            }
            for (int j = i+1; j < A.length; j++) {
                end *= A[j];
            }
            B[i] = front * end;
            end = 1;
        }
        return B;
    }

    /**
     * 题52
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        //si和pi分别表示str数组的下标和pattern数组的下标
        int si = 0;
        int pi = 0;
        boolean result = true;
        while (result){
            if (pi < pattern.length){
                if (si < str.length){
                    //si没走完，pi也没走完，这种情况最复杂
                    if (pi+1 < pattern.length){
                        if (pattern[pi+1] == '*'){
                            if (str[si] == pattern[pi]){
                                int last = si;
                                for (; last < str.length; last++) {
                                    if (str[last] != pattern[pi]){
                                        break;
                                    }
                                }

                                    si = last;
                                    pi += 2;

                            }else {
                                pi += 2;
                            }
                        }else {
                            if (pattern[pi] == '.'){
                                pi++;
                                si++;
                            }else {
                                if (pattern[pi] == str[si]){
                                    pi++;
                                    si++;
                                }else {
                                    result = false;
                                }
                            }
                        }
                    }else {
                        if (pattern[pi] == '.'){
                            pi++;
                            si++;
                        }else {
                            if (pattern[pi] == str[si]){
                                pi++;
                                si++;
                            }else {
                                result = false;
                            }
                        }
                    }
                }else {
                    //si走完了
                    if (pi+1 < pattern.length){
                        if (pattern[pi+1] == '*') {
                            pi += 2;
                        }
                    }else {
                        result = false;
                    }
                }
            }else {
                //pi走完了
                if (si < str.length){
                    result = false;
                }else {
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //题目2的测试代码
        /*StringBuffer str = new StringBuffer("helloworld ");
        String string = replaceSpace(str);
        System.out.println(string);*/

        //题目3的测试代码
        //ListNode listNode = new ListNode(1);
        //ArrayList<Integer> list = printListFromTailToHead(null);
        Solution solution = new Solution();
        
        TreeNode l1 = new TreeNode(8);
        TreeNode l2 = new TreeNode(8);
        TreeNode l3 = new TreeNode(7);
        TreeNode l4 = new TreeNode(9);
        TreeNode l5 = new TreeNode(2);
        TreeNode l6 = new TreeNode(4);
        TreeNode l7 = new TreeNode(7);
        TreeNode r1 = new TreeNode(8);
        TreeNode r2 = new TreeNode(9);
        TreeNode r3 = new TreeNode(2);
        TreeNode r4 = new TreeNode(4);
        l1.left = l2;
        l1.right = l3;
        l2.left = l4;
        l2.right = l5;
        l5.left = l6;
        l5.right = l7;
        r1.left = r2;
        r1.right = r3;
        r3.left = r4;
        //System.out.println(solution.HasSubtree(l1,r1));
        //int[][] arr = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
        //System.out.println(solution.printMatrix(arr));
        /*int[] push = {1,2,3,4,5};
        int[] pop = {3,1,2,5,4};
        System.out.println(solution.IsPopOrder(push,pop));*/
        int[] arr = {1,2,4,7,11,16};
        char[] chars = {'a','a','a','c'};
        /*ArrayList a = new Solution().FindNumbersWithSum(arr,10);*/
        String a = "abcXYZdef";
        String aFront = a.substring(0,1);
        String aEnd = a.substring(1,3);
        String str = "aaab";
        String pattern = "a*b";
        System.out.println(new Solution().match(str.toCharArray(),pattern.toCharArray()));/*
        System.out.println(aFront);
        System.out.println(aEnd);*/
    }
}
