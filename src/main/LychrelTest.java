package main;
import java.util.*;

public class LychrelTest {

    public static void main(String[] args) {
        new LychrelTest().run();
    }

    public void run() {
        int count = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            if (checkLychrel( i )) {
                count++;
                System.out.println( "prob lychrel = " + i );
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println( "count:  " + count );
        System.out.println( "time: " + ( stop - start ) + "ms" );
    }

    public boolean checkLychrel(int i) {
        LargeInt largeInt = new LargeInt( i );
        for (int j = 1; j <= 100; j++) {
            largeInt = largeInt.add( largeInt.reverse() );
            if (largeInt.isPalindrom()) return false;
        }
        return true;
    }

    final class LargeInt {
        List<Integer> digits;

        public LargeInt(List<Integer> list) {
            digits = list;
        }

        public LargeInt(int value) {
            digits = new ArrayList();
            while (value != 0) {
                digits.add( value % 10 );
                value /= 10;
            }
        }

        public LargeInt add(LargeInt addend) {
            Iterator<Integer> addendIterator = addend.digits.iterator();
            List result = new ArrayList( digits.size() );
            int carry = 0;
            for (int digit : digits) {
                int sum = digit + addendIterator.next() + carry;
                result.add( sum % 10 );
                carry = sum / 10;
            }

            if (carry != 0) result.add( carry );
            return new LargeInt( result );
        }

        public LargeInt reverse() {
            List result = new ArrayList( digits );
            Collections.reverse( result );
            return new LargeInt( result );
        }

        public boolean isPalindrom() {
            LinkedList list = new LinkedList( digits );
            while (list.size() > 1) {
                if (list.removeFirst() != list.removeLast()) {
                    return false;
                }
            }
            return true;
        }
    }

}

