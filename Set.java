public class Set extends Match{
    private Ball firstBall = null;
    private Ball lastBall = null;
    private boolean Win;
    private Set nextSet = null;
    public Set(){
        
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
}