package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

import org.greenrobot.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import uk.co.morrisonspls.sysdevns.flickrgallery.model.FlickrPhotoLoader;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by sysdevns on 24/10/2016.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(EventBus.class)
public class MainPresenterTest {

    private static final int VALID_POSITION = 3;

    @Test
    public void testPhotoClicked() {
        EventBus eventBus = mock(EventBus.class);
        PowerMockito.mockStatic(EventBus.class);
        Mockito.when(EventBus.getDefault()).thenReturn(eventBus);
        MainView view = mock(MainView.class);
        MainPresenter presenter = new MainPresenter();

        presenter.attachView(view);
        presenter.jsonFlickrPhotos = new ArrayList<>();
        presenter.photoClicked(VALID_POSITION);

        verify(view).launchDetail(VALID_POSITION);
    }

}
