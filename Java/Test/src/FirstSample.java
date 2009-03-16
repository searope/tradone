import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.NounSynset;
import java.util.List;
import com.sun.xml.internal.ws.util.StringUtils;

public class FirstSample
{
    public static void main(String[] args)
    {
//        WordNetDatabase db = WordNetDatabase.getFileInstance();
//        Synset[] synsets = db.getSynsets("entity", SynsetType.NOUN);
//        for (int i=0; i<synsets.length; i++){
//            System.out.println(synsets[i].toString());
//            if (synsets[i] instanceof NounSynset){
//                NounSynset[] nounSynsets = ((NounSynset) synsets[i]).getHypernyms();
//                for (int j=0; j<nounSynsets.length; j++){
//                    System.out.println("\t" + nounSynsets[j].toString());
//                }
//            }
//        }
        List<List<NounSynset>> list = Hypernym.getHypernyms("green");
        for (int i=0;i<list.size();i++){
            List<NounSynset> nounSynsetList = list.get(i);
            String prefix = "";
            for (int j=nounSynsetList.size()-1;j>=0;j--){
                System.out.println(prefix + nounSynsetList.get(j).toString());
                prefix += "\t";
            }
        }
    }
}