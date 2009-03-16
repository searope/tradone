import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.impl.file.synset.NounReferenceSynset;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Seriozshka
 * Date: Mar 15, 2009
 * Time: 8:03:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hypernym {
    private static WordNetDatabase db = WordNetDatabase.getFileInstance();
    public static List<List<NounSynset>> getHypernyms(String word){
        List<List<NounSynset>> ret = new ArrayList<List<NounSynset>>();
        // the queue holds intermediary results with arrays of hypernims chains calculated so far
        LinkedList<List<NounSynset>> queue = new LinkedList<List<NounSynset>>();
        Synset[] synsets = db.getSynsets(word, SynsetType.NOUN);
        for (int i=0; i<synsets.length; i++){
            if (synsets[i] instanceof NounReferenceSynset){
                List<NounSynset> hypernyms = new ArrayList<NounSynset>();
                hypernyms.add((NounSynset) synsets[i]);
                queue.add(hypernyms);
            }
        }
        while (queue.size() > 0) {
            List<NounSynset> referenceSynsetList = queue.pollFirst();
            NounSynset[] hypernyms = referenceSynsetList.get(referenceSynsetList.size() - 1).getHypernyms();
            if (hypernyms.length ==0) {// it's the top
                ret.add(referenceSynsetList);
                continue;
            }
            for (int j=0;j<hypernyms.length;j++){
                List<NounSynset> temp = new ArrayList<NounSynset>(referenceSynsetList);
                temp.add(hypernyms[j]);
                queue.add(temp);
            }
        }
        return ret;
    }
}
