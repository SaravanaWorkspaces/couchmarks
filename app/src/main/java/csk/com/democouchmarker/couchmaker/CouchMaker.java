package csk.com.democouchmarker.couchmaker;

import android.view.View;

/**
 * Created by Saravanakumar.Chinnaraj on 15/05/18.
 */

public class CouchMaker {

    private View view;

    private int text;

    private int popupPosition;

    private int dismissDialogPosition;

    private double notchPosition;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public int getPopupPosition() {
        return popupPosition;
    }

    public void setPopupPosition(int popupPosition) {
        this.popupPosition = popupPosition;
    }

    public int getDismissDialogPosition() {
        return dismissDialogPosition;
    }

    public void setDismissDialogPosition(int dismissDialogPosition) {
        this.dismissDialogPosition = dismissDialogPosition;
    }

    public double getNotchPosition() {
        return notchPosition;
    }

    public void setNotchPosition(double notchPosition) {
        this.notchPosition = notchPosition;
    }
}
