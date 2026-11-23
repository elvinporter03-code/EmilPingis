public class tests{
    public static void main(String Args[]){
        Ball b1 = new Ball(true,6,true,"F","R",false,true,false);
        Ball b2 = new Ball(true,6,true,"B","R",false,true,false);
        Set s1 = new Set();
        Match m1 = new Match();

        m1.addSet(s1);
        s1.addBall(b1);
        s1.addBall(b2);
        Set s = m1.getFirst();
        while(s != null){
            Ball b = s.getFirst();
            while(b != null){
                b.printBall();
                b = b.getNext();
            }
            s = s.getNext();
        }
    }
}