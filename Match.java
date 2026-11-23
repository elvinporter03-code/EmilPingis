public class Match{
    private Set firstSet = null;
    private Set lastSet = null;
    private boolean Win;
    public Match(){

    }

    public void addSet(Set ny){
        if(this.firstSet == null){
            this.firstSet = ny;
        }
        if(this.lastSet != null){
            this.lastSet.setNext(ny);
        }
        this.lastSet = ny;
    }

    public Set getFirst(){
        return this.firstSet;
    }
}