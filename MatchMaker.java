import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class MatchMaker{

    public static Ball addDataToBall(Ball b, String Data, int slot){
        switch(slot){
            case 1 -> b.Serve = Data.equals("1") ? true : false; 
            case 2 -> b.Hits = Integer.parseInt(Data);
            case 3 -> b.Win = Data.equals("1") ? true : false; 
            case 4 -> b.LastHitType = Data;
            case 5 -> b.LastZone = Data;
            case 6 -> b.Net = Data.equals("1") ? true : false; 
            case 7 -> b.Outside = Data.equals("1") ? true : false; 
            case 8 -> b.Miss = Data.equals("1") ? true : false; 
        }   
        return b;
    }
    public static void main(String Args[]){
        Match match = new Match();
        Set set = new Set();
        int Emil = 0;
        int Opponent = 0;
        Boolean SetOver = false;
        try{
            List<String[]> rows = CsvRowReader.readRowsFromFolder(
            Paths.get("C:/Users/elvin/EmilPingis/Matcher/8.11.2026"), ",", true);

            for (String[] row : rows) {           // en datapunkt i taget
            Ball Current = new Ball();
            int i = 1;
                for (String col : row) {          // en kolumn i taget
                    Current = addDataToBall(Current, col, i);
                    if(i == 3){
                        if(col.equals("1")){
                            Emil++;
                        } else{
                            Opponent++;
                        }
                        if(Emil > 11 && Emil - Opponent > 1){
                            set.setWin(true);
                            SetOver = true;
                        } else if(Opponent > 11 && Opponent - Emil > 1){
                            set.setWin(false);
                            SetOver = true;
                        }
                    }
                    i++;
                }
                set.addBall(Current);
                if(SetOver){
                    match.addSet(set);
                    set = new Set();
                    Emil = 0;
                    Opponent = 0;
                    SetOver = false;
                }
                Current.printBall();
                System.out.println(Emil + " - " + Opponent);
                System.out.println();
            }
            Analyzer.AnalyzeData(match);
        } catch(Exception e){
            System.out.println(e);
        }

    }

}