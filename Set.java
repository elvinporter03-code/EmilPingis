public class Set extends Match{
    private Ball firstBall = null;
    private Ball lastBall = null;
    private int Count;
    private boolean Win;
    private Set nextSet = null;
    public Set(){
        this.Count = 0;
    }

    public Set mergeSets(Set s){
        Set result = s;
        Ball iterator = this.getFirst();
        while(iterator != null){
            s.addBall(iterator);
            iterator=iterator.getNext();
        }
        return result;
    }
    public Set getNext(){
        return this.nextSet;
    }

    public void addBall(Ball ny){
        if(this.firstBall == null){
            this.firstBall = ny;
        }
        if(this.lastBall != null){
            this.lastBall.setNext(ny);
        }
        this.lastBall = ny;
        this.Count++;
    }

    public void setNext(Set next){
        this.nextSet = next;
    }

    public Ball getFirst(){
        return this.firstBall;
    }

    public void setWin(Boolean b){
        Win = b;
    }

    public int getCount(){
        return this.Count;
    }

    public Ball getLast(){
        return this.lastBall;
    }
    
}