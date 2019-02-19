package fr.smartrecruit.viewmodel;
import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.data.Tips;
public class TipsViewModel {
    private List<Tips> tips = new ArrayList();
    private final String DUMMY_CONSEIL = "conseil #";
    private final String DUMMY_TYPE = "Type #";

    public List<Tips> getTips()
    {
        Tips tips1 = new Tips(DUMMY_CONSEIL, DUMMY_TYPE);
        Tips tips2 = new Tips(DUMMY_CONSEIL, DUMMY_TYPE);
        Tips tips3 = new Tips(DUMMY_CONSEIL, DUMMY_TYPE);
        tips.add(tips1);
        tips.add(tips2);
        tips.add(tips3);

        return tips;

    }

}
