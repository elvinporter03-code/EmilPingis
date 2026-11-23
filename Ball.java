public class Ball extends Set{
    public boolean Serve;
    public int Hits;
    public boolean Win;
    public String LastHitType;
    public String LastZone;
    public boolean Net;
    public boolean Outside;
    public boolean Miss;
    private Ball nextBall = null;

    public Ball(){

    }

    public Ball getNext(){
        return this.nextBall;
    }

    public void setNext(Ball b){
        this.nextBall = b;
    }

    public boolean getWin(){
        return this.Win;
    }

    public void printBall(){
        String output = "";
        if(this.Serve){
            output += "Emil servar, ";
        }else{
            output += "Emil servar inte, ";
        }
        if(this.Win){
            output += "han vann efter: ";
        }else{
            output+= "han förlorade efter: ";
        }
        output += this.Hits;
        output += " Slag. Emils sista slag var:";
        output += this.LastHitType + " " + this.LastZone;
        if(this.Win){
            if(this.Net){
                output += ". Och motståndaren slog den i nätet";
            }
            if(this.Miss){
                output += ". Och den gick inte ens att nudda!";
            }
            if(this.Outside){
                output += ". Och motståndaren slog den utanför";
            }
        }else{
            if(this.Net){
                output += ". Men den gick i nätet";
            }
            if(this.Miss){
                output += ". Men han hann inte fånga returen";
            }
            if(this.Outside){
                output += ". Men den gick utanför";
            }
        }
        System.out.println(output); 
    }

 
}