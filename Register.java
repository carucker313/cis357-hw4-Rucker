public class Register {
    private Sale sale;
    private ProductCatalog catalog;

    public Register(ProductCatalog catalog){
        this.catalog = catalog;
    }
    public void makeNewSale() {
        sale = new Sale();
    }
    public void enterItem(String id, int quantity) {
        ProductSpecification spec = catalog.getSpecification(id);
        sale.makeLineItem(spec, quantity);
    }
}
