package csk.com.democouchmarker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myntra.coachmarks.common.DialogDismissButtonPosition;
import com.myntra.coachmarks.common.PopUpPosition;

import java.util.ArrayList;
import java.util.List;

import csk.com.democouchmarker.couchmaker.CouchMaker;
import csk.com.democouchmarker.couchmaker.CouchMakerHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button leftButton;
    Button centerButton;
    Button rightButton;

    CouchMakerHandler couchMakerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftButton = (Button) findViewById(R.id.left_btn);
        centerButton = (Button) findViewById(R.id.center_btn);
        rightButton = (Button) findViewById(R.id.right_btn);

        leftButton.setOnClickListener(this);

    }

    private CouchMaker createCouchMarker(View view, int popupPosition, int dismissTextPosition,
                                         double notchPosition, int strId) {
        CouchMaker couchMaker = new CouchMaker();
        couchMaker.setView(view);
        couchMaker.setPopupPosition(popupPosition);
        couchMaker.setDismissDialogPosition(dismissTextPosition);
        couchMaker.setNotchPosition(notchPosition);
        couchMaker.setText(strId);
        return couchMaker;
    }

    @Override
    public void onClick(View v) {
        List<CouchMaker> couchMakerList = new ArrayList<>();

        couchMakerList.add(createCouchMarker(leftButton, PopUpPosition.RIGHT,
                DialogDismissButtonPosition.RIGHT, .35, R.string.app_name));

        couchMakerList.add(createCouchMarker(centerButton, PopUpPosition.TOP,
                DialogDismissButtonPosition.RIGHT, .20, R.string.app_name));

        couchMakerList.add(createCouchMarker(rightButton, PopUpPosition.TOP,
                DialogDismissButtonPosition.LEFT, .80, R.string.app_name));
        
        couchMakerHandler = new CouchMakerHandler(this, couchMakerList);
        couchMakerHandler.startCouching();
    }
}
