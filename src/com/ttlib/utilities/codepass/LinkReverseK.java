package com.ttlib.utilities.codepass;

public class LinkReverseK {

    public static void main(String[] args) {
        ListNode head=new ListNode();
        ListNode second=new ListNode();
        ListNode third=new ListNode();
        ListNode forth=new ListNode();
        head.next=second;
        second.next=third;
        third.next=forth;

        head.data=1;
        second.data=2;
        third.data=3;
        forth.data=4;

        LinkReverseK test=new LinkReverseK();
        ListNode resultListNode=test.findKToTail(head, 3);
        System.out.println(resultListNode.data);
    }
    public ListNode findKToTail(ListNode head,int k){
        if(head==null||k==0){ return null;
        }
        ListNode resultNode=null;
        ListNode headListNode=head;
        for(int i=0;i<k;++i){
            if(headListNode.next!=null){
                headListNode=headListNode.next;
            }else{
                return null;
            }
        }
        resultNode=head; while(headListNode!=null){
            resultNode=resultNode.next;
            headListNode=headListNode.next;
        }
        return resultNode; }
}
