package com.example.user.assignment4service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.EventLog;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class MyService extends IntentService {

    Boolean isRunning = true;
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.fahadshahid.serviceapp.action.FOO";
    private static final String ACTION_BAZ = "com.example.fahadshahid.serviceapp.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.fahadshahid.serviceapp.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.fahadshahid.serviceapp.extra.PARAM2";

    public MyService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);

    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(MyService.this, "Service Started", Toast.LENGTH_SHORT).show();

        int limit = intent.getIntExtra("limit", 0);

        for (int i = 0; i <= limit; i++) {
            if (!isRunning) {
                break;
            }

            try {
                Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
                Thread.sleep(1000);
                String string = "Service Running " + i + "%";
                Event event = new EventLog.Event(string);
                EventBus.getDefault().post(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}