import java.util.function.Predicate;

public abstract class Analyzer{
    public static void AnalyzeData(Match m){
        Set Wins = getWins(m);
        Set Losses = getLosses(m);
        double wc = Wins.getCount();
        double t = m.getCount();
        double winp = wc/t;
        System.out.println("Ball Winrate: " + winp);

        Set ForehandWins = getForeHands(Wins);
        double fwc = ForehandWins.getCount();
        double fwp = fwc/wc;
        System.out.println("Amount of forehands per win: " + fwp);

        Set BackhandWins = getBackHands(Wins);
        double bwc = BackhandWins.getCount();
        double bwp = bwc/wc;
        System.out.println("Amount of backhands per win: " + bwp);

        Set ServeAces = getServeAces(Wins);
        double swc = ServeAces.getCount();
        double swp = swc/wc;
        System.out.println("Amount of serveaces per win: " + swp);
        
        Set EmilServar = getServes(m);
        Set OppServe = getOpponentServes(m);
        Set EmilServeVinster = getWins(EmilServar);
        double ESW = EmilServeVinster.getCount();
        double ES = EmilServar.getCount();
        double ESWP = ESW/ES;
        System.out.println("Andel av vunna bollar som kommer efter serve: " + ESWP);
        System.out.println("Andel av vunna bollar som kommer efter motståndarserve: " + (1-ESWP));
        //System.out.println("Vinster: ");
        //printPhases(Wins); behöver lite fixar innan den används igen
        System.out.println("Andel bollar som avgörs på Serve/ServeAttack: ");
        Set serveattacks = getPhase(EmilServar, 1, 6).mergeSets(getPhase(OppServe,1,6));
        double sac = serveattacks.getCount();
        double serveshare = sac/t;
        System.out.println(serveshare);
        printServeAttacks(EmilServar, OppServe, Wins);
        System.out.println("Winrate innan långspel");
        winRate(serveattacks);

    }
    public static void printServeAttacks(Set e, Set o, Set w){

        System.out.println("Serve winrate innan långspel: ");
        winRate(getPhase(e, 1, 6));
        System.out.println("Motståndare serve winrate innan långspel: ");
        winRate(getPhase(o, 1, 6));



        System.out.println("Andel poäng från Serveäss");
        winShare(getPhase(e, 1, 2), w);
        System.out.println("Andel poäng från Serveförsvar");
        winShare(getPhase(o, 1, 2), w);
        System.out.println("Andel poäng från Serveattacken");
        winShare(getPhase(e, 3, 6), w);
        System.out.println("Andel poäng från Serveattackförsvar");
        winShare(getPhase(o, 3, 6), w);
        System.out.println("Andel poäng från långa spel");
        winShare(getPhase(e.mergeSets(o), 7, 999), w);

        System.out.println("Forehands i servespel");
        winRate(getForeHands(getPhase(e,3,6)));
        System.out.println("backhands i servespel");
        winRate(getBackHands(getPhase(e,3,6)));

        System.out.println("Med bollen på höger");
        winRate(getRight(getPhase(e,3,6)));
        System.out.println("Med bollen på vänster");
        winRate(getLeft(getPhase(e,3,6)));

        System.out.println("Forehands till Höger i servespel");
        winRate(getRight(getForeHands(getPhase(e,3,6))));
        System.out.println("Backhands till vänster i servespel");
        winRate(getLeft(getBackHands(getPhase(e,3,6))));

        System.out.println("hur många av dessa som går ut till forehand");
        getShare(getForeHands(getPhase(e,3,6)), getPhase(e,3,6));
        System.out.println("hur många av dessa som går ut till backhand");
        getShare(getBackHands(getPhase(e,3,6)), getPhase(e,3,6));

    }

    public static void printPhases(Set s){
        double wc = s.getCount();
        Set p1w = getPhase(s, 1, 1);
        Set p2w = getPhase(s, 2, 2);
        Set p3w = getPhase(s, 3, 5);
        Set p4w = getPhase(s, 6, 999);

        Set p1w2 = getExtraHits(p2w);
        p1w = p1w.mergeSets(p1w2);
        double p1wc = p1w.getCount();
        double p1wr = p1wc/wc;
        System.out.println("Andel bollar i fas1: " + p1wr);

        p2w = getOpponentServes(p2w);
        double p2wc = p2w.getCount();
        double p2wr = p2wc/wc;
        System.out.println("Andel bollar i fas2: " + p2wr);
        
        double p3wc = p3w.getCount();
        double p3wr = p3wc/wc;
        System.out.println("Andel bollar i fas2: " + p3wr);
            
        double p4wc = p4w.getCount();
        double p4wr = p4wc/wc;
        System.out.println("Andel bollar i fas2: " + p4wr);
    }

    public static void winRate(Set s){
        double sTotal = s.getCount();
        double sWins = getWins(s).getCount();
        double result = sWins/sTotal;
        System.out.println(result);
    }
    public static void winShare(Set s, Set w){
        double wc = w.getCount();
        double sc = getWins(s).getCount();
        double result = sc/wc;
        System.out.println(result);
    }
    public static void getShare(Set a, Set b){
        double ac = a.getCount();
        double bc = b.getCount();
        double result = ac/bc;
        System.out.println(result);
    }

    public static Set ForEachBall(Set s, Predicate<Ball> p){
        Set result = new Set();
        Ball ballIterator = s.getFirst();
            while(ballIterator != null){
                if(p.test(ballIterator)){
                    result.addBall(ballIterator.copy());
                }
                ballIterator = ballIterator.getNext();
            }
            return result;
    }
    
    public static Set ForEachSet(Match m, Predicate<Ball> p){
        Set result = new Set();
        Set setIterator = m.getFirst();
        while(setIterator != null){
            Ball ballIterator = setIterator.getFirst();
            while(ballIterator != null){
                if(p.test(ballIterator)){
                    result.addBall(ballIterator.copy());
                }
                ballIterator = ballIterator.getNext();
            }
            setIterator = setIterator.getNext();
        }
        return result;
    }

    public static Set getWins(Match m){
        return ForEachSet(m, x -> x.getWin());
    }
    public static Set getWins(Set m){
        return ForEachBall(m, x -> x.getWin());
    }

    public static Set getLosses(Match m){
        return ForEachSet(m, x -> !(x.getWin()));
    }
    public static Set getLosses(Set m){
        return ForEachBall(m, x -> !(x.getWin()));
    }

    public static void printSet(Set s){
        Ball b = s.getFirst();
        while(b != null){
            System.out.println(b.getWin());
            b = b.getNext();
        }
    }

    public static Set getForeHands(Set s){
        return ForEachBall(s, x -> x.getHitType().equals("F"));
    }
        
    public static Set getBackHands(Set s){
        return ForEachBall(s, x -> x.getHitType().equals("B"));
    }

    public static Set getServeAces(Set s){
        return ForEachBall(s, x -> x.getHitType().equals("S"));
    }

    public static Set getServes(Match m){
        return ForEachSet(m, x -> x.Serve);
    }

    public static Set getOpponentServes(Match m){
        return ForEachSet(m, x -> !(x.Serve));
    }
    
    public static Set getServes(Set m){
        return ForEachBall(m, x -> x.Serve);
    }

    public static Set getOpponentServes(Set m){
        return ForEachBall(m, x -> !(x.Serve));
    }


    public static Set getPhase(Set s, int lb, int ub){
        return ForEachBall(s, x -> (x.Hits >= lb) && (x.Hits <= ub));
    }

    public static Set getExtraHits(Set s){
        return ForEachBall(s, x -> !(x.Miss));
    }

    public static Set getLeft(Set s){
        return ForEachBall(s, x -> x.LastZone.equals("L"));
    }
    public static Set getRight(Set s){
        return ForEachBall(s, x -> x.LastZone.equals("R"));
    }

}