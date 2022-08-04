public class SalesLineItem {
    private int qty;
    private ProductSpecification productSpec;

    public SalesLineItem(ProductSpecification productSpec, int qty) {
        this.productSpec = productSpec;
        this.qty =qty;
    }
    public double getSubtotal(){
        return productSpec.getPrice()*qty;
    }
}
