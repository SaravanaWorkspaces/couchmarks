package csk.com.democouchmarker.couchmaker;

import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.myntra.coachmarks.PopUpCoachMark;
import com.myntra.coachmarks.builder.CoachMarkBuilder;
import com.myntra.coachmarks.builder.ImageLayoutInformation;
import com.myntra.coachmarks.builder.InfoForViewToMask;
import com.myntra.coachmarks.common.CoachMarkTextGravity;

import java.util.ArrayList;
import java.util.List;

import csk.com.democouchmarker.R;

/**
 * Created by Saravanakumar.Chinnaraj on 15/05/18.
 */

public class CouchMakerHandler implements PopUpCoachMark.OnCouchMarkerDismissListener {

    private FragmentActivity activity;
    private List<CouchMaker> couchMakersList = new ArrayList<>();
    private int mScreenHeight;
    private int mScreenWidth;

    private int itemIndex = -1;

    public CouchMakerHandler(FragmentActivity activity, List<CouchMaker> couchMakers) {
        this.activity = activity;
        this.couchMakersList = couchMakers;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
    }

    private Point getAnchorTop(View view) {
        return new Point(view.getLeft(), view.getTop());
    }

    private Point getAnchorBottom(View view) {
        return new Point(view.getRight(), view.getBottom());
    }

    private CoachMarkBuilder createCouchMarker(CouchMaker couchMaker) {
        return CoachMarkBuilder.create(getAnchorTop(couchMaker.getView()),
                getAnchorBottom(couchMaker.getView()), couchMaker.getText())
                .setImageLayoutInformation(createCirclePointer())
                .setUserDesiredPopUpPositionWithRespectToView(couchMaker.getPopupPosition())
                .setPopUpCoachMarkDismissButtonPosition(couchMaker.getDismissDialogPosition())
                .setCoachMarkTextGravity(CoachMarkTextGravity.CENTER)
                .setNotchPosition(couchMaker.getNotchPosition())
                .setInfoForViewToMaskList(prepareMaskList())
                .setImageDrawableRes(R.drawable.similar_item_drawable)
                .setAnimationOnImage(R.anim.coach_mark_smaple_animation)
                .build();
    }

    public void startCouching() {
        CouchMaker couchMaker = getNext();
        CoachMarkBuilder coachMarkBuilder = createCouchMarker(couchMaker);

        PopUpCoachMark popUpCoachMark = PopUpCoachMark.newInstance(coachMarkBuilder);
        popUpCoachMark.setCouchMarkerDismissListener(this);
        popUpCoachMark.show(activity.getSupportFragmentManager(), "Test");
    }

    private CouchMaker getNext() {
        return couchMakersList.get(++itemIndex);
    }

    private boolean hasMore() {
        return couchMakersList == null ? false : itemIndex < couchMakersList.size() - 1;
    }

    // List of InfoForViewToMask to be overlay to mask android views.
    private ArrayList<InfoForViewToMask> prepareMaskList() {
        ArrayList<InfoForViewToMask> infoForViewToMaskArrayList = new ArrayList<>(1);
        infoForViewToMaskArrayList.add(createInfoForViewToMask());
        return infoForViewToMaskArrayList;
    }

    // Overlay to mask
    private InfoForViewToMask createInfoForViewToMask() {
        return InfoForViewToMask
                .create(new Point(0, 0), mScreenHeight, mScreenWidth)
                .build();
    }

    // Animated circle to point the view.
    private ImageLayoutInformation createCirclePointer() {
        return ImageLayoutInformation
                .create(R.dimen.image_width, R.dimen.image_height)
                .build();
    }

    @Override
    public void onMarkerDismiss() {
        if (hasMore()) {
            startCouching();
        }
    }
}
