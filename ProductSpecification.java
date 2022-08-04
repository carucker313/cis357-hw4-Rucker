public class ProductSpecification {
    private String id;
    private float price;
    private String description;

    /**constructor with args**/
    public ProductSpecification(String id,float price,String description){
        this.id = id;
        this.price = price;
         this.description = description;

    }
    /**A getter method that returns the ID of the item**/
    public String getID(){
        return id;
    }
    /**A setter method that gets the PRICE of the item**/
    public float getPrice(){
        return price;
    }
    /**A getter method that returns the description of the item**/

    public String getDescription(){
        return description;
    }
    /**A setter method that sets the description of the item**/
    public void setDescription(String description) {
        this.description = description;
    }
    /**A setter method that sets the ID of the item**/
    public void setId(String id) {
        this.id = id;
    }

    /**A setter method that sets the price of the item **/
    public void setPrice(float price) {
        this.price = price;
    }
}
