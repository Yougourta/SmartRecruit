package fr.smartrecruit.viewmodel;
import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.data.Tips;
public class TipsViewModel {
    private List<Tips> tips = new ArrayList();
    private final String DUMMY_CONSEIL = "conseil #";
    private final String DUMMY_TYPE = "Type #";


    public List<Tips> getTips()
    { return tips; }

    public Tips createRandomTips(){
        return new Tips("Random tips", DUMMY_TYPE);
    }
}
