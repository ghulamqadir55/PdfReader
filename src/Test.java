
import org.openqa.selenium.By;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;

class AppiumDemo {


    public static void main(String[] args) throws MalformedURLException, InterruptedException {


        ArrayList<String> devices = new ArrayList<>();
//        devices.add("c8623dd1");
        devices.add("f401a4a5");


        for (String device : devices) {

            Operation object = new Operation();


            System.out.println(device);
            object.setUpDevice(device,
                    "com.pdfreader.pdfviewer.pdf.reader",
                    "com.pdfreader.pdfviewer.Activities.ActivitySplash",
                    "http://127.0.0.1:4723/wd/hub");


            //splash and permission

            Thread.sleep(11000);

            if (object.checkItemDisplayed("com.pdfreader.pdfviewer.pdf.reader:id/layoutJump")) {
                System.out.println("no permission");
                object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/txtPerAllow");
                Thread.sleep(3000);
                object.performClick("android:id/switch_widget");
                object.performClick("miui:id/up");
            }

            Thread.sleep(5000);

             checkPremium(object);


            object.getMyDriver().findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]")).click();

            Thread.sleep(2000);
            object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/imgPdfBack");

            Thread.sleep(1000);
            object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/imgMenuItemList");

            Thread.sleep(2000);

            //commit
            object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/itemSheetRename");
            Thread.sleep(1000);

            object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/inpRename");
            Thread.sleep(1000);


            object.performInput("com.pdfreader.pdfviewer.pdf.reader:id/inpRename","markee");

            Thread.sleep(2000);

            object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/txtDialogOk");
            Thread.sleep(1000);




              object.swipeScreen(Operation.Direction.UP);
            Thread.sleep(5000);




            Thread.sleep(10000);

            object.getMyDriver().quit();

        }


    }

    private static void checkPremium(Operation object) throws InterruptedException {

        //dashboard menu top
        //premium check
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/imgMenuItem");
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/itemGoToPrem");

        Thread.sleep(5000);

        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/imgBottomSheetClose");
        Thread.sleep(1000);

        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/imgMenuItem");
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/itemGoToPrem");
        Thread.sleep(3000);
        //button
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/relMonthlyLayoutChild");
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/relYearlyLayoutChild");
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/relLifeTimeLayoutChild");
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/relPurchaseLayout");
        Thread.sleep(5000);
        object.performClick("com.android.vending:id/0_resource_name_obfuscated");
        Thread.sleep(2000);
        object.performClick("com.pdfreader.pdfviewer.pdf.reader:id/imgBottomSheetClose");
        Thread.sleep(2000);


    }

}

