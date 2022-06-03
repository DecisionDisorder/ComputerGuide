package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;
import com.threebro.computerguide.Combi.FinalTwo;

import java.text.DecimalFormat;

public class EstimateListActivity extends AppCompatActivity {

    private int indexOfSet;             // Index of set of product list or past model list
    private PcComponent[] pcComponents; // PC components array
    private FinalTwo desktopEstimate;   // Selected desktop estimate
    private Laptop laptopEstimate;      // Selected laptop model

    // URL of price compare web site for searching
    private String url = "http://search.danawa.com/mobile/dsearch.php?keyword=";

    static String[] componentsNameArr;  // Component names that will be title of each component

    private TextView priceTextView;     // Total price of this estimate or model
    private DBHelper db;                // Internal Database Helper

    // Desktop Component type enumeration
    public enum PcComponentType {
        CPU, COOLER, MB, RAM, VGA, STORAGE, CASE, POWER;

        // Get enumeration in array type
        private static PcComponentType[] allValues = values();

        // Convert integer to enum
        public static PcComponentType fromOrdinal(int n) {return allValues[n];}
    }

    // Laptop Component type enumeration
    public enum LaptopComponentType {
        NAME, COMPANY, CPU, RAM, VGA, STORAGE, OS, DISPLAY, WEIGHT;

        // Get enumeration in array type
        private static LaptopComponentType[] allValues = values();

        // Convert integer to enum
        public static LaptopComponentType fromOrdinal(int n) {return allValues[n];}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize internal database helper
        db = new DBHelper(this);
        setContentView(R.layout.activity_estimate_list);

        // Open intent and bundle received from prior activity
        Intent rcvIntent = getIntent();
        indexOfSet = rcvIntent.getIntExtra("index",3);
        String listType = rcvIntent.getStringExtra("ListType");
        Bundle productBundle = rcvIntent.getBundleExtra("productBundle");
        String computerType = productBundle.getString("computerType");

        if(computerType.equals(ComputerType.DESKTOP.toString())) {
            // If computer type is desktop and it is new estimate
            if (listType.equals("New")) {
                // Get estimate data from new desktop set list
                desktopEstimate = MainActivity.desktopSet.getFinal2().get(indexOfSet);
                // Load estimate spec UI
                loadEstimateList(ComputerType.DESKTOP);
                // Add estimate data to internal DB
                db.addProductList(desktopEstimate);
            }
            // It is already recommended estimate,
            else if (listType.equals("Past")) {
                // Get estimate data from recommended list and load UI
                desktopEstimate = PastModelListActivity.recommendListManager.recommendedSetList.get(indexOfSet).getRecommendedSet();
                loadEstimateList(ComputerType.DESKTOP);
            }
        }
        else {
            // If computer type is laptop and it is new model
            if (listType.equals("New")) {
                // Get model data from new laptop model list
                laptopEstimate = MainActivity.laptopSet.getFlaptop().get(indexOfSet);
                // Load model spec UI
                loadEstimateList(ComputerType.LAPTOP);
                // Add model data to internal DB
                db.addLabProductList(laptopEstimate);
            }
            // It is already recommended model,
            else if (listType.equals("Past")) {
                // Get model data from recommended list and load UI
                laptopEstimate = PastModelListActivity.recommendListManager.recommendLaptopSetList.get(indexOfSet).getRecommendedLaptop();
                loadEstimateList(ComputerType.LAPTOP);
            }
        }
    }

    // Load Desktop/Laptop Components UI
    private void loadEstimateList(ComputerType type) {
        // Format numbers to make them easier to see (e.g., 1,000).
        DecimalFormat formatter = new DecimalFormat("#,###");

        // Get UI view from activity
        priceTextView = findViewById(R.id.priceID);
        LinearLayout componentContainer = findViewById(R.id.componentContainer);

        // Component icon array
        TypedArray iconIdArr;

        if(type == ComputerType.DESKTOP) {
            // On the desktop, display the total amount of the estimate,
            // create an array of pc components, and load the component name and component icon array.
            priceTextView.setText("Total : " + formatter.format(desktopEstimate.getPrice()) + "원");
            pcComponents = new PcComponent[PcComponentType.values().length];
            componentsNameArr = getResources().getStringArray(R.array.computer_components);
            iconIdArr = getResources().obtainTypedArray(R.array.icon_array);

            // Set a title, icon, price, and name for each component.
            for (int i = 0; i < pcComponents.length; i++) {
                // If setting component is RAM, set the max ram slot amount of mainboard, and ram amount
                if (i == PcComponentType.RAM.ordinal()) {
                    int maxAmount = desktopEstimate.getMb().getSlotAmount();
                    pcComponents[i] = new PcComponent(this, maxAmount);
                    pcComponents[i].setActiveAmountSet(true, getComponentAmount(i));
                }
                // Other components simply generate PcComponent.
                else
                    pcComponents[i] = new PcComponent(this);
                pcComponents[i].setTitle(componentsNameArr[i]);
                pcComponents[i].setIcon(iconIdArr.getDrawable(i));

                String name = getComponentName(i, desktopEstimate);
                String price = formatter.format(getComponentPrice(i, desktopEstimate)) + "원";
                setURL(name, false);
                pcComponents[i].setNameAndPrice(name, price);

                // If setting component is Graphic Card or Power supply, set text size small
                // (Because their names are too long)
                if (i == PcComponentType.VGA.ordinal() || i == PcComponentType.POWER.ordinal())
                    pcComponents[i].setTextSize();
                componentContainer.addView(pcComponents[i]);
            }
        }
        else {
            // On the laptop, display the total amount of the model,
            // create an array of laptop components, and load the component name and component icon array.
            priceTextView.setText("Total : " + formatter.format(laptopEstimate.getPrice()) + "원");
            pcComponents = new PcComponent[LaptopComponentType.values().length];
            componentsNameArr = getResources().getStringArray(R.array.laptop_components);
            iconIdArr = getResources().obtainTypedArray(R.array.laptop_icon_array);

            // Set a title, icon, price, and name for each component.
            for (int i = 0; i < pcComponents.length; i++) {
                pcComponents[i] = new PcComponent(this);
                pcComponents[i].setTitle(componentsNameArr[i]);
                pcComponents[i].setIcon(iconIdArr.getDrawable(i));

                String name = getComponentName(i, laptopEstimate);
                String price = "";

                if(i == LaptopComponentType.NAME.ordinal())
                    setURL(name, true);
                pcComponents[i].setNameAndPrice(name, price);
                componentContainer.addView(pcComponents[i]);
            }
        }
    }

    // Get component name of desktop estimate with component index
    public static String getComponentName(int component, FinalTwo desktopEstimate) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return desktopEstimate.getCpu().getName();
            case COOLER:
                return desktopEstimate.getCl().getName();
            case MB:
                return desktopEstimate.getMb().getName();
            case RAM:
                RAM ram = desktopEstimate.getRm();
                return ram.getName() + " " + ram.getRamCapacity();
            case VGA:
                return desktopEstimate.getGpu().getName();
            case STORAGE:
                Storage storage = desktopEstimate.getSt();
                return storage.getName() + " " + storage.getCapacity();
            case CASE:
                return desktopEstimate.getCa().getName();
            case POWER:
                return desktopEstimate.getPw().getName();
        }
        return "";
    }

    // Get component price of desktop estimate with component index
    public static int getComponentPrice(int component, FinalTwo desktopEstimate) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return desktopEstimate.getCpu().getPrice();
            case COOLER:
                return desktopEstimate.getCl().getPrice();
            case MB:
                return desktopEstimate.getMb().getPrice();
            case RAM:
                return desktopEstimate.getRm().getPrice();
            case VGA:
                return desktopEstimate.getGpu().getPrice();
            case STORAGE:
                return desktopEstimate.getSt().getPrice();
            case CASE:
                return desktopEstimate.getCa().getPrice();
            case POWER:
                return desktopEstimate.getPw().getPrice();
        }
        return 0;
    }

    // Get component name of laptop model with component index
    public static String getComponentName(int component, Laptop laptopEstimate) {
        LaptopComponentType type = LaptopComponentType.fromOrdinal(component);
        switch (type) {
            case NAME:
                return laptopEstimate.getName();
            case COMPANY:
                return laptopEstimate.getCompany();
            case CPU:
                return laptopEstimate.getCpu1() + " " + laptopEstimate.getCpu2() + " " + laptopEstimate.getCpu3();
            case RAM:
                return (int)laptopEstimate.getMemory() + "GB";
            case VGA:
                return laptopEstimate.getGraphic();
            case STORAGE:
                return (int)laptopEstimate.getSsd() + "GB";
            case OS:
                return laptopEstimate.getOs();
            case DISPLAY:
                return laptopEstimate.getDisplay() + " inch";
            case WEIGHT:
                return laptopEstimate.getWeight() + " kg";
        }
        return "";
    }

    // Add the number of components that match the 'component' index by the 'amount'
    public void addComponentAmount(int component, int amount) {
        if(component == PcComponentType.RAM.ordinal()) {
            // If the component is RAM, set the current number of RAM plus amount and update the text view
            int priorAmount = getComponentAmount(component);
            desktopEstimate.getRm().setAmount(priorAmount + amount);
            pcComponents[component].setAmountTextView(getComponentAmount(component));

            // Update component and total price
            updatePrice(component);
        }
    }

    // Gets the number of components that match the component index
    public int getComponentAmount(int component) {
        if(component == PcComponentType.RAM.ordinal()) {
            return desktopEstimate.getRm().getAmount();
        }

        return 0;
    }

    // Set Hyperlink button of price comparing site
    public void setURL(String name, boolean active) {
        Button comparePriceButton = findViewById(R.id.comparePriceButton);
        if(active) {
            // If comparePriceButton need to be active, set button visible
            comparePriceButton.setVisibility(View.VISIBLE);

            // Set compare price button listener with hyperlink function that connects to a price comparison site.
            comparePriceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String danawaUrl = convertNameToURL(name);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(danawaUrl));
                    startActivity(intent);
                }
            });
        }
        // If comparePriceButton doesn't need to be active, set button not visible
        else {
            comparePriceButton.setVisibility(View.GONE);
        }
    }

    // Get price comparison URL with search keyword
    private String convertNameToURL(String name) {
        String url = this.url;
        url += name.replaceAll(" ", "+");
        return url;
    }

    // Update component and total price
    private void updatePrice(int changedComponent) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(changedComponent == PcComponentType.RAM.ordinal()) {
            String price = formatter.format(getComponentPrice(changedComponent, desktopEstimate)) + "원";
            pcComponents[changedComponent].setNameAndPrice(getComponentName(changedComponent, desktopEstimate), price);
        }

        priceTextView.setText("Total : "+formatter.format(desktopEstimate.getPrice()) + "원");
    }
}