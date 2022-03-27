package com.example.icp9;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;




public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 11;
    final int TOPPINGS_PRICE = 2;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        RadioButton extra_cheese = (RadioButton) findViewById(R.id.extra_cheese_checked);
        boolean hasExtraCheese = extra_cheese.isChecked();

        RadioButton no_cheese = (RadioButton) findViewById(R.id.no_cheese_checked);
        boolean hasNoCheese = no_cheese.isChecked();

        CheckBox sausage = (CheckBox) findViewById(R.id.sausage_checked);
        boolean hasSausage = sausage.isChecked();

        CheckBox ham = (CheckBox) findViewById(R.id.ham_checked);
        boolean hasHam = ham.isChecked();

        CheckBox pineapple = (CheckBox) findViewById(R.id.pineapple_checked);
        boolean hasPineapple = pineapple.isChecked();

        CheckBox mushroom = (CheckBox) findViewById(R.id.mushroom_checked);
        boolean hasMushroom = mushroom.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasPepperoni, hasExtraCheese, hasSausage, hasHam, hasPineapple, hasMushroom);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasPepperoni, hasExtraCheese, hasNoCheese, hasSausage, hasHam, hasPineapple, hasMushroom, totalPrice);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("message", orderSummaryMessage);
        startActivity(intent);
    }
//String name, String output
    public void sendEmail(View view) {
        // Write the relevant code for triggering email
        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        if (userInputName.isEmpty()) {
            Log.i("MainActivity", "Please enter a name for this order");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.no_order_name);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }

        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        RadioButton extra_cheese = (RadioButton) findViewById(R.id.extra_cheese_checked);
        boolean hasExtraCheese = extra_cheese.isChecked();

        RadioButton no_cheese = (RadioButton) findViewById(R.id.no_cheese_checked);
        boolean hasNoCheese = no_cheese.isChecked();

        CheckBox sausage = (CheckBox) findViewById(R.id.sausage_checked);
        boolean hasSausage = sausage.isChecked();

        CheckBox ham = (CheckBox) findViewById(R.id.ham_checked);
        boolean hasHam = ham.isChecked();

        CheckBox pineapple = (CheckBox) findViewById(R.id.pineapple_checked);
        boolean hasPineapple = pineapple.isChecked();

        CheckBox mushroom = (CheckBox) findViewById(R.id.mushroom_checked);
        boolean hasMushroom = mushroom.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasPepperoni, hasExtraCheese, hasSausage, hasHam, hasPineapple, hasMushroom);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasPepperoni, hasExtraCheese, hasNoCheese, hasSausage, hasHam, hasPineapple, hasMushroom, totalPrice);
        // Hint to accomplish the task


        EditText userEmailView = (EditText) findViewById(R.id.email_input);
        String userEmail = userEmailView.getText().toString();

        if (userEmail.isEmpty()) {
            Log.i("MainActivity", "Please enter an email address");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.no_email);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }

        Spinner emailProviderView = (Spinner) findViewById(R.id.email_provider);
        String emailProvider = emailProviderView.getSelectedItem().toString();

        String address = userEmail+emailProvider;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:" + address));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pizza Order");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);
        startActivity(intent);
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean p, boolean e, boolean n, boolean s, boolean h, boolean pine, boolean m, float price) {

        String pep = p ? (getString(R.string.order_summary_pepperoni) + "\n") : "";
        String che = e ? (getString(R.string.order_summary_extra_cheese) + "\n") : "";
        String no  = n ? (getString(R.string.order_summary_no_cheese) + "\n") : "";
        String sau = s ? (getString(R.string.order_summary_sausage) + "\n") : "";
        String ham = h ? (getString(R.string.order_summary_ham) + "\n") : "";
        String pin = pine ? (getString(R.string.order_summary_pineapple) + "\n") : "";
        String mus = m ? (getString(R.string.order_summary_mushroom) + "\n") : "";

        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                che +
                no  +
                pep +
                sau +
                ham +
                pin +
                mus +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasPepperoni, boolean hasExtraCheese, boolean hasSausage, boolean hasHam, boolean hasPineapple, boolean hasMushroom) {
        int basePrice = PIZZA_PRICE;
        if (hasPepperoni) {
            basePrice += TOPPINGS_PRICE;
        }
        if (hasExtraCheese) {
            basePrice += TOPPINGS_PRICE;
        }
        if (hasSausage) {
            basePrice += TOPPINGS_PRICE;
        }
        if (hasHam) {
            basePrice += TOPPINGS_PRICE;
        }
        if (hasPineapple) {
            basePrice += TOPPINGS_PRICE;
        }
        if (hasMushroom) {
            basePrice += TOPPINGS_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select at least one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}