package info.mycommander.mycommander.View;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;

import info.mycommander.mycommander.R;

/**
 * Implementation of App Widget functionality.
 */
public class CmdWidget extends AppWidgetProvider {

    private static final String WIDGET_CALL = "info.mycommander.mycommander.WIDGET_CALL";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.cmd_widget);

            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, getVoicePendingIntent(context));

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }


    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cmd_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Recived","Intent");
        super.onReceive(context, intent);
        if (WIDGET_CALL.equals(intent.getAction())) {
            Intent i = new Intent(context,VoiceActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    private PendingIntent getVoicePendingIntent(Context ctx){
        Intent intent = new Intent(ctx,CmdWidget.class);
        intent.setAction(WIDGET_CALL);
        return PendingIntent.getBroadcast(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}


