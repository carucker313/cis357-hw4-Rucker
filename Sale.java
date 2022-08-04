import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Sale {
    private List<SalesLineItem> saleLineItems = new ArrayList();
    private boolean isComplete = false;
    private Date date = new Date();
    private Payment payment;

    public void makeLineItem(ProductSpecification spec, int quantity) {
        saleLineItems.add(new SalesLineItem(spec, quantity));
    }
    public void becomeComplete() { isComplete = true; }
    public boolean isComplete(){
        return isComplete;
    }

    public float getTotal() {
        float total = 0;
        Iterator i = saleLineItems.iterator();
        while (i.hasNext()) {
            SalesLineItem sli = (SalesLineItem) i.next();
            total = (float) (total+sli.getSubtotal());
        }
        return total;
    }
    public void makePayment(float cashTendered) {
        payment = new Payment(cashTendered);
    }
    public float getBalance() {
        return payment.getAmount()-getTotal();
    }
}
