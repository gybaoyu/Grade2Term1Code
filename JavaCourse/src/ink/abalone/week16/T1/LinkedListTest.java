package ink.abalone.week16.T1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args) {
        var a = new LinkedList<String>();
        a.add("Amy");
        a.add("Bob1");
        a.add("Carl1");
        var b = new LinkedList<String>();
        b.add("Bob");
        b.add("Carl");
        b.add("Dan");
        b.add("Frances");
        ListIterator<String> aIt = a.listIterator();
        Iterator<String> bIt = b.iterator();
        while(bIt.hasNext()){
            if (aIt.hasNext())aIt.next();
            aIt.add(bIt.next());
        }
        System.out.println(a);
        bIt = b.iterator();
        while(bIt.hasNext()){
            bIt.next();
            if (bIt.hasNext()) {
                bIt.next();
                bIt.remove();
            }
        }
        System.out.println(b);
        a.removeAll(b);
        System.out.println(a);
    }
}
