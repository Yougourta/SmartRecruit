package fr.smartrecruit.controller.candidat;

import android.content.Context;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.DataConstants;

public class OffersDetailController {
    private static OffersDetailController offersDetailController;

    private OffersDetailController() {}

    public static OffersDetailController getOffersDetailController(){
        if (offersDetailController == null)
            offersDetailController = new OffersDetailController();
        return offersDetailController;
    }

    public void apply(Context context, String idOffer){
        new SmarRecruitApi(context).updateStatus(idOffer, DataConstants.APP_RDV_ATT);
    }
}
