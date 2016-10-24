package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

import android.content.Context;

/**
 * Created by sysdevns on 21/10/2016.
 */

public interface MainPresenterView {

    void photoClicked(int position);
    void loadPhotos();
    void getMorePhotos();


}
