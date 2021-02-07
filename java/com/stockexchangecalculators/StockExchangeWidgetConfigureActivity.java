package com.stockexchangecalculators;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.settings.ConstantValues;
import com.stockexchange.Asset;
import com.stockexchange.Stock;

/**
 * The configuration screen for the {@link StockExchangeWidget StockExchangeWidget} AppWidget.
 */
public class StockExchangeWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "com.stockexchangecalculators.StockExchangeWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    EditText mAppWidgetName;
    EditText mAppWidgetCost;

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = StockExchangeWidgetConfigureActivity.this;

            // When the button is clicked, store the string locally
            String name = mAppWidgetName.getText().toString();
            String purchasePrice = mAppWidgetCost.getText().toString();

            Stock stock = new Stock(name, Integer.parseInt(purchasePrice), 10, ConstantValues.get("STOCK_BROKER"), ConstantValues.get("STOCK_MARKET"));

            saveTitlePref(context, mAppWidgetId, "stock", stock.toString());

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            StockExchangeWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    public StockExchangeWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget

    static void saveTitlePref(Context context, int appWidgetId, String nameText, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId + nameText, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId, String nameText) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(PREF_PREFIX_KEY + appWidgetId + nameText, null);
    }

    static void deleteTitlePref(Context context, int appWidgetId, String nameText) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId + nameText);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        init();
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.stock_exchange_widget_configure);
        mAppWidgetName = (EditText) findViewById(R.id.appwidget_name);
        mAppWidgetCost = (EditText) findViewById(R.id.appwidget_cost);
        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        mAppWidgetName.setText(loadTitlePref(StockExchangeWidgetConfigureActivity.this, mAppWidgetId, "stock"));
    }

    private static void init(){
        ConstantValues.init();
    }
}

