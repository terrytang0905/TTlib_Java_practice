package com.newroad.util.practice.datastruct;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * Created by zhenjie.tang on 2019-06-09.
 *
 * @Description: 前缀树的结点
 */
public class PrefixTreeNode {


    public char label;  // 结点的名称，在前缀树里是单个字母
    public HashMap<Character, PrefixTreeNode> sons = null; // 使用哈希映射存放子结点。哈希便于确认是否已经添加过某个字母对应的结点。
    public String prefix = null;   // 从树的根到当前结点这条通路上，全部字母所组成的前缀。例如通路 b->o->y，对于字母 o 结点而言，前缀是 b；对于字母 y 结点而言，前缀是 bo
    public String explanation = null;  // 词条的解释

    // 初始化结点
    public PrefixTreeNode(char l, String pre, String exp) {
        label = l;
        prefix = pre;
        explanation = exp;
        sons = new HashMap<>();

    }

    // 使用栈来实现深度优先搜索
    public void dfsByStack(PrefixTreeNode root) {

        Stack<PrefixTreeNode> stack = new Stack<PrefixTreeNode>();
        // 创建堆栈对象，其中每个元素都是 TreeNode 类型
        stack.push(root);		// 初始化的时候，压入根结点

        while (!stack.isEmpty()) {	// 只要栈里还有结点，就继续下去

            PrefixTreeNode node = stack.pop();	// 弹出栈顶的结点

            if (node.sons.size() == 0) {
                // 已经到达叶子结点了，输出
                System.out.println(node.prefix + node.label);
            } else {
                // 非叶子结点，遍历它的每个子结点
                Iterator<Map.Entry<Character, PrefixTreeNode>> iter
                        = node.sons.entrySet().iterator();

                // 注意，这里使用了一个临时的栈 stackTemp
                // 这样做是为了保持遍历的顺序，和递归遍历的顺序是一致的
                // 如果不要求一致，可以直接压入 stack
                Stack<PrefixTreeNode> stackTemp = new Stack<PrefixTreeNode>();
                while (iter.hasNext()) {
                    stackTemp.push(iter.next().getValue());
                }
                while (!stackTemp.isEmpty()) {
                    stack.push(stackTemp.pop());
                }
            }
        }

    }


}
