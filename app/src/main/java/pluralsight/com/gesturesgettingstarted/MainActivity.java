package pluralsight.com.gesturesgettingstarted;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        View.OnDragListener{


    private static final String TAG = "MainActivity";


    //widgets
    private ImageView mImageView1,mImageView2;


    //vars
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView1 = findViewById(R.id.image1);
        mImageView2 = findViewById(R.id.image2);

        mImageView1.setOnTouchListener(this);
        mImageView2.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);

        setImage();
    }

    private void setImage(){
        Glide.with(this)
                .load(R.drawable.france_mtn)
                .into(mImageView1);

        Glide.with(this)
                .load(R.drawable.oregon_beach)
                .into(mImageView2);
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.image1) {
            mGestureDetector.onTouchEvent(event);
            return true;
        }

        return false;
        }


/*
GestureDetector
 */

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress: called");

        View.DragShadowBuilder builder = new View.DragShadowBuilder(mImageView1);
        mImageView1.startDragAndDrop(null, builder, null, 0);

        builder.getView().setOnDragListener(this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown: called");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: called");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: called");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: called");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: called");
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch(event.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(TAG, "onDrag: drag started.");

                return true;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(TAG, "onDrag: drag entered.");
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                Log.d(TAG, "onDrag: current point: ( " + event.getX() + " , " + event.getY() + " )"  );

                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "onDrag: exited.");
                return true;

            case DragEvent.ACTION_DROP:

                Log.d(TAG, "onDrag: dropped.");

                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(TAG, "onDrag: ended.");


                return true;

            // An unknown action type was received.
            default:
                Log.e(TAG,"Unknown action type received by OnStartDragListener.");
                break;

        }
        return false;
    }
}

