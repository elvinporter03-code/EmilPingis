public class Match{
    private Set firstSet = null;
    private Set lastSet = null;
    private boolean Win;
    private int Count;
    public Match(){
        this.Count = 0;
    }

    public void addSet(Set ny){
        if(this.firstSet == null){
            this.firstSet = ny;
        }
        if(this.lastSet != null){
            this.lastSet.setNext(ny);
        }
        this.lastSet = ny;
        this.Count += ny.getCount();
    }

    public Set getFirst(){
        return this.firstSet;
    }
    public int getCount(){
        return this.Count;
    }
}