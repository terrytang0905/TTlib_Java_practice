package com.ttsoftware.utilities.algorithm.list;

/**
 * Created with IntelliJ IDEA.
 * User: tangz
 * Date: 6/17/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkListApp {
    public static void main(String[] args){
        LinkList thisList =new LinkList();

        thisList.insertFirst(22,2.99);
        thisList.insertFirst(44,4.99);
        thisList.insertFirst(66,6.99);
        thisList.insertFirst(88,8.99);

        thisList.displayList();

        while (!thisList.isEmpty()){
            Link alink=thisList.deleteFirst();
            System.out.print("Deleted");
            alink.displayLink();
            System.out.print("");
        }
        thisList.displayList();
    }
}
