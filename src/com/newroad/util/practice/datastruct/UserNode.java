package com.newroad.util.practice.datastruct;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by zhenjie.tang on 2019-06-09.
 */
public class UserNode {


    public int user_id;        // 结点的名称，这里使用用户 id
    public HashSet<Integer> friends = null;
    // 使用哈希映射存放相连的朋友结点。哈希便于确认和某个用户是否相连。
    public int degree;        // 用于存放和给定的用户结点，是几度好友

    // 初始化结点
    public UserNode(int id) {
        user_id = id;
        friends = new HashSet<Integer>();
        degree = 0;
    }

    public static int user_num = 100;
    public static int relation_num = 6;

    public UserNode[] buildUserRelation() {
        Random random = new Random(100);
        UserNode[] user_nodes = new UserNode[user_num];

// 生成所有表示用户的结点
        for (int i = 0; i < user_num; i++) {
            user_nodes[i] = new UserNode(i);
        }

// 生成所有表示好友关系的边
        for (int i = 0; i < relation_num; i++) {
            int friend_a_id = random.nextInt(user_num);
            int friend_b_id = random.nextInt(user_num);
            if (friend_a_id == friend_b_id) continue;
            // 自己不能是自己的好友。如果生成的两个好友 id 相同，跳过
            UserNode friend_a = user_nodes[friend_a_id];
            UserNode friend_b = user_nodes[friend_b_id];

            friend_a.friends.add(friend_b_id);
            friend_b.friends.add(friend_a_id);
        }

        return user_nodes;
    }

    /**
     * @param user_nodes- 用户的结点；user_id- 给定的用户 ID，我们要为这个用户查找好友
     * @return void
     * @Description: 通过广度优先搜索，查找好友
     */
    public static void bfs(UserNode[] user_nodes, int user_id) {

        if (user_id > user_nodes.length) return;    // 防止数组越界的异常

        Queue<Integer> queue = new LinkedList<Integer>();    // 用于广度优先搜索的队列

        queue.offer(user_id);        // 放入初始结点
        HashSet<Integer> visited = new HashSet<Integer>();    // 存放已经被访问过的结点，防止回路
        visited.add(user_id);

        while (!queue.isEmpty()) {
            int current_user_id = queue.poll();        // 拿出队列头部的第一个结点
            if (user_nodes[current_user_id] == null) continue;

            // 遍历刚刚拿出的这个结点的所有直接连接结点，并加入队列尾部
            for (int friend_id : user_nodes[current_user_id].friends) {
                if (user_nodes[friend_id] == null) continue;
                if (visited.contains(friend_id)) continue;
                queue.offer(friend_id);
                visited.add(friend_id);    // 记录已经访问过的结点
                user_nodes[friend_id].degree = user_nodes[current_user_id].degree + 1;        // 好友度数是当前结点的好友度数再加 1
                System.out.println(String.format("\t%d 度好友：%d", user_nodes[friend_id].degree, friend_id));
            }
        }

    }

}
